package by.shyshaliaksey.webproject.model.service.impl;

import static by.shyshaliaksey.webproject.controller.StaticPath.IMAGE_DEFAULT;

import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.DatabaseFeedback;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.entity.Token;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import by.shyshaliaksey.webproject.model.service.ValidationService;
import by.shyshaliaksey.webproject.model.util.DateHandler;
import by.shyshaliaksey.webproject.model.util.DeploymentPropertiesReader;
import by.shyshaliaksey.webproject.model.util.EmailMessanger;
import by.shyshaliaksey.webproject.model.util.FileHandler;
import by.shyshaliaksey.webproject.model.util.CryptoHandler;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.util.localization.LocaleKey;
import jakarta.servlet.http.Part;
import jakarta.xml.bind.DatatypeConverter;

/**
 * Implementer of {@link UserService} designed for communication between
 * controller and model layer for actions related to user.
 * 
 * @author Aliaksey Shysh
 *
 */
public class UserServiceImpl implements UserService {

	@Override
	public Map<Feedback.Key, Object> authorizeUser(String email, String password) throws ServiceException {
		try {
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			validationService.validateEmailFormInput(result, email);
			validationService.validatePasswordFormInput(result, password);

			if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))) {
				UserDao userDao = DaoProvider.getInstance().getUserDao();
				Map<DatabaseFeedback.Key, Optional<String>> loginData = userDao.findUserLoginData(email);
				Optional<String> passwordDatabaseOptional = loginData.get(DatabaseFeedback.Key.PASSWORD);
				Optional<String> saltDatabaseOptional = loginData.get(DatabaseFeedback.Key.SALT);
				if (passwordDatabaseOptional.isPresent() && saltDatabaseOptional.isPresent()) {
					String passwordHash = passwordDatabaseOptional.get();
					String saltHex = saltDatabaseOptional.get();
					byte[] salt = DatatypeConverter.parseHexBinary(saltHex);
					String enteredPassword = DatatypeConverter
							.printHexBinary(CryptoHandler.hashPassword(password, salt));
					if (enteredPassword.equals(passwordHash)) {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
						result.put(Feedback.Key.EMAIL_STATUS, Boolean.TRUE);
						result.put(Feedback.Key.PASSWORD_STATUS, Boolean.TRUE);
						result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
						result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
						result.put(Feedback.Key.PASSWORD_FEEDBACK,
								LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
					result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
				result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
				result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
				result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
				result.put(Feedback.Key.PASSWORD_FEEDBACK,
						LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Can not login with this credentials", e);
		}
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		try {
			UserDao userDao = DaoProvider.getInstance().getUserDao();
			Optional<User> user = userDao.findByEmail(email);
			return user;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by email " + email + " :" + e.getMessage(), e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> registerUser(String email, String login, String password, String passwordRepeat,
			LocaleAttribute locale) throws ServiceException {
		try {
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			validationService.validateEmailFormInput(result, email);
			validationService.validateLoginFormInput(result, login);
			validationService.validatePasswordFormInput(result, password);
			validationService.validatePasswordConfirmationFormInput(result, passwordRepeat);
			if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.LOGIN_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_CONFIRMATION_STATUS))) {
				if (password.equals(passwordRepeat)) {
					byte[] salt = CryptoHandler.createSalt();
					byte[] hashedPassword = CryptoHandler.hashPassword(password, salt);
					String saltHex = DatatypeConverter.printHexBinary(salt);
					String hashedPasswordHex = DatatypeConverter.printHexBinary(hashedPassword);
					UserDao userDao = DaoProvider.getInstance().getUserDao();
					Optional<User> userOptional = userDao.findByEmail(email);
					boolean registerResult = false;

					if (!userOptional.isPresent() || !userOptional.get().getLogin().equals(login)) {
						registerResult = userDao.registerUser(email, login, hashedPasswordHex, saltHex,
								IMAGE_DEFAULT.getValue(), Role.USER);
						if (registerResult) {
							String token = CryptoHandler.createToken();
							final int minutesToExpriration = 5;
							String expirationTime = DateHandler.prepareDate(minutesToExpriration, Calendar.MINUTE);
							userDao.addNewToken(email, token, expirationTime);
							EmailMessanger.sendEmail(email, token,
									EmailMessanger.Function.REGISTER, locale);
							result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
							result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.CHECK_YOUR_EMAIL.getValue());
							result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
							result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
							result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						} else {
							result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
							result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
							result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
							result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
									LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						}
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
						User user = userOptional.get();
						if (user.getEmail().equals(email)) {
							result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.EMAIL_FEEDBACK,
									LocaleKey.EMAIL_FEEDBACK_INVALID_USER_EXISTS.getValue());
						}
						if (user.getLogin().equals(login)) {
							result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.LOGIN_FEEDBACK,
									LocaleKey.LOGIN_FEEDBACK_INVALID_USER_EXISTS.getValue());
						}
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.PASSWORD_FEEDBACK,
							LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
					result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
							LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user register: " + e.getMessage(), e);
		}
	}

	@Override
	public Optional<User> findByLogin(String login) throws ServiceException {
		try {
			UserDao userDao = DaoProvider.getInstance().getUserDao();
			Optional<User> user = userDao.findByLogin(login);
			return user;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by login " + login + " :" + e.getMessage(), e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> makeRequestForNewEmail(String email, String newEmail, int userId,
			LocaleAttribute locale) throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			validationService.validateEmailFormInput(result, newEmail);

			if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS))) {
				UserDao userDao = DaoProvider.getInstance().getUserDao();
				Optional<User> userOld = userDao.findByEmail(newEmail);
				if (!userOld.isPresent()) {
					String token = CryptoHandler.createToken();
					final int minutesToExpriration = 5;
					String expirationTime = DateHandler.prepareDate(minutesToExpriration, Calendar.MINUTE);
					userDao.addNewToken(email, token, expirationTime, newEmail);
					// send message
					boolean isMessageSent = EmailMessanger.sendEmail(newEmail, token,
							EmailMessanger.Function.CHANGE_EMAIL, locale);
					if (isMessageSent) {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
						result.put(Feedback.Key.EMAIL_STATUS, Boolean.TRUE);
						result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.CHECK_YOUR_EMAIL.getValue());
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
						result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_FEEDBACK_INVALID_USER_EXISTS.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while changing email " + email + " :" + e.getMessage(), e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> changeLogin(String login, String newLogin, int userId) throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			validationService.validateLoginFormInput(result, newLogin);

			if (Boolean.TRUE.equals(result.get(Feedback.Key.LOGIN_STATUS))) {
				UserDao userDao = DaoProvider.getInstance().getUserDao();
				Optional<User> userOld = userDao.findByLogin(newLogin);
				if (!userOld.isPresent()) {
					Optional<User> user = userDao.findByLogin(login);
					if (user.isPresent() && user.get().getId() == userId) {
						boolean updateResult = userDao.updateUserLogin(newLogin, userId);
						if (updateResult) {
							result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
							result.put(Feedback.Key.LOGIN_STATUS, Boolean.TRUE);
							result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						} else {
							result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.INTERNAL_SERVER_ERROR);
							result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						}
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
						result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.LOGIN_FEEDBACK,
								LocaleKey.LOGIN_FEEDBACK_INVALID_AUTHORIZATION_ERROR.getValue());
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.LOGIN_FEEDBACK_INVALID_USER_EXISTS.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while changing login " + login + " :" + e.getMessage(), e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> changePassword(String password, String passwordConfirm, int userId)
			throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			validationService.validatePasswordFormInput(result, password);
			validationService.validatePasswordConfirmationFormInput(result, passwordConfirm);
			validationService.validatePasswordEquality(result, password, passwordConfirm);

			if (Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_CONFIRMATION_STATUS))) {
				UserDao userDao = DaoProvider.getInstance().getUserDao();
				Optional<User> user = userDao.findById(userId);
				if (user.isPresent() && user.get().getId() == userId) {
					Map<DatabaseFeedback.Key, Optional<String>> loginData = userDao.findUserLoginData(userId);
					Optional<String> saltDatabaseOptional = loginData.get(DatabaseFeedback.Key.SALT);
					if (saltDatabaseOptional.isPresent()) {
						String saltHex = saltDatabaseOptional.get();
						byte[] salt = DatatypeConverter.parseHexBinary(saltHex);
						String hashedPassword = DatatypeConverter
								.printHexBinary(CryptoHandler.hashPassword(password, salt));
						boolean passwordUpdateResult = userDao.updateUserPassword(hashedPassword, userId);
						if (passwordUpdateResult) {
							result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
						} else {
							result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
							result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
							result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
							result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
									LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						}
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
						result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID.getValue());
						result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.PASSWORD_FEEDBACK,
								LocaleKey.PASSWORD_CONFIRMATION_FEEDBACK_INVALID.getValue());
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.PASSWORD_FEEDBACK,
							LocaleKey.PASSWORD_FEEDBACK_INVALID_NO_USER_WITH_ID.getValue());
					result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
							LocaleKey.PASSWORD_FEEDBACK_INVALID_NO_USER_WITH_ID.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by Id " + userId + " :" + e.getMessage(), e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> updateImage(String serverDeploymentPath, Part userImage, int userId)
			throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			result.put(Feedback.Key.IMAGE_PATH, LocaleKey.EMPTY_MESSAGE.getValue());
			String fileExtension = FilenameUtils.getExtension(userImage.getSubmittedFileName());
			validationService.validateImageFormInput(result, fileExtension, userImage.getSize());

			if (Boolean.TRUE.equals(result.get(Feedback.Key.IMAGE_STATUS))) {

				String fileName = userImage.getSubmittedFileName();
				String newFileName = FileHandler.prepareAlienImageName(fileName);
				String imageUrl = StaticPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
				boolean uploadToDeployment = FileHandler.uploadImage(serverDeploymentPath,
						StaticPath.PROFILE_IMAGE_FOLDER.getValue(), newFileName, userImage);
				UserDao userDao = DaoProvider.getInstance().getUserDao();
				boolean updateImageResult = userDao.updateProfileImage(imageUrl, userId);
				if (uploadToDeployment && updateImageResult) {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
					result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
					result.put(Feedback.Key.IMAGE_PATH,
							DeploymentPropertiesReader.Deployment.CURRENT_DEPLOYMENT.getValue() + imageUrl);
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.INTERNAL_SERVER_ERROR);
					result.put(Feedback.Key.IMAGE_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (ServiceException | DaoException e) {
			throw new ServiceException("Error occured when updating image for userId " + userId + " :" + e.getMessage(),
					e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> addNewComment(int currentUserId, String alienIdString, String newComment)
			throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			validationService.validateCommentFormInput(result, newComment);
			int alienId = Integer.parseInt(alienIdString);
			if (Boolean.TRUE.equals(result.get(Feedback.Key.COMMENT_STATUS))) {
				UserDao userDao = DaoProvider.getInstance().getUserDao();
				boolean addResult = userDao.addNewComment(currentUserId, alienId, newComment);
				if (addResult) {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
					result.put(Feedback.Key.COMMENT_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.COMMENT_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.COMMENT_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(
					"Error occured while adding comment for user " + currentUserId + " :" + e.getMessage(), e);
		}
	}

	@Override
	public boolean deleteComment(String commentIdString, User currentUser) throws ServiceException {
		try {
			boolean result = false;
			UserDao userDao = DaoProvider.getInstance().getUserDao();
			int commentId = Integer.parseInt(commentIdString);
			if (currentUser.getRole() == Role.USER) {
				result = userDao.deleteComment(commentId, currentUser.getId());
			} else if (currentUser.getRole() == Role.ADMIN) {
				result = userDao.deleteComment(commentId);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(
					"Error occured while deleting comment " + commentIdString + " :" + e.getMessage(), e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> suggestNewAlien(String alienName, String alienSmallDescription,
			String alienFullDescription, Part alienImage, String serverDeploymentPath) throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			AlienDao alienDao = DaoProvider.getInstance().getAlienDao();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			validationService.validateAlienInfoFormInput(result, alienName, alienSmallDescription,
					alienFullDescription);
			String fileName = alienImage.getSubmittedFileName();
			validationService.validateImageFormInput(result, FilenameUtils.getExtension(fileName),
					alienImage.getSize());

			if (Boolean.TRUE.equals(result.get(Feedback.Key.ALIEN_NAME_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.IMAGE_STATUS))) {
				Optional<Alien> alienInDatabase = alienDao.findByName(alienName);
				if (!alienInDatabase.isPresent()) {
					String newFileName = FileHandler.prepareAlienImageName(fileName);
					String imageUrl = StaticPath.ALIEN_IMAGE_FOLDER.getValue() + newFileName;
					alienDao.suggestNewAlien(alienName, alienSmallDescription, alienFullDescription, imageUrl);
					boolean uploadToDeployment = FileHandler.uploadImage(serverDeploymentPath,
							StaticPath.ALIEN_IMAGE_FOLDER.getValue(), newFileName, alienImage);
					if (uploadToDeployment) {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
						result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.INTERNAL_SERVER_ERROR);
						result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.IMAGE_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK,
								LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK,
								LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.ALIEN_NAME_FEEDBACK,
							LocaleKey.ALIEN_NAME_FEEDBACK_INVALID_ALREADY_EXISTS.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when suggesting new alien " + alienName + " :" + e.getMessage(),
					e);
		}
	}

	@Override
	public Map<Feedback.Key, Object> suggestNewAlienImage(String alienName, Part alienImage,
			String serverDeploymentPath) throws ServiceException {
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			AlienDao alienDao = DaoProvider.getInstance().getAlienDao();
			Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
			validationService.validateAlienNameFormInput(result, alienName);
			String fileExtension = FilenameUtils.getExtension(alienImage.getSubmittedFileName());
			validationService.validateImageFormInput(result, fileExtension, alienImage.getSize());

			if (Boolean.TRUE.equals(result.get(Feedback.Key.ALIEN_NAME_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.IMAGE_STATUS))) {
				Optional<Alien> alienInDatabase = alienDao.findByName(alienName);
				if (alienInDatabase.isPresent()) {

					String fileName = alienImage.getSubmittedFileName();
					String newFileName = FileHandler.prepareAlienImageName(fileName);
					String imageUrl = StaticPath.ALIEN_IMAGE_FOLDER.getValue() + newFileName;
					int alienId = alienInDatabase.get().getId();
					boolean uploadToDeployment = FileHandler.uploadImage(serverDeploymentPath,
							StaticPath.ALIEN_IMAGE_FOLDER.getValue(), newFileName, alienImage);
					boolean suggestImageResult = alienDao.suggestNewAlienImage(alienId, imageUrl);
					if (uploadToDeployment && suggestImageResult) {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
						result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
						result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
					} else {
						result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.INTERNAL_SERVER_ERROR);
						result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.IMAGE_STATUS, Boolean.FALSE);
						result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
						result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
					}
				} else {
					result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
					result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
					result.put(Feedback.Key.ALIEN_NAME_FEEDBACK,
							LocaleKey.ALIEN_NAME_FEEDBACK_INVALID_DOES_NOT_EXIST.getValue());
				}
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			}
			return result;
		} catch (

		DaoException e) {
			throw new ServiceException(
					"Error occured when suggesting new alien image " + alienName + " :" + e.getMessage(), e);
		}
	}

	@Override
	public boolean setNewEmail(String tokenRequested) throws ServiceException {
		try {
			boolean result = false;
			UserDao userDao = DaoProvider.getInstance().getUserDao();
			Optional<Token> tokenOptional = userDao.findToken(tokenRequested, Token.Status.NORMAL);
			if (tokenOptional.isPresent()) {
				Token token = tokenOptional.get();
				boolean isExpired = DateHandler.isExpired(token.getExpirationDate());
				if (!isExpired) {
					boolean updateResult = userDao.updateUserEmail(token.getEmail(), token.getNewEmail());
					result = updateResult;
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Can not activate user account", e);
		}
	}

	@Override
	public boolean activateAccount(String tokenRequested) throws ServiceException {
		try {
			boolean result = false;
			UserDao userDao = DaoProvider.getInstance().getUserDao();
			Optional<Token> tokenOptional = userDao.findToken(tokenRequested, Token.Status.NORMAL);
			if (tokenOptional.isPresent()) {
				Token token = tokenOptional.get();
				boolean isExpired = DateHandler.isExpired(token.getExpirationDate());
				if (!isExpired) {
					boolean activateUserAccountResult = userDao.updateUserStatusToNormal(token.getEmail());
					boolean setTokenStatusExpired = userDao.setTokenStatusExpired(tokenRequested);
					result = activateUserAccountResult && setTokenStatusExpired;
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Can not activate user account", e);
		}
	}

}

package by.shyshaliaksey.webproject.model.service.impl;

import static by.shyshaliaksey.webproject.controller.FilePath.IMAGE_DEFAULT;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.io.FilenameUtils;

import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.LoginData;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.AddNewCommentResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.EmailUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.ImageUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.PasswordUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.RegisterResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.RequestRestorePasswordTokenResultInfo;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.TimeService;
import by.shyshaliaksey.webproject.model.service.UserService;
import by.shyshaliaksey.webproject.model.service.ValidationService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.xml.bind.DatatypeConverter;

public class UserServiceImpl implements UserService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final UserDao userDao = daoProvider.getUserDao();

	@Override
	public LoginResultInfo userLogIn(String email, String password) throws ServiceException {
		LoginResultInfo result = new LoginResultInfo();
		try {
			Optional<LoginData> loginData = userDao.findUserLoginData(email);
			if (loginData.isPresent()) {
				result.setEmailCorrect(true);
				String passwordHash = loginData.get().getHashedPasswordHex();
				String saltHex = loginData.get().getSaltHex();
				byte[] salt = DatatypeConverter.parseHexBinary(saltHex);
				String enteredPassword = DatatypeConverter.printHexBinary(hashPassword(password, salt));
				if (enteredPassword.equals(passwordHash)) {
					result.setPasswordCorrect(true);
				} else {
					result.setPasswordErrorInfo(ErrorFeedback.PASSWORD_INCORRECT_FOR_EMAIL.getValue());
				}
			} else {
				result.setEmailErrorInfo(ErrorFeedback.NO_USER_WITH_EMAIL.getValue());
				result.setPasswordErrorInfo(ErrorFeedback.NO_USER_WITH_EMAIL.getValue());
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Can not login with this credentials", e);
		}
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		try {
			Optional<User> user = userDao.findByEmail(email);
			return user;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by email " + email + " :" + e.getMessage(), e);
		}
	}

	// TODO do something with dao register
	@Override
	public RegisterResultInfo registerUser(String email, String login, String password, String passwordRepeat,
			String imagePath, Role role) throws ServiceException {
		RegisterResultInfo result = new RegisterResultInfo();
		ValidationService validationService = ServiceProvider.getInstance().getValidationService();
		// all validations here
		if (validationService.validateEmail(email)) {
			result.setEmailCorrect(true);
		} else {
			result.setEmailCorrect(false);
			result.setEmailErrorInfo(ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_EMAIL.getValue());
		}
		if (validationService.validateLogin(login)) {
			result.setLoginCorrect(true);
		} else {
			result.setLoginCorrect(false);
			result.setLoginErrorInfo(ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_LOGIN.getValue());
		}
		if (validationService.validatePassword(password)) {
			result.setPasswordCorrect(true);
		} else {
			result.setPasswordCorrect(false);
			result.setPasswordErrorInfo(ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORD.getValue());
		}
		if (validationService.validatePassword(passwordRepeat)) {
			result.setPasswordConfirmationCorrect(true);
		} else {
			result.setPasswordConfirmationCorrect(false);
			result.setPasswordConfirmationErrorInfo(
					ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORD_CONFIRMATION.getValue());
		}
		try {
			if (result.isEmailCorrect() && result.isLoginCorrect() && result.isPasswordCorrect()
					&& result.isPasswordConfirmationCorrect()) {
				if (password.equals(passwordRepeat)) {
					byte[] salt = createSalt();
					byte[] hashedPassword = hashPassword(password, salt);
					String saltHex = DatatypeConverter.printHexBinary(salt);
					String hashedPasswordHex = DatatypeConverter.printHexBinary(hashedPassword);
					Optional<User> userOptional = userDao.findByEmail(email);
					boolean registerResult = false;
					if (userOptional.isPresent()) {
						User user = userOptional.get();
						if (user.getEmail().equals(email)) {
							result.setEmailCorrect(false);
							result.setEmailErrorInfo(
									ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_EMAIL_USER_EXISTS.getValue());
						}
						if (user.getLogin().equals(login)) {
							result.setLoginCorrect(false);
							result.setLoginErrorInfo(
									ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_USER_EXISTS.getValue());
						}
					} else {
						registerResult = userDao.registerUser(email, login, hashedPasswordHex, saltHex,
								IMAGE_DEFAULT.getValue(), Role.USER);
						result.setRegistrationSuccessful(registerResult);
					}
				} else {
					result.setPasswordCorrect(false);
					result.setPasswordConfirmationCorrect(false);
					result.setPasswordErrorInfo(
							ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS.getValue());
					result.setPasswordConfirmationErrorInfo(
							ErrorFeedback.REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS.getValue());
				}

			}
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user register: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Optional<User> findByLogin(String login) throws ServiceException {
		try {
			Optional<User> user = userDao.findByLogin(login);
			return user;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by login " + login + " :" + e.getMessage(), e);
		}
	}

	@Override
	public EmailUpdateResultInfo changeEmail(String email, String newEmail, int userId) throws ServiceException {
		try {
			EmailUpdateResultInfo result = new EmailUpdateResultInfo();
			ValidationService validation = ServiceProvider.getInstance().getValidationService();
			if (validation.validateEmail(newEmail)) {
				result.setEmailCorrect(true);
				Optional<User> userOld = userDao.findByEmail(newEmail);
				if (userOld.isPresent()) {
					result.setEmailCorrect(false);
					result.setEmailErrorInfo(
							ErrorFeedback.UPDATE_EMAIL_RESULT_INFO_FEEDBACK_INVALID_EMAIL_USER_EXISTS.getValue());
				} else {
					Optional<User> user = userDao.findByEmail(email);
					if (user.isPresent() && user.get().getId() == userId) {
						boolean updateResult = userDao.updateUserEmail(newEmail, userId);
						if (updateResult) {
							result.setEmailCorrect(true);
						} else {
							result.setEmailCorrect(false);
							result.setEmailErrorInfo(ErrorFeedback.UPDATE_STANDARD_EMAIL_FEEDBACK.getValue());
						}
					} else {
						result.setEmailCorrect(false);
						result.setEmailErrorInfo(
								ErrorFeedback.UPDATE_EMAIL_RESULT_INFO_FEEDBACK_INVALID_AUTHORIZATION_ERROR.getValue());
					}
				}
			} else {
				result.setEmailCorrect(false);
				result.setEmailErrorInfo(ErrorFeedback.UPDATE_EMAIL_RESULT_INFO_FEEDBACK_INVALID_EMAIL.getValue());
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while changing email " + email + " :" + e.getMessage(), e);
		}
	}

	@Override
	public LoginUpdateResultInfo changeLogin(String login, String newLogin, int userId) throws ServiceException {
		LoginUpdateResultInfo result = new LoginUpdateResultInfo();
		try {
			ValidationService validation = ServiceProvider.getInstance().getValidationService();
			if (validation.validateLogin(newLogin)) {
				result.setLoginCorrect(true);
				Optional<User> userOld = userDao.findByLogin(newLogin);
				if (userOld.isPresent()) {
					result.setLoginCorrect(false);
					result.setLoginErrorInfo(
							ErrorFeedback.UPDATE_LOGIN_RESULT_INFO_FEEDBACK_INVALID_LOGIN_USER_EXISTS.getValue());
				} else {
					Optional<User> user = userDao.findByLogin(login);
					if (user.isPresent() && user.get().getId() == userId) {
						boolean updateResult = userDao.updateUserLogin(newLogin, userId);
						if (updateResult) {
							result.setLoginCorrect(true);
						} else {
							result.setLoginCorrect(false);
							result.setLoginErrorInfo(ErrorFeedback.UPDATE_LOGIN_STANDARD_LOGIN_FEEDBACK.getValue());
						}
					} else {
						result.setLoginCorrect(false);
						result.setLoginErrorInfo(
								ErrorFeedback.UPDATE_LOGIN_RESULT_INFO_FEEDBACK_INVALID_AUTHORIZATION_ERROR.getValue());
					}
				}
			} else {
				result.setLoginCorrect(false);
				result.setLoginErrorInfo(ErrorFeedback.UPDATE_LOGIN_RESULT_INFO_FEEDBACK_INVALID_LOGIN.getValue());
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while changing login " + login + " :" + e.getMessage(), e);
		}
	}

	@Override
	public PasswordUpdateResultInfo changePassword(String password, String passwordConfirm, int userId)
			throws ServiceException {
		PasswordUpdateResultInfo result = new PasswordUpdateResultInfo();
		try {
			ValidationService validation = ServiceProvider.getInstance().getValidationService();
			if (validation.validatePassword(password)) {
				result.setPasswordCorrect(true);
			} else {
				result.setPasswordErrorInfo(
						ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORD.getValue());
				result.setPasswordCorrect(false);
			}
			if (validation.validatePassword(passwordConfirm)) {
				result.setPasswordConfirmationCorrect(true);
			} else {
				result.setPasswordErrorInfo(
						ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORD_CONFIRMATION.getValue());
				result.setPasswordConfirmationCorrect(false);
			}
			if (!password.equals(passwordConfirm)) {
				result.setPasswordCorrect(false);
				result.setPasswordConfirmationCorrect(false);
				result.setPasswordErrorInfo(
						ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS.getValue());
				result.setPasswordConfirmationErrorInfo(
						ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS.getValue());
			}
			if (result.isPasswordCorrect() && result.isPasswordConfirmationCorrect()) {
				Optional<User> user = userDao.findById(userId);
				if (user.isPresent() && user.get().getId() == userId) {
					Optional<LoginData> loginData = userDao.findUserLoginData(userId);
					if (loginData.isPresent()) {
						// String passwordHash = loginData.get().getHashedPasswordHex();
						String saltHex = loginData.get().getSaltHex();
						byte[] salt = DatatypeConverter.parseHexBinary(saltHex);
						String hashedPassword = DatatypeConverter.printHexBinary(hashPassword(password, salt));
						boolean passwordUpdateResult = userDao.updateUserPassword(hashedPassword, userId);
						if (passwordUpdateResult) {
							result.setPasswordCorrect(true);
							result.setPasswordConfirmationCorrect(true);
						} else {
							result.setPasswordCorrect(false);
							result.setPasswordConfirmationCorrect(false);
							result.setPasswordErrorInfo(
									ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_NO_USER_WITH_ID
											.getValue());
							result.setPasswordConfirmationErrorInfo(
									ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_NO_USER_WITH_ID
											.getValue());
						}
					} else {
						result.setPasswordCorrect(false);
						result.setPasswordConfirmationCorrect(false);
						result.setPasswordErrorInfo(ErrorFeedback.STANDARD_PASSWORD_FEDDBACK.getValue());
						result.setPasswordConfirmationErrorInfo(ErrorFeedback.STANDARD_PASSWORD_FEDDBACK.getValue());
					}
				} else {
					result.setPasswordCorrect(false);
					result.setPasswordConfirmationCorrect(false);
					result.setPasswordErrorInfo(
							ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_NO_USER_WITH_ID.getValue());
					result.setPasswordConfirmationErrorInfo(
							ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_NO_USER_WITH_ID.getValue());
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by Id " + userId + " :" + e.getMessage(), e);
		}
	}

	@Override
	public ImageUpdateResultInfo updateImage(String serverDeploymentPath, String rootFolder, Part part, int userId)
			throws ServiceException {		
		try (InputStream inputStream1 = part.getInputStream(); InputStream inputStream2 = part.getInputStream();) {
			ImageUpdateResultInfo result = new ImageUpdateResultInfo();
			// TODO image validation
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			String submittedFileName = part.getSubmittedFileName();
			String fileExtension = FilenameUtils.getExtension(submittedFileName);
			if (validationService.validateImageExtension(fileExtension)) {
				result.setImageCorrect(true);
				if (validationService.validateImageSize(part.getSize())) {
					result.setImageCorrect(true);
					
				} else {
					result.setImageCorrect(false);
					result.setImageErrorInfo(ErrorFeedback.UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_SIZE.getValue());
				}
			} else {
				result.setImageCorrect(false);
				result.setImageErrorInfo(ErrorFeedback.UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_EXTENSION.getValue());
			}
			if (result.isImageCorrect()) {
				String newFileName = "user_profile_image_" + userId + "." + fileExtension;
				String realpath = rootFolder + FolderPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
				Path imageRealPath = Paths.get(realpath);
				Path imageServerDeploymentPath = Paths.get(serverDeploymentPath + newFileName);

				long bytes = createFile(inputStream1, imageRealPath);
				if (bytes > 0) {
					String url = FolderPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
					boolean imageUpdateResult = userDao.updateProfileImage(url, userId);
					if (imageUpdateResult) {
						result.setImageCorrect(true);
					} else {
						result.setImageCorrect(false);
						result.setImageErrorInfo(ErrorFeedback.UPDATE_IMAGE_STANDARD_IMAGE_FEEDBACK.getValue());
					}
				}
				bytes = createFile(inputStream2, imageServerDeploymentPath);
			}
			return result;
		} catch (IOException | DaoException e) {
			throw new ServiceException("Error occured when updating image for userId " + userId + " :" + e.getMessage(),
					e);
		}
	}

	private long createFile(InputStream inputStream, Path imagePath) throws ServiceException {
		try {
			Files.deleteIfExists(imagePath);
			imagePath = Files.createFile(imagePath);
			long bytes = Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
			return bytes;
		} catch (IOException e) {
			throw new ServiceException("Error occured when creating file by path: " + imagePath + " :" + e.getMessage(),
					e);
		}
	}

	@Override
	public boolean isUserBanned(HttpSession session) {
		boolean result = false;
		User user = (User) session.getAttribute(RequestAttribute.CURRENT_USER.getValue());
		if (user != null && user.getUserStatus() == User.UserStatus.BANNED) {
			result = true;
		}
		return result;
	}

	@Override
	public AddNewCommentResultInfo addNewComment(int userId, int alienId, String newComment) throws ServiceException {
		AddNewCommentResultInfo result = new AddNewCommentResultInfo();
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			if(validationService.validateComment(newComment)) {
				result.setCommentCorrect(true);
			} else {
				result.setCommentCorrect(false);
				result.setCommentErrorInfo(ErrorFeedback.ADD_NEW_COMMENT_RESULT_INFO_FEEDBACK_INVALID_COMMENT.getValue());
				result.setStatusCode(400);
			}
			if(result.isCommentCorrect()) {
				boolean addResult = userDao.addNewComment(userId, alienId, newComment);
				if(addResult) {
					result.setStatusCode(200);
				} else {
					result.setCommentCorrect(false);
					result.setCommentErrorInfo(ErrorFeedback.ADD_NEW_COMMENT_STANDARD_COMMENT_FEEDBACK.getValue());
					result.setStatusCode(500);
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while adding comment for user " + userId + " :" + e.getMessage(),
					e);
		}
	}

	@Override
	public boolean deleteComment(int commentId) throws ServiceException {
		boolean result = false;
		try {
			result = userDao.deleteComment(commentId);
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while deleting comment " + commentId + " :" + e.getMessage(), e);
		}
	}
	
	@Override
	public RequestRestorePasswordTokenResultInfo requestRestorePasswordToken(String email) throws ServiceException {
		// проверить email
		// если ок, то создать новый токен
			// если ок, то отправить email
		// если не ок, то засетить инфу
//		try {
//			RequestRestorePasswordTokenResultInfo result = new RequestRestorePasswordTokenResultInfo();
//			ValidationService validationSerivce = ServiceProvider.getInstance().getValidationService();
//			if(validationSerivce.validateEmail(email)) {
//				result.setEmailCorrect(true);
//			} else {
//				result.setEmailCorrect(false);
//				result.setEmailErrorInfo(ErrorFeedback.REQUEST_RESTORE_PASSWORD_TOKEN_RESULT_INFO_FEEDBACK_INVALID_EMAIL.getValue());
//			}
//			
//			if (result.isEmailCorrect()) {
//				Optional<User> userOptional = userDao.findByEmail(email);
//				if(userOptional.isPresent()) {
//					// generate token
//					final String randomString = "jfi2j204uff4u4fhkhHJ@#NM<Boff904uf";
//					String token = DatatypeConverter.printHexBinary(hashPassword(randomString, email.getBytes()));
//					
//					TimeService timeService = ServiceProvider.getInstance().getTimeService();
//					final int tokenExpirationTime = 5;
//					String expirationDate = timeService.prepareTokenExpirationDate(tokenExpirationTime);
//					
//					boolean addTokenResult = userDao.addNewToken(email, token, expirationDate);
//					if (addTokenResult) {
//						boolean sendMessageResult = sendMessage(email, token);
//						if(sendMessageResult) {
//							result.setEmailCorrect(true);
//						} else {
//							result.setEmailCorrect(false);
//							result.setEmailErrorInfo(ErrorFeedback.REQUEST_RESTORE_PASSWORD_TOKEN_RESULT_INFO_FEEDBACK_SERVER_MESSAGE_ERROR.getValue());
//						}
//					} else {
//						result.setEmailCorrect(false);
//						result.setEmailErrorInfo(ErrorFeedback.REQUEST_RESTORE_PASSWORD_TOKEN_RESULT_INFO_FEEDBACK_SERVER_TOKEN_ERROR.getValue());
//					}
//				} else {
//					result.setEmailCorrect(false);
//					result.setEmailErrorInfo(ErrorFeedback.NO_USER_WITH_EMAIL.getValue());
//				}
//			}
//			return result;
//		} catch (DaoException e) {
//			throw new ServiceException("Error occured while requesting token for " + email + " :" + e.getMessage(), e);
//		}
		throw new UnsupportedOperationException(); // TODO todo if you'll have time
	}

	private byte[] hashPassword(String password, byte[] salt) throws ServiceException {
		// do not change this final variables
		final int iterations = 65536;
		final int size = 32 * 8;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, size);
		final String algorithmName = "PBKDF2WithHmacSHA1";
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return hash;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error occured while instantiating SecretKeyFactory with algorithm "
					+ algorithmName + " :" + e.getMessage(), e);
		}
	}

	private byte[] createSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;
	}



}

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
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.xml.bind.DatatypeConverter;

public class UserServiceImpl implements UserService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final UserDao userDao = daoProvider.getUserDao();
	
	@Override
	public boolean userLogIn(String email, String password) throws ServiceException {
		boolean result = false;
		try {
			Optional<LoginData> loginData = userDao.findUserLoginData(email);
			if (loginData.isPresent()) {
				String passwordHash = loginData.get().getHashedPasswordHex();
				String saltHex = loginData.get().getSaltHex();
				byte[] salt = DatatypeConverter.parseHexBinary(saltHex);
				String enteredPassword = DatatypeConverter.printHexBinary(hashPassword(password, salt));
				if (enteredPassword.equals(passwordHash)) {
					result = true;
				}
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
			throw new ServiceException("Error occured when finding user by email " + email + " :"+ e.getMessage(), e);
		}
	}

	// TODO do something with dao register
	@Override
	public boolean registerUser(String email, String login, String password, String passwordRepeat, String imagePath, Role role)
			throws ServiceException {
		boolean result = false;
		try {
			if (password.equals(passwordRepeat)) {
				byte[] salt = createSalt();
				byte[] hashedPassword = hashPassword(password, salt);
				String saltHex = DatatypeConverter.printHexBinary(salt);
				String hashedPasswordHex = DatatypeConverter.printHexBinary(hashedPassword);
				result = userDao.registerUser(email, login, hashedPasswordHex, saltHex, IMAGE_DEFAULT.getValue(), Role.USER);
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
			throw new ServiceException("Error occured when finding user by login " + login + " :"+ e.getMessage(), e);
		}
	}

	@Override
	public boolean changeEmail(String email, String newEmail, int userId) throws ServiceException {
		boolean result = false;
		try {
			Optional<User> user = userDao.findByEmail(email);
			if (user.isPresent() && user.get().getId() == userId) {
				result = userDao.updateUserEmail(newEmail, userId);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while changing email " + email + " :"+ e.getMessage(), e);
		}
	}

	@Override
	public boolean changeLogin(String login, String newLogin, int userId) throws ServiceException {
		boolean result = false;
		try {
			Optional<User> user = userDao.findByLogin(login);
			if (user.isPresent()) {
				result = userDao.updateUserLogin(newLogin, userId);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while changing login " + login + " :"+ e.getMessage(), e);
		}
	}

	@Override
	public boolean changePassword(String password, String passwordConfirm, int userId) throws ServiceException {
		boolean result = false;
		try {
			Optional<User> user = userDao.findById(userId);
			if (user.isPresent()) {
				String hashedPassword = password; // hash here
				result = userDao.updateUserPassword(hashedPassword, userId);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when finding user by Id " + userId + " :"+ e.getMessage(), e);
		}
	}

	@Override
	public boolean updateImage(String serverDeploymentPath, String rootFolder, Part part, int userId) throws ServiceException {
		boolean result = false;
		try (InputStream inputStream1 = part.getInputStream(); InputStream inputStream2 = part.getInputStream();){
			String submittedFileName = part.getSubmittedFileName();
			// TODO error will occured if file_name with wrong type, need validation
			String fileExtension = FilenameUtils.getExtension(submittedFileName);
			String newFileName = "user_profile_image_" +  userId + "." + fileExtension;
			String realpath = rootFolder + FolderPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
			Path imageRealPath = Paths.get(realpath);
			Path imageServerDeploymentPath = Paths.get(serverDeploymentPath + newFileName);
			
			long bytes = createFile(inputStream1, imageRealPath);
			if (bytes > 0) {
				String url = FolderPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
				result = userDao.updateProfileImage(url, userId);
			}
			bytes = createFile(inputStream2, imageServerDeploymentPath);
		} catch (IOException | DaoException e) {
			throw new ServiceException("Error occured when updating image for userId " + userId + " :"+ e.getMessage(), e);
		}
		return result;
	}

	private long createFile(InputStream inputStream, Path imagePath) throws ServiceException {
		try {
			Files.deleteIfExists(imagePath);
			imagePath = Files.createFile(imagePath);
			long bytes = Files.copy(
					  inputStream, 
					  imagePath, 
				      StandardCopyOption.REPLACE_EXISTING);
			return bytes;
		} catch (IOException e) {
			throw new ServiceException("Error occured when creating file by path: " + imagePath + " :"+ e.getMessage(), e);
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
	public boolean addNewComment(int userId, int alienId, String newComment) throws ServiceException {
		boolean result = false;
		try {
			// Optional<User> user = userDao.findByEmail(email);
//			if (user.isPresent() && user.get().getId() == userId) {
//				result = userDao.updateUserEmail(newEmail, userId);
//			}
			result = userDao.addNewComment(userId, alienId, newComment);
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while adding comment for user " + userId + " :"+ e.getMessage(), e);
		}
	}

	@Override
	public boolean deleteComment(int commentId) throws ServiceException {
		boolean result = false;
		try {
			result = userDao.deleteComment(commentId);
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while deleting comment " + commentId + " :"+ e.getMessage(), e);
		}
	}
	
	public byte[] hashPassword(String password, byte[] salt) throws ServiceException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 32*8);
		final String algorithmName = "PBKDF2WithHmacSHA1";
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return hash;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error occured while instantiating SecretKeyFactory with algorithm " + algorithmName + " :"+ e.getMessage(), e);
		}
	}
	
	public byte[] createSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;
	}

}

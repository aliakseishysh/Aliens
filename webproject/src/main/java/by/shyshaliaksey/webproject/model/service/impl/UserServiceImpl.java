package by.shyshaliaksey.webproject.model.service.impl;

import static by.shyshaliaksey.webproject.controller.command.FilePath.IMAGE_DEFAULT;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.apache.tomcat.jakartaee.commons.compress.utils.FileNameUtils;

import by.shyshaliaksey.webproject.controller.command.FolderPath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.Part;

public class UserServiceImpl implements UserService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final UserDao userDao = daoProvider.getUserDao();
	
	@Override
	public boolean userLogIn(String email, String password) throws ServiceException {
		try {
			boolean result = userDao.loginUser(email, password);
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
	public boolean registerUser(String email, String login, String password, String imagePath, Role role)
			throws ServiceException {
		boolean result;
		try {
			result = userDao.registerUser(email, login, password, IMAGE_DEFAULT.getValue(), Role.USER);
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
			String fileExtension = FileNameUtils.getExtension(submittedFileName);
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
			long bytes = java.nio.file.Files.copy(
					  inputStream, 
					  imagePath, 
				      StandardCopyOption.REPLACE_EXISTING);
			return bytes;
		} catch (IOException e) {
			throw new ServiceException("Error occured when creating file by path: " + imagePath + " :"+ e.getMessage(), e);
		}
	}

}

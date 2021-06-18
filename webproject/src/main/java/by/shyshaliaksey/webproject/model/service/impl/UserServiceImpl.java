package by.shyshaliaksey.webproject.model.service.impl;

import static by.shyshaliaksey.webproject.controller.command.FilePath.IMAGE_DEFAULT;

import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.UserService;

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
			throw new ServiceException("Error occured when finding user by email " + email + " :"+ e.getMessage(), e);
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
			throw new ServiceException("Error occured when finding user by login " + login + " :"+ e.getMessage(), e);
		}
	}

	

}

package by.shyshaliaksey.webproject.model.service.impl;

import static by.shyshaliaksey.webproject.controller.command.FilePath.DEFAULT_IMAGE;

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
			result = userDao.registerUser(email, login, password, DEFAULT_IMAGE, Role.USER);
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

	

}

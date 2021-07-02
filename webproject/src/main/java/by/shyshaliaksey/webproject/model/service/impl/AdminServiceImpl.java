package by.shyshaliaksey.webproject.model.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.TimeService;
import jakarta.servlet.http.Part;

public class AdminServiceImpl implements AdminService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final UserDao userDao = daoProvider.getUserDao();
	private static final AlienDao alienDao = daoProvider.getAlienDao();

	@Override
	public boolean banUser(String userLogin, int daysToBan) throws ServiceException {
		boolean result = false;
		try {
			Optional<User> user = userDao.findByLogin(userLogin);
			if (user.isPresent()) {
				ServiceProvider serviceProvider = ServiceProvider.getInstance();
				TimeService timeService = serviceProvider.getTimeService();
				String banDate = timeService.prepareBanDate(daysToBan);
				result = userDao.banUser(userLogin, banDate);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when banning user " + userLogin + " :" + e.getMessage(), e);
		}
	}

	@Override
	public boolean unbanUser(String userLogin) throws ServiceException {
		boolean result = false;
		try {
			Optional<User> user = userDao.findByLogin(userLogin);
			if (user.isPresent()) {
				ServiceProvider serviceProvider = ServiceProvider.getInstance();
				TimeService timeService = serviceProvider.getTimeService();
				String banDate = timeService.prepareBanDate(0);
				result = userDao.unbanUser(userLogin, banDate);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when unbanning user " + userLogin + " :" + e.getMessage(), e);
		}
	}

	@Override
	public boolean promoteUser(String userLogin, String currentUserLogin) throws ServiceException {
		boolean result = false;
		try {
			if (!userLogin.equals(currentUserLogin)) {
				Optional<User> user = userDao.findByLogin(userLogin);
				if (user.isPresent() && user.get().getRole() == Role.USER) {
					result = userDao.promoteUser(userLogin);
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when promoting user " + userLogin + " :" + e.getMessage(), e);
		}
	}

	@Override
	public boolean demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException {
		boolean result = false;
		try {
			if (!adminLogin.equals(currentAdminLogin)) {
				Optional<User> user = userDao.findByLogin(adminLogin);
				if (user.isPresent() && user.get().getRole() == Role.ADMIN) {
					result = userDao.demoteAdmin(adminLogin);
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when demoting " + adminLogin + " :" + e.getMessage(), e);
		}
	}

}

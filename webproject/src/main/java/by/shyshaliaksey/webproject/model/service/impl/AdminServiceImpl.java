package by.shyshaliaksey.webproject.model.service.impl;

import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.TimeService;

public class AdminServiceImpl implements AdminService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final UserDao userDao = daoProvider.getUserDao();
	
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
			throw new ServiceException("Error occured when finding user banning user " + userLogin + " :"+ e.getMessage(), e);
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
			throw new ServiceException("Error occured when unbanning user " + userLogin + " :"+ e.getMessage(), e);
		}
	}




}

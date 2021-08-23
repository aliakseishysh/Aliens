package by.shyshaliaksey.aliens.model.service.impl;

import by.shyshaliaksey.aliens.exception.DaoException;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.dao.DaoProvider;
import by.shyshaliaksey.aliens.model.dao.RatingDao;
import by.shyshaliaksey.aliens.model.service.RatingService;

public class RatingServiceImpl implements RatingService {

	private static final RatingDao ratingDao = DaoProvider.getInstance().getRatingDao();
	
	@Override
	public int findUserRate(int alienId, int userId) throws ServiceException {
		try {
			return ratingDao.findUserRate(alienId, userId);
		} catch (DaoException e) {
			throw new ServiceException("Can not find user rate: " + e.getMessage(), e);
		}
	}

	@Override
	public void updateRate(int alienId, int userId, int rateValue) throws ServiceException {
		try {
			ratingDao.updateRate(alienId, userId, rateValue);
		} catch (DaoException e) {
			throw new ServiceException("Can not update user rate: " + e.getMessage(), e);
		}
		
	}

	@Override
	public void addRate(int alienId, int userId, int rateValue) throws ServiceException {
		try {
			ratingDao.addRate(alienId, userId, rateValue);
		} catch (DaoException e) {
			throw new ServiceException("Can not add user rate: " + e.getMessage(), e);
		}
		
	}

	@Override
	public Double calculateAverageRate(int alienId) throws ServiceException {
		try {
			return ratingDao.calculateAverageRating(alienId);
		} catch (DaoException e) {
			throw new ServiceException("Can not calculate average rate: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean checkRateExistence(int alienId, int userId) throws ServiceException {
		try {
			return ratingDao.checkRateExistence(alienId, userId);
		} catch (DaoException e) {
			throw new ServiceException("Can not check rate existence: " + e.getMessage(), e);
		}
	}
	
}

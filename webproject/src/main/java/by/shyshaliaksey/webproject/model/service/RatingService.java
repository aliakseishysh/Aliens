package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.exception.ServiceException;

public interface RatingService {

	int findUserRate(int alienId, int userId) throws ServiceException;

	void updateRate(int alienId, int userId, int rateValue) throws ServiceException;

	void addRate(int alienId, int userId, int rateValue) throws ServiceException;

	Double calculateAverageRate(int alienId) throws ServiceException;

	boolean checkRateExistence(int alienId, int userId) throws ServiceException;

}

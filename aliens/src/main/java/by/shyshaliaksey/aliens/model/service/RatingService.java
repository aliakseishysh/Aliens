package by.shyshaliaksey.aliens.model.service;

import by.shyshaliaksey.aliens.exception.ServiceException;

public interface RatingService {

	/**
	 * Finds user rate for specified alien.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @param userId  {@code int} user id obtained from current session
	 * @return {@code int} user rate
	 * @throws ServiceException if can't find user rate
	 */
	int findUserRate(int alienId, int userId) throws ServiceException;

	/**
	 * Updates user rate for specified alien.
	 * 
	 * @param alienId   {@code int} alien id obtained from request
	 * @param userId    {@code int} user id obtained from current session
	 * @param rateValue {@code int} user rate
	 * @throws ServiceException if can't update user rate
	 */
	void updateRate(int alienId, int userId, int rateValue) throws ServiceException;

	/**
	 * Adds rate for specified alien from user.
	 * 
	 * @param alienId   {@code int} alien id obtained from request
	 * @param userId    {@code int} user id obtained from current session
	 * @param rateValue {@code int} user rate
	 * @throws ServiceException if can't add user rate
	 */
	void addRate(int alienId, int userId, int rateValue) throws ServiceException;

	/**
	 * Calculates average alien rate.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @return {@code Double} alien average rate
	 * @throws ServiceException if can't calculate average alien rate
	 */
	Double calculateAverageRate(int alienId) throws ServiceException;

	/**
	 * Checks if user rate for specified alien exists.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @param userId  {@code int} user id obtained from current session
	 * @return {@code true} if rate exists, {@code false} otherwise
	 * @throws ServiceException if rating service exception occurred
	 */
	boolean checkRateExistence(int alienId, int userId) throws ServiceException;

}

package by.shyshaliaksey.webproject.model.dao;

import by.shyshaliaksey.webproject.exception.DaoException;

public interface RatingDao {

	/**
	 * Adds user rate for alien
	 * 
	 * @param alienId   {@code int} id of alien
	 * @param userId    {@code int} id of user
	 * @param rateValue {@code int} rate value
	 * @throws DaoException
	 */
	void addRate(int alienId, int userId, int rateValue) throws DaoException;

	/**
	 * Calculates average rating of specified alien
	 * 
	 * @param alienId id of alien
	 * @return {@code double} calculated average rate of alien
	 * @throws DaoException
	 */
	double calculateAverageRating(int alienId) throws DaoException;

	/**
	 * Updates user rate for specified alien
	 * 
	 * @param alienId   {@code int} id of alien
	 * @param userId    {@code int} id of user
	 * @param rateValue {@code int} rate value
	 * @throws DaoException
	 */
	void updateRate(int alienId, int userId, int rateValue) throws DaoException;

	/**
	 * Checks if rate exist for specified alien id and user id
	 * 
	 * @param alienId {@code int} id of alien
	 * @param userId  {@code int} id of user
	 * @return true if count of rates equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean checkRateExistence(int alienId, int userId) throws DaoException;

	/**
	 * Finds user rate for specified alien id
	 * 
	 * @param alienId {@code int} id of alien
	 * @param userId  {@code int} id of user
	 * @return user rate if true, <b>-1</b> otherwise
	 * @throws DaoException
	 */
	int findUserRate(int alienId, int userId) throws DaoException;

}

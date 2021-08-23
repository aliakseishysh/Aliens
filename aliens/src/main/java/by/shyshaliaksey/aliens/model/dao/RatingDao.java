package by.shyshaliaksey.aliens.model.dao;

import by.shyshaliaksey.aliens.exception.DaoException;

public interface RatingDao {

	/**
	 * Adds user rate for alien
	 * 
	 * @param alienId   {@code int} id of alien
	 * @param userId    {@code int} id of user
	 * @param rateValue {@code int} rate value
	 * @throws DaoException if rating dao exception occurred
	 */
	void addRate(int alienId, int userId, int rateValue) throws DaoException;

	/**
	 * Calculates average rating of specified alien
	 * 
	 * @param alienId id of alien
	 * @return {@code double} calculated average rate of alien
	 * @throws DaoException if rating dao exception occurred
	 */
	double calculateAverageRating(int alienId) throws DaoException;

	/**
	 * Updates user rate for specified alien
	 * 
	 * @param alienId   {@code int} id of alien
	 * @param userId    {@code int} id of user
	 * @param rateValue {@code int} rate value
	 * @throws DaoException if rating dao exception occurred
	 */
	void updateRate(int alienId, int userId, int rateValue) throws DaoException;

	/**
	 * Checks if rate exist for specified alien id and user id
	 * 
	 * @param alienId {@code int} id of alien
	 * @param userId  {@code int} id of user
	 * @return true if count of rates equals 1, false otherwise
	 * @throws DaoException if rating dao exception occurred
	 */
	boolean checkRateExistence(int alienId, int userId) throws DaoException;

	/**
	 * Finds user rate for specified alien id
	 * 
	 * @param alienId {@code int} id of alien
	 * @param userId  {@code int} id of user
	 * @return user rate if true, <b>-1</b> otherwise
	 * @throws DaoException if rating dao exception occurred
	 */
	int findUserRate(int alienId, int userId) throws DaoException;

}

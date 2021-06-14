package by.shyshaliaksey.webproject.model.dao;

import by.shyshaliaksey.webproject.exception.DaoException;

public interface RatingDao {

	void addRate(int alienId, int userId, int rateValue) throws DaoException;
	double calculateAverageRating(int alienId) throws DaoException;
	void updateRate(int alienId, int userId, int rateValue) throws DaoException;
	boolean checkRateExistence(int alienId, int userId) throws DaoException;
	int findUserRate(int alienId, int userId) throws DaoException;
	
}

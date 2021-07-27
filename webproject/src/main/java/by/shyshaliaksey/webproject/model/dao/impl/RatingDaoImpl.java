package by.shyshaliaksey.webproject.model.dao.impl;

import static by.shyshaliaksey.webproject.model.dao.ColumnName.RATE_VALUE;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.AVERAGE_RATE;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.RATES_COUNT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.RatingDao;

/**
 * Implementer of {@link RatingDao} designed for operations with rating
 * 
 * @author Aliaksey Shysh
 *
 */
public class RatingDaoImpl implements RatingDao {

	private static final Logger logger = LogManager.getRootLogger();
	private static final RatingDaoImpl instance = new RatingDaoImpl();
	private static final String ADD_RATE = """
			INSERT INTO ratings 
			(alien_id, user_id, rate_value) 
			VALUES (?, ?, ?)
			""";
	private static final String UPDATE_RATE = """
			UPDATE ratings 
			SET rate_value = ? 
			WHERE alien_id = ? AND user_id = ?
			""";
	private static final String AVERAGE_RATING = """
			SELECT AVG(rate_value) AS averageRate 
			FROM ratings 
			WHERE alien_id=?
			""";
	private static final String CHECK_RATE_EXISTENCE = """
			SELECT COUNT(*) as ratesCount 
			FROM ratings 
			WHERE alien_id=? AND user_id=?
			""";
	private static final String FIND_USER_RATE = """
			SELECT rate_value 
			FROM ratings 
			WHERE alien_id=? AND user_id=?
			""";

	private RatingDaoImpl() {
	}

	public static RatingDaoImpl getInstance() {
		return instance;
	}

	@Override
	public void addRate(int alienId, int userId, int rateValue) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_RATE)) {
			statement.setInt(1, alienId);
			statement.setInt(2, userId);
			statement.setInt(3, rateValue);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_RATE, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_RATE, e);
		}
	}

	@Override
	public double calculateAverageRating(int alienId) throws DaoException {
		double averageRate = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(AVERAGE_RATING)) {
			statement.setInt(1, alienId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				averageRate = resultSet.getDouble(AVERAGE_RATE);
			}
			return averageRate;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", AVERAGE_RATING, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + AVERAGE_RATING, e);
		}
	}

	@Override
	public void updateRate(int alienId, int userId, int rateValue) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RATE)) {
			statement.setInt(1, rateValue);
			statement.setInt(2, alienId);
			statement.setInt(3, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_RATE, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_RATE, e);
		}
	}

	@Override
	public boolean checkRateExistence(int alienId, int userId) throws DaoException {
		int rateCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_RATE_EXISTENCE)) {
			statement.setInt(1, alienId);
			statement.setInt(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				rateCount = resultSet.getInt(RATES_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", CHECK_RATE_EXISTENCE, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + CHECK_RATE_EXISTENCE, e);
		}
		return rateCount == 1;
	}

	@Override
	public int findUserRate(int alienId, int userId) throws DaoException {
		int userRate = -1;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_RATE)) {
			statement.setInt(1, alienId);
			statement.setInt(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userRate = resultSet.getInt(RATE_VALUE);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_USER_RATE, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_USER_RATE, e);
		}
		return userRate;
	}

}

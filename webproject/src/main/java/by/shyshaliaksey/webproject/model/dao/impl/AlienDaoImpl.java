package by.shyshaliaksey.webproject.model.dao.impl;

import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_ID;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_NAME;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_DESCRIPTION_SMALL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_DESCRIPTION_FULL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_IMAGE_URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.entity.Alien;

public class AlienDaoImpl implements AlienDao {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AlienDaoImpl instance = new AlienDaoImpl();
	private static final String SPACE = " ";
	private static final String FIND_ALL = "SELECT * FROM aliens";
	private static final String FIND_BY_ID = String.join(SPACE, FIND_ALL, "WHERE alien_id=?");
	private static final String FIND_BY_NAME = String.join(SPACE, FIND_ALL, "WHERE _name=?");
	
	public static AlienDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Alien> findAll() throws DaoException {
		List<Alien> aliens = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while (resultSet.next()) {
				int alienId = resultSet.getInt(ALIEN_ID);
				String name = resultSet.getString(ALIEN_NAME);
				String descriptionSmall = resultSet.getString(ALIEN_DESCRIPTION_SMALL);
				String descriptionBig = resultSet.getString(ALIEN_DESCRIPTION_FULL);
				String imagePath = resultSet.getString(ALIEN_IMAGE_URL);
				Alien alien = new Alien(alienId, name, descriptionSmall, descriptionBig, imagePath);
				aliens.add(alien);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_ALL, e);
		}
		return aliens;
	}

	@Override
	public Optional<Alien> findById(int alienId) throws DaoException {
		Optional<Alien> alien = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, alienId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(ALIEN_NAME);
				String descriptionSmall = resultSet.getString(ALIEN_DESCRIPTION_SMALL);
				String descriptionBig = resultSet.getString(ALIEN_DESCRIPTION_FULL);
				String imagePath = resultSet.getString(ALIEN_IMAGE_URL);
				alien = Optional.of(new Alien(alienId, name, descriptionSmall, descriptionBig, imagePath));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_ID, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
		}
		return alien;
	}

	@Override
	public Optional<Alien> findByName(String alienName) throws DaoException {
		Optional<Alien> alien = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME)) {
			statement.setString(1, alienName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int alienId = resultSet.getInt(ALIEN_ID);
				String name = resultSet.getString(ALIEN_NAME);
				String descriptionSmall = resultSet.getString(ALIEN_DESCRIPTION_SMALL);
				String descriptionBig = resultSet.getString(ALIEN_DESCRIPTION_FULL);
				String imagePath = resultSet.getString(ALIEN_IMAGE_URL);
				alien = Optional.of(new Alien(alienId, name, descriptionSmall, descriptionBig, imagePath));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_ID, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
		}
		return alien;
	}

	@Override
	public boolean addAlien(Alien alien) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAlien(int alienId) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}

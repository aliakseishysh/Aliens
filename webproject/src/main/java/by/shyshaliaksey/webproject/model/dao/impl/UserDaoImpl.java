package by.shyshaliaksey.webproject.model.dao.impl;

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
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.UserFactory;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getRootLogger();

	private static final UserDaoImpl instance = new UserDaoImpl();

	private static final String SPACE = " ";

	private static final String FIND_ALL = "SELECT users.user_id, users.email, users.login_name, users.image_url, roles._name FROM users INNER JOIN roles ON users.role_id = roles.role_id";

	private static final String FIND_BY_ID = String.join(SPACE, FIND_ALL, "WHERE users.user_id=?");

	private static final String FIND_BY_LOGIN = String.join(SPACE, FIND_ALL, "WHERE users.login_name=?");

	private static final String FIND_BY_EMAIL = String.join(SPACE, FIND_ALL, "WHERE users.email=?");

	private static final String FIND_LOGIN_BY_EMAIL = "SELECT login_name FROM users WHERE email=?";

	private static final String LOGIN_AND_PASSWORD_CHECK = "SELECT count(*) as usersCount FROM users WHERE email=? AND password_hash=?";

	private static final String REGISTER = "INSERT INTO users (email, login_name, password_hash, image_url, role_id) VALUES (?, ?, ?, ?, ?)";

	public static UserDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<AbstractUser> findAll() throws DaoException {
		List<AbstractUser> users = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getFreeConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String email = resultSet.getString(2);
				String loginName = resultSet.getString(3);
				String imageUrl = resultSet.getString(4);
				Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
				AbstractUser user = UserFactory.getInstance(id, email, loginName, imageUrl, role);
				users.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_ALL, e);
		}
		return users;
	}

	@Override
	public Optional<AbstractUser> findById(int userId) throws DaoException {
		Optional<AbstractUser> user = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getFreeConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String email = resultSet.getString(2);
				String loginName = resultSet.getString(3);
				String imageUrl = resultSet.getString(4);
				Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
				user = Optional.of(UserFactory.getInstance(userId, email, loginName, imageUrl, role));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_ID, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
		}
		return user;
	}

	@Override
	public Optional<AbstractUser> findByLogin(String userLogin) throws DaoException {
		Optional<AbstractUser> user = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getFreeConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
			statement.setString(1, userLogin);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt(1);
				String email = resultSet.getString(2);
				String loginName = resultSet.getString(3);
				String imageUrl = resultSet.getString(4);
				Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
				user = Optional.of(UserFactory.getInstance(userId, email, loginName, imageUrl, role));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_LOGIN, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_LOGIN, e);
		}
		return user;
	}

	@Override
	public Optional<AbstractUser> findByEmail(String email) throws DaoException {
		Optional<AbstractUser> user = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getFreeConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt(1);
				String loginName = resultSet.getString(3);
				String imageUrl = resultSet.getString(4);
				Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
				user = Optional.of(UserFactory.getInstance(userId, email, loginName, imageUrl, role));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_EMAIL, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_EMAIL, e);
		}
		return user;
	}

	@Override
	public boolean registerUser(String email, String login, String passwordHash, String imagePath, Role role)
			throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getFreeConnection();
				PreparedStatement statement = connection.prepareStatement(REGISTER)) {
			statement.setString(1, email);
			statement.setString(2, login);
			statement.setString(3, passwordHash);
			statement.setString(4, imagePath);
			statement.setInt(5, role.ordinal() + 1);
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", REGISTER, e.getMessage());
			throw new DaoException("Can not proceed request: " + REGISTER, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean loginUser(String email, String passwordHash) throws DaoException {
		int usersCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getFreeConnection();
				PreparedStatement statement = connection.prepareStatement(LOGIN_AND_PASSWORD_CHECK)) {
			statement.setString(1, email);
			statement.setString(2, passwordHash);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usersCount = resultSet.getInt("usersCount");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", LOGIN_AND_PASSWORD_CHECK, e.getMessage());
			throw new DaoException("Can not proceed request: " + LOGIN_AND_PASSWORD_CHECK, e);
		}
		return usersCount == 1;
	}

}

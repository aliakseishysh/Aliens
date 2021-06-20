package by.shyshaliaksey.webproject.model.dao.impl;

import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_ID;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_EMAIL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_LOGIN_NAME;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_PASSWORD_HASH;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_IMAGE_URL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_ROLE_TYPE;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USERS_COUNT;
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
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.UserStatus;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.TimeService;
import by.shyshaliaksey.webproject.model.entity.Role;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getRootLogger();
	private static final UserDaoImpl instance = new UserDaoImpl();
	private static final String SPACE = " ";
	private static final String FIND_ALL = "SELECT user_id, email, login_name, image_url, role_type, _status FROM users";
	private static final String FIND_BY_ID = String.join(SPACE, FIND_ALL, "WHERE users.user_id=?");
	private static final String FIND_BY_LOGIN = String.join(SPACE, FIND_ALL, "WHERE users.login_name=?");
	private static final String FIND_BY_EMAIL = String.join(SPACE, FIND_ALL, "WHERE users.email=?");
	private static final String LOGIN_AND_PASSWORD_CHECK = "SELECT count(*) as usersCount FROM users WHERE email=? AND password_hash=?";
	private static final String REGISTER = "INSERT INTO users (email, login_name, password_hash, image_url, role_type, _status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_EMAIL = "UPDATE users SET email = ? WHERE user_id = ?";
	private static final String UPDATE_LOGIN = "UPDATE users SET login_name = ? WHERE user_id = ?";
	private static final String UPDATE_PASSWORD = "UPDATE users SET password_hash = ? WHERE user_id = ?";
	private static final String UPDATE_PROFILE_IMAGE = "UPDATE users SET image_url = ? WHERE user_id = ?";
	private static final String BAN_USER = "UPDATE users SET _status = ?, banned_to_datetime = ? WHERE login_name = ?";
	private static final String UNBAN_USER = "UPDATE users SET _status = ?, banned_to_datetime = ? WHERE login_name = ?";
	
	public static UserDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<User> findAll() throws DaoException {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while (resultSet.next()) {
				int id = resultSet.getInt(USER_ID);
				String email = resultSet.getString(USER_EMAIL);
				String loginName = resultSet.getString(USER_LOGIN_NAME);
				String imageUrl = resultSet.getString(USER_IMAGE_URL);
				Role role = Role.valueOf(resultSet.getString(USER_ROLE_TYPE));
				User user = new User(id, email, loginName, imageUrl, role);
				users.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_ALL, e);
		}
		return users;
	}

	@Override
	public Optional<User> findById(int userId) throws DaoException {
		Optional<User> user = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String email = resultSet.getString(USER_EMAIL);
				String loginName = resultSet.getString(USER_LOGIN_NAME);
				String imageUrl = resultSet.getString(USER_IMAGE_URL);
				Role role = Role.valueOf(resultSet.getString(USER_ROLE_TYPE));
				user = Optional.of(new User(userId, email, loginName, imageUrl, role));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_ID, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
		}
		return user;
	}

	@Override
	public Optional<User> findByLogin(String userLogin) throws DaoException {
		Optional<User> user = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
			statement.setString(1, userLogin);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt(USER_ID);
				String email = resultSet.getString(USER_EMAIL);
				String loginName = resultSet.getString(USER_LOGIN_NAME);
				String imageUrl = resultSet.getString(USER_IMAGE_URL);
				Role role = Role.valueOf(resultSet.getString(USER_ROLE_TYPE));
				user = Optional.of(new User(userId, email, loginName, imageUrl, role));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_BY_LOGIN, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_BY_LOGIN, e);
		}
		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) throws DaoException {
		Optional<User> user = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt(USER_ID);
				String loginName = resultSet.getString(USER_LOGIN_NAME);
				String imageUrl = resultSet.getString(USER_IMAGE_URL);
				Role role = Role.valueOf(resultSet.getString(USER_ROLE_TYPE));
				user = Optional.of(new User(userId, email, loginName, imageUrl, role));
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
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(REGISTER)) {
			statement.setString(1, email);
			statement.setString(2, login);
			statement.setString(3, passwordHash);
			statement.setString(4, imagePath);
			statement.setString(5, role.getValue());
			statement.setString(6, UserStatus.NORMAL.getValue());
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
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(LOGIN_AND_PASSWORD_CHECK)) {
			statement.setString(1, email);
			statement.setString(2, passwordHash);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usersCount = resultSet.getInt(USERS_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", LOGIN_AND_PASSWORD_CHECK, e.getMessage());
			throw new DaoException("Can not proceed request: " + LOGIN_AND_PASSWORD_CHECK, e);
		}
		return usersCount == 1;
	}

	@Override
	public boolean updateUserEmail(String email, int userId) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL)) {
			statement.setString(1, email);
			statement.setInt(2, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", UPDATE_EMAIL, e.getMessage());
			throw new DaoException("Can not proceed request: " + UPDATE_EMAIL, e);
		}
		return result == 1;
	}

	@Override
	public boolean updateUserLogin(String login, int userId) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LOGIN)) {
			statement.setString(1, login);
			statement.setInt(2, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", UPDATE_LOGIN, e.getMessage());
			throw new DaoException("Can not proceed request: " + UPDATE_LOGIN, e);
		}
		return result == 1;
	}

	@Override
	public boolean updateUserPassword(String hashedPassword, int userId) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
			statement.setString(1, hashedPassword);
			statement.setInt(2, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", UPDATE_PASSWORD, e.getMessage());
			throw new DaoException("Can not proceed request: " + UPDATE_PASSWORD, e);
		}
		return result == 1;
	}

	@Override
	public boolean updateProfileImage(String newFileName, int userId) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PROFILE_IMAGE)) {
			statement.setString(1, newFileName);
			statement.setInt(2, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", UPDATE_PROFILE_IMAGE, e.getMessage());
			throw new DaoException("Can not proceed request: " + UPDATE_PROFILE_IMAGE, e);
		}
		return result == 1;
	}

	@Override
	public boolean banUser(String userLogin, String banDate) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(BAN_USER)) {
			statement.setString(1, UserStatus.BANNED.getValue());
			statement.setString(2, banDate);
			statement.setString(3, userLogin);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", BAN_USER, e.getMessage());
			throw new DaoException("Can not proceed request: " + BAN_USER, e);
		}
		return result == 1;
	}

	@Override
	public boolean unbanUser(String userLogin, String unbanDate) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(BAN_USER)) {
			statement.setString(1, UserStatus.NORMAL.getValue());
			statement.setString(2, unbanDate);
			statement.setString(3, userLogin);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", BAN_USER, e.getMessage());
			throw new DaoException("Can not proceed request: " + BAN_USER, e);
		}
		return result == 1;
	}
	
	

}

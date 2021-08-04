package by.shyshaliaksey.aliens.model.dao.impl;

import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_ID;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_EMAIL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_LOGIN_NAME;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_STATUS;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_SALT;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_PASSWORD_HASH;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_BANNED_TO_DATE;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_IMAGE_URL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_ROLE_TYPE;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.TOKEN_ID;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.TOKEN_EMAIL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.TOKEN;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.TOKEN_EXPIRATION;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.TOKEN_NEW_EMAIL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.aliens.model.dao.DatabaseFeedback;
import by.shyshaliaksey.aliens.model.dao.UserDao;
import by.shyshaliaksey.aliens.exception.DaoException;
import by.shyshaliaksey.aliens.model.connection.ConnectionPool;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.entity.Comment;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.entity.Token;

/**
 * Implementer of {@link UserDao} designed for user operations
 * 
 * @author Aliaksey Shysh
 *
 */
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getRootLogger();
	private static final UserDao instance = new UserDaoImpl();
	private static final String FIND_BY_ID = """
			SELECT `user_id`, `email`, `login_name`, `image_url`, `role_type`, `status`, `banned_to_datetime`
			FROM `users`
			WHERE `users`.`user_id` = ?
			""";
	private static final String FIND_BY_LOGIN = """
			SELECT `user_id`, `email`, `login_name`, `image_url`, `role_type`, `status`, `banned_to_datetime`
			FROM `users`
			WHERE `users`.`login_name` = ?
			""";
	private static final String FIND_BY_EMAIL = """
			SELECT `user_id`, `email`, `login_name`, `image_url`, `role_type`, `status`, `banned_to_datetime`
			FROM `users` WHERE `users`.`email` = ?
			""";
	private static final String FIND_USER_LOGIN_DATA = """
			SELECT `password_hash`, `salt`
			FROM `users`
			WHERE `email` = ? AND `status` = ? OR `status` = ?
			""";
	private static final String FIND_USER_LOGIN_DATA_BY_ID = """
			SELECT `password_hash`, `salt`
			FROM `users`
			WHERE `user_id` = ? AND `status` = ? OR `status` = ?
			""";
	private static final String REGISTER = """
			INSERT INTO `users`
			(`email`, `login_name`, `password_hash`, `salt`, `image_url`, `role_type`, `status`)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String UPDATE_EMAIL_BY_EMAIL = """
			UPDATE `users`
			SET `email` = ?
			WHERE `email` = ?
			""";
	private static final String UPDATE_LOGIN = """
			UPDATE users
			SET login_name = ?
			WHERE user_id = ?
			""";
	private static final String UPDATE_PASSWORD = """
			UPDATE `users`
			SET `password_hash` = ?
			WHERE `user_id` = ?
			""";
	private static final String UPDATE_PROFILE_IMAGE = """
			UPDATE `users`
			SET `image_url` = ?
			WHERE `user_id` = ?
			""";
	private static final String BAN_UNBAN = """
			UPDATE `users`
			SET `status` = ?, `banned_to_datetime` = ?
			WHERE `login_name` = ? AND `status` = ?
			""";
	private static final String PROMOTE_DEMOTE = """
			UPDATE `users`
			SET `role_type` = ?
			WHERE `login_name` = ?
			""";
	private static final String ADD_NEW_COMMENT = """
			INSERT INTO `comments`
			(`user_id`, `alien_id`, `comment`, `comment_status`)
			VALUES (?, ?, ?, ?)
			""";
	private static final String CHANGE_COMMENT_STATUS_ADMIN = """
			UPDATE `comments`
			SET `comment_status` = ?
			WHERE `comment_id` = ?
			""";
	private static final String CHANGE_COMMENT_STATUS_USER = """
			UPDATE `comments`
			SET `comment_status` = ?
			WHERE `comment_id` = ? AND `user_id` = ?
			""";
	private static final String ADD_NEW_TOKEN = """
			INSERT INTO `tokens`
			(`email`, `token`, `expiration_date`, `status`)
			VALUES (?, ?, ?, ?)
			""";
	private static final String ADD_NEW_UPDATE_EMAIL_TOKEN = """
			INSERT INTO `tokens`
			(`email`, `token`, `expiration_date`, `status`, `new_email`)
			VALUES (?, ?, ?, ?, ?)
			""";
	private static final String CHANGE_USER_STATUS = """
			UPDATE `users`
			SET `status` = ?
			WHERE `email` = ?
			""";
	private static final String FIND_TOKEN = """
			SELECT `token_id`, `email`, `token`, `status`, `expiration_date`, `new_email`
			FROM `tokens`
			WHERE `token` = ? AND `status` = ?
			""";
	private static final String SET_TOKEN_STATUS = """
			UPDATE `tokens`
			SET `status` = ?
			WHERE `token` = ? AND `status` = ?
			""";

	public static UserDao getInstance() {
		return instance;
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
				User.Status status = User.Status.valueOf(resultSet.getString(USER_STATUS));
				Date bannedToDate = resultSet.getDate(USER_BANNED_TO_DATE);
				user = Optional.of(new User(userId, email, loginName, imageUrl, role, status, bannedToDate));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_BY_ID, e.getMessage(),
					e.getStackTrace());
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
				User.Status status = User.Status.valueOf(resultSet.getString(USER_STATUS));
				Date bannedToDate = resultSet.getDate(USER_BANNED_TO_DATE);
				user = Optional.of(new User(userId, email, loginName, imageUrl, role, status, bannedToDate));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_BY_LOGIN, e.getMessage(),
					e.getStackTrace());
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
				User.Status status = User.Status.valueOf(resultSet.getString(USER_STATUS));
				Date bannedToDate = resultSet.getDate(USER_BANNED_TO_DATE);
				user = Optional.of(new User(userId, email, loginName, imageUrl, role, status, bannedToDate));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_BY_EMAIL, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_BY_EMAIL, e);
		}
		return user;
	}

	@Override
	public Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(String userEmail) throws DaoException {
		Map<DatabaseFeedback.Key, Optional<String>> result = new EnumMap<>(DatabaseFeedback.Key.class);
		result.put(DatabaseFeedback.Key.PASSWORD, Optional.empty());
		result.put(DatabaseFeedback.Key.SALT, Optional.empty());
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_LOGIN_DATA)) {
			statement.setString(1, userEmail);
			statement.setString(2, User.Status.NORMAL.name());
			statement.setString(3, User.Status.BANNED.name());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result.put(DatabaseFeedback.Key.PASSWORD, Optional.of(resultSet.getString(USER_PASSWORD_HASH)));
				result.put(DatabaseFeedback.Key.SALT, Optional.of(resultSet.getString(USER_SALT)));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_USER_LOGIN_DATA, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_USER_LOGIN_DATA, e);
		}
		return result;
	}

	@Override
	public Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(int userId) throws DaoException {
		Map<DatabaseFeedback.Key, Optional<String>> result = new EnumMap<>(DatabaseFeedback.Key.class);
		result.put(DatabaseFeedback.Key.SALT, Optional.empty());
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_LOGIN_DATA_BY_ID)) {
			statement.setInt(1, userId);
			statement.setString(2, User.Status.NORMAL.name());
			statement.setString(3, User.Status.BANNED.name());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result.put(DatabaseFeedback.Key.SALT, Optional.of(resultSet.getString(USER_SALT)));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_USER_LOGIN_DATA, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_USER_LOGIN_DATA, e);
		}
		return result;
	}

	@Override
	public boolean registerUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage,
			Role defaultRole) throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(REGISTER)) {
			statement.setString(1, email);
			statement.setString(2, login);
			statement.setString(3, passwordHashHex);
			statement.setString(4, saltHex);
			statement.setString(5, defaultImage);
			statement.setString(6, defaultRole.getValue());
			statement.setString(7, User.Status.CONFIRMATION_AWAITING.name());
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", REGISTER, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + REGISTER, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean updateUserEmail(String email, String newEmail) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL_BY_EMAIL)) {
			statement.setString(1, newEmail);
			statement.setString(2, email);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_EMAIL_BY_EMAIL, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + UPDATE_EMAIL_BY_EMAIL, e);
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_LOGIN, e.getMessage(),
					e.getStackTrace());
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_PASSWORD, e.getMessage(),
					e.getStackTrace());
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_PROFILE_IMAGE, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + UPDATE_PROFILE_IMAGE, e);
		}
		return result == 1;
	}

	@Override
	public boolean banUser(String userLogin, String banDate) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(BAN_UNBAN)) {
			statement.setString(1, User.Status.BANNED.name());
			statement.setString(2, banDate);
			statement.setString(3, userLogin);
			statement.setString(4, User.Status.NORMAL.name());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", BAN_UNBAN, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + BAN_UNBAN, e);
		}
		return result == 1;
	}

	@Override
	public boolean unbanUser(String userLogin, String unbanDate) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(BAN_UNBAN)) {
			statement.setString(1, User.Status.NORMAL.name());
			statement.setString(2, unbanDate);
			statement.setString(3, userLogin);
			statement.setString(4, User.Status.BANNED.name());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", BAN_UNBAN, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + BAN_UNBAN, e);
		}
		return result == 1;
	}

	@Override
	public boolean promoteUser(String userLogin) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(PROMOTE_DEMOTE)) {
			statement.setString(1, Role.ADMIN.getValue());
			statement.setString(2, userLogin);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", PROMOTE_DEMOTE, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + PROMOTE_DEMOTE, e);
		}
		return result == 1;
	}

	@Override
	public boolean demoteAdmin(String adminLogin) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(PROMOTE_DEMOTE)) {
			statement.setString(1, Role.USER.getValue());
			statement.setString(2, adminLogin);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", PROMOTE_DEMOTE, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + PROMOTE_DEMOTE, e);
		}
		return result == 1;
	}

	@Override
	public boolean addNewComment(int userId, int alienId, String newComment) throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW_COMMENT)) {
			statement.setInt(1, userId);
			statement.setInt(2, alienId);
			statement.setString(3, newComment);
			statement.setString(4, Comment.Status.NORMAL.name());
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_NEW_COMMENT, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_NEW_COMMENT, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean deleteComment(int commentId) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_COMMENT_STATUS_ADMIN)) {
			statement.setString(1, Comment.Status.DELETED.name());
			statement.setInt(2, commentId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", CHANGE_COMMENT_STATUS_ADMIN, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + CHANGE_COMMENT_STATUS_ADMIN, e);
		}
		return result == 1;
	}

	@Override
	public boolean deleteComment(int commentId, int userId) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_COMMENT_STATUS_USER)) {
			statement.setString(1, Comment.Status.DELETED.name());
			statement.setInt(2, commentId);
			statement.setInt(3, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", CHANGE_COMMENT_STATUS_USER, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + CHANGE_COMMENT_STATUS_USER, e);
		}
		return result == 1;
	}

	@Override
	public boolean addNewToken(String email, String token, String expirationDate) throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW_TOKEN)) {
			statement.setString(1, email);
			statement.setString(2, token);
			statement.setString(3, expirationDate);
			statement.setString(4, Token.Status.NORMAL.name());
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_NEW_TOKEN, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_NEW_TOKEN, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean addNewToken(String email, String token, String expirationDate, String newEmail) throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW_UPDATE_EMAIL_TOKEN)) {
			statement.setString(1, email);
			statement.setString(2, token);
			statement.setString(3, expirationDate);
			statement.setString(4, Token.Status.NORMAL.name());
			statement.setString(5, newEmail);
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_NEW_UPDATE_EMAIL_TOKEN, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_NEW_UPDATE_EMAIL_TOKEN, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean updateUserStatusToNormal(String email) throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_USER_STATUS)) {
			statement.setString(1, User.Status.NORMAL.name());
			statement.setString(2, email);
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", CHANGE_USER_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + CHANGE_USER_STATUS, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public Optional<Token> findToken(String tokenRequestedContent, Token.Status status) throws DaoException {
		Optional<Token> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_TOKEN)) {
			statement.setString(1, tokenRequestedContent);
			statement.setString(2, status.name());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int tokenId = resultSet.getInt(TOKEN_ID);
				String tokenEmail = resultSet.getString(TOKEN_EMAIL);
				String tokenContent = resultSet.getString(TOKEN);
				String tokenExpiration = resultSet.getString(TOKEN_EXPIRATION);
				String tokenNewEmail = resultSet.getString(TOKEN_NEW_EMAIL);
				Token token = new Token(tokenId, tokenEmail, tokenContent, status, tokenExpiration, tokenNewEmail);
				result = Optional.of(token);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_TOKEN, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_TOKEN, e);
		}
		return result;
	}

	@Override
	public boolean setTokenStatusExpired(String tokenRequestedContent) throws DaoException {
		int rowsUpdated = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SET_TOKEN_STATUS)) {
			statement.setString(1, Token.Status.EXPIRED.name());
			statement.setString(2, tokenRequestedContent);
			statement.setString(3, Token.Status.NORMAL.name());
			rowsUpdated = statement.executeUpdate();
			return rowsUpdated == 1;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", SET_TOKEN_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + SET_TOKEN_STATUS, e);
		}
	}

}

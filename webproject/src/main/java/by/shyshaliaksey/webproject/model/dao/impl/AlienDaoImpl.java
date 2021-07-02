package by.shyshaliaksey.webproject.model.dao.impl;

import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_ID;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_NAME;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_COUNT;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_COMMENTS_COUNT;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_DESCRIPTION_SMALL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_DESCRIPTION_FULL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.ALIEN_IMAGE_URL;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.COMMENT;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.COMMENT_ID;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_LOGIN_NAME;
import static by.shyshaliaksey.webproject.model.dao.ColumnName.USER_IMAGE_URL;
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
import by.shyshaliaksey.webproject.model.entity.Comment;
import by.shyshaliaksey.webproject.model.entity.UserStatus;

public class AlienDaoImpl implements AlienDao {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AlienDaoImpl instance = new AlienDaoImpl();
	private static final String SPACE = " ";
	private static final String FIND_ALL = "SELECT * FROM aliens";
	private static final String FIND_LIMIT = "SELECT * FROM aliens LIMIT ?, ?";
	private static final String FIND_ALL_COMMENTS_BY_ID = "SELECT comments.comment_id, comments.alien_id, comments.comment, comments.comment_status, users.login_name, users.image_url FROM comments INNER JOIN users ON comments.user_id=users.user_id WHERE alien_id=? AND comment_status=?";
	private static final String FIND_BY_ID = String.join(SPACE, FIND_ALL, "WHERE alien_id=?");
	private static final String FIND_BY_NAME = String.join(SPACE, FIND_ALL, "WHERE _name=?");
	private static final String ADD_NEW = "INSERT INTO aliens (_name, description_small, description_full, image_url) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE aliens SET _name = ?, description_small = ?, description_full = ?, image_url = ? WHERE alien_id = ?";
	private static final String FIND_ALIEN_COUNT = "SELECT count(*) as alienCount FROM aliens";
	private static final String FIND_ALIEN_COMMENTS_COUNT = "SELECT count(*) as alienCommentsCount FROM comments WHERE alien_id=?";
	private static final String FIND_COMMENTS_LIMIT = "SELECT comments.comment_id, comments.alien_id, comments.comment, comments.comment_status, users.login_name, users.image_url FROM comments INNER JOIN users ON comments.user_id=users.user_id WHERE alien_id=? AND comment_status=? LIMIT ?, ? ";
	
	public static AlienDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Alien> findAll(int fromRecord, int aliensPerPageLimit) throws DaoException {
		List<Alien> aliens = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_LIMIT)) {
			statement.setInt(1, fromRecord);
			statement.setInt(2, aliensPerPageLimit);
			ResultSet resultSet = statement.executeQuery();
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_LIMIT, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_LIMIT, e);
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
	public boolean addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription, String imageUrl) throws DaoException {
		int rowsAdded = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW)) {
			statement.setString(1, alienName);
			statement.setString(2, alienSmallDescription);
			statement.setString(3, alienFullDescription);
			statement.setString(4, imageUrl);
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", ADD_NEW, e.getMessage());
			throw new DaoException("Can not proceed request: " + ADD_NEW, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean updateAlien(int alienId, String alienName, String alienSmallDescription, String alienFullDescription,
			String imageUrl) throws DaoException {
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setString(1, alienName);
			statement.setString(2, alienSmallDescription);
			statement.setString(3, alienFullDescription);
			statement.setString(4, imageUrl);
			statement.setInt(5, alienId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", UPDATE, e.getMessage());
			throw new DaoException("Can not proceed request: " + UPDATE, e);
		}
		return result == 1;
	}

	@Override
	public List<Comment> findAllComments(int alienId) throws DaoException {
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMMENTS_BY_ID)) {
			statement.setInt(1, alienId);
			statement.setString(2, Comment.CommentStatus.NORMAL.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int commentId = resultSet.getInt(COMMENT_ID);
				String commentValue = resultSet.getString(COMMENT);
				String userLogin = resultSet.getString(USER_LOGIN_NAME);
				String userImageUrl = resultSet.getString(USER_IMAGE_URL);
				Comment comment = new Comment(commentId, commentValue, userLogin, userImageUrl);
				comments.add(comment);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL_COMMENTS_BY_ID, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_ALL_COMMENTS_BY_ID, e);
		}
		return comments;
	}
	
	@Override
	public int findAlienCount() throws DaoException {
		int alienCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALIEN_COUNT)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alienCount = resultSet.getInt(ALIEN_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALIEN_COUNT, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_ALIEN_COUNT, e);
		}
		return alienCount;
	}
	
	@Override
	public int findAlienCommentsCount(int alienId) throws DaoException {
		int alienCommentsCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALIEN_COMMENTS_COUNT)) {
			statement.setInt(1, alienId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alienCommentsCount = resultSet.getInt(ALIEN_COMMENTS_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALIEN_COMMENTS_COUNT, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_ALIEN_COMMENTS_COUNT, e);
		}
		return alienCommentsCount;
	}

	@Override
	public List<Comment> findComments(int alienId, int fromRecord, int aliensPerPageLimit) throws DaoException {
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_COMMENTS_LIMIT)) {
			statement.setInt(1, alienId);
			statement.setString(2, Comment.CommentStatus.NORMAL.toString());
			statement.setInt(3, fromRecord);
			statement.setInt(4, aliensPerPageLimit);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int commentId = resultSet.getInt(COMMENT_ID);
				String commentValue = resultSet.getString(COMMENT);
				String userLogin = resultSet.getString(USER_LOGIN_NAME);
				String userImageUrl = resultSet.getString(USER_IMAGE_URL);
				Comment comment = new Comment(commentId, commentValue, userLogin, userImageUrl);
				comments.add(comment);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_COMMENTS_LIMIT, e.getMessage());
			throw new DaoException("Can not proceed request: " + FIND_COMMENTS_LIMIT, e);
		}
		return comments;
	}

}

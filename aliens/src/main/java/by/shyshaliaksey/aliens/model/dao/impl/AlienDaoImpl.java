package by.shyshaliaksey.aliens.model.dao.impl;

import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_ID;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_NAME;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_COUNT;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_COMMENTS_COUNT;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_DESCRIPTION_SMALL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_DESCRIPTION_FULL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_IMAGE_URL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.ALIEN_IMAGE_IMAGE_URL;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.COMMENT;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.COMMENT_ID;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_ID;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_LOGIN_NAME;
import static by.shyshaliaksey.aliens.model.dao.ColumnName.USER_IMAGE_URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.aliens.exception.DaoException;
import by.shyshaliaksey.aliens.model.connection.ConnectionPool;
import by.shyshaliaksey.aliens.model.dao.AlienDao;
import by.shyshaliaksey.aliens.model.entity.Alien;
import by.shyshaliaksey.aliens.model.entity.Comment;
import by.shyshaliaksey.aliens.model.entity.User;

/**
 * Implementer of {@link AlienDao} designed for operations with aliens
 * 
 * @author Aliaksey Shysh
 *
 */
public class AlienDaoImpl implements AlienDao {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AlienDao instance = new AlienDaoImpl();
	private static final String FIND_LIMIT = """
			SELECT `alien_id`, `name`, `status`, `description_small`, `description_full`, `image_url`
			FROM `aliens`
			WHERE `status` = ? LIMIT ?, ?
			""";
	private static final String FIND_BY_ID = """
			SELECT `alien_id`, `name`, `status`, `description_small`, `description_full`, `image_url`
			FROM `aliens`
			WHERE `alien_id` = ?
			""";
	private static final String FIND_BY_ID_AND_STATUS = """
			SELECT `alien_id`, `name`, `status`, `description_small`, `description_full`, `image_url`
			FROM `aliens`
			WHERE `alien_id` = ? AND `status` = ?
			""";
	private static final String FIND_BY_NAME = """
			SELECT `alien_id`, `name`, `status`, `description_small`, `description_full`, `image_url`
			FROM `aliens`
			WHERE `name` = ?
			""";
	private static final String ADD_NEW = """
			INSERT INTO `aliens`
			(`name`, `status`, `description_small`, `description_full`, `image_url`)
			VALUES (?, ?, ?, ?, ?)
			""";
	private static final String ADD_NEW_IMAGE = """
			INSERT INTO `aliens_images`
			(`alien_id`, `image_url`, `status`)
			VALUES (?, ?, ?)
			""";
	private static final String FIND_ALIEN_COUNT = """
			SELECT count(`alien_id`) as `alienCount`
			FROM `aliens`
			WHERE `status` = ?
			""";
	private static final String FIND_ALIEN_COMMENTS_COUNT = """
			SELECT count(`comment_id`) as `alienCommentsCount`
			FROM `comments`
			WHERE `alien_id` = ? AND `comment_status` = ?
			""";
	private static final String FIND_COMMENTS_LIMIT = """
			SELECT `comments`.`comment_id`, `comments`.`alien_id`, `comments`.`comment`, `comments`.`comment_status`,
				`users`.`user_id`, `users`.`login_name`, `users`.`image_url`
			FROM `comments`
			INNER JOIN `users` ON `comments`.`user_id` = `users`.`user_id`
			WHERE `alien_id` = ? AND `comment_status` = ?
			LIMIT ?, ?
			""";
	private static final String FIND_IMAGES_BY_ID = """
			SELECT `image_url`
			FROM `aliens_images`
			WHERE `alien_id` = ? and `status` = ?
			""";
	private static final String FIND_ALIENS_IMAGES_COUNT = """
			SELECT count(`alien_image_id`) as `alienCount`
			FROM `aliens_images`
			WHERE `status`= ?
			""";
	private static final String FIND_IMAGES = """
			SELECT `aliens_images`.`alien_id`, `aliens`.`name`, `aliens_images`.`image_url`, `aliens_images`.`status`
			FROM `aliens_images`
			INNER JOIN `aliens` ON `aliens_images`.`alien_id` = `aliens`.`alien_id`
			WHERE `aliens_images`.`status` = ?
			LIMIT ?, ?
			""";
	private static final String UPDATE_ALIEN_STATUS = """
			UPDATE `aliens`
			SET `status` = ?
			WHERE `alien_id` = ?
			""";
	private static final String CHANGE_IMAGE_STATUS = """
			UPDATE `aliens_images`
			SET `status` = ?
			WHERE `status` = ? AND `image_url` = ?
			LIMIT 1
			""";
	private static final String UPDATE_ALIEN_INFO = """
			UPDATE `aliens`
			SET `name` = ?, `description_small` = ?, `description_full` = ?
			WHERE `alien_id` = ?
			""";
	private static final String UPDATE_ALIEN_IMAGE = """
			UPDATE `aliens`
			SET `image_url` = ?
			WHERE `alien_id` = ?
			""";

	public static AlienDao getInstance() {
		return instance;
	}

	@Override
	public List<Alien> findAll(int fromRecord, int aliensPerPageLimit) throws DaoException {
		List<Alien> aliens = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_LIMIT)) {
			statement.setString(1, Alien.Status.NORMAL.name());
			statement.setInt(2, fromRecord);
			statement.setInt(3, aliensPerPageLimit);
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_LIMIT, e.getMessage(),
					e.getStackTrace());
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_BY_ID, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
		}
		return alien;
	}

	@Override
	public Optional<Alien> findByIdAndStatus(int alienId, Alien.Status status) throws DaoException {
		Optional<Alien> alien = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_AND_STATUS)) {
			statement.setInt(1, alienId);
			statement.setString(2, status.name());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(ALIEN_NAME);
				String descriptionSmall = resultSet.getString(ALIEN_DESCRIPTION_SMALL);
				String descriptionBig = resultSet.getString(ALIEN_DESCRIPTION_FULL);
				String imagePath = resultSet.getString(ALIEN_IMAGE_URL);
				alien = Optional.of(new Alien(alienId, name, descriptionSmall, descriptionBig, imagePath));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_BY_ID_AND_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID_AND_STATUS, e);
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
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_BY_ID, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
		}
		return alien;
	}

	@SuppressWarnings("ThrowFromFinallyBlock")
	@Override
	public boolean addNewAlienAndImageToGallery(String alienName, String alienSmallDescription,
			String alienFullDescription, String imageUrl) throws DaoException {
		boolean result = false;
		final Connection connection = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement addAlien = connection.prepareStatement(ADD_NEW, new String[] { ALIEN_ID });
				PreparedStatement addImage = connection.prepareStatement(ADD_NEW_IMAGE)) {
			connection.setAutoCommit(false);

			addAlien.setString(1, alienName);
			addAlien.setString(2, Alien.Status.NORMAL.name());
			addAlien.setString(3, alienSmallDescription);
			addAlien.setString(4, alienFullDescription);
			addAlien.setString(5, imageUrl);
			int affectedRows = addAlien.executeUpdate();
			if (affectedRows != 0) {
				ResultSet generatedKeys = addAlien.getGeneratedKeys();
				int id;
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);

					addImage.setInt(1, id);
					addImage.setString(2, imageUrl);
					addImage.setString(3, Alien.Status.NORMAL.name());
					int rowsAdded = addImage.executeUpdate();
					result = rowsAdded == 1;
					connection.commit();
				}
			} else {
				throw new DaoException("Can not proceed request: " + ADD_NEW);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {} {}", ADD_NEW, ADD_NEW_IMAGE, e.getMessage(),
					e.getStackTrace());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, "Can not rollback on `{}` request: {} {} {}", ADD_NEW, ADD_NEW_IMAGE,
						e.getMessage(), e.getStackTrace());
				throw new DaoException(
						"Can not proceed request: " + ADD_NEW + " " + ADD_NEW_IMAGE + " " + e.getMessage(), e);
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Can not return connection on `{}` request: {} {} {}", ADD_NEW, ADD_NEW_IMAGE,
						e.getMessage(), e.getStackTrace());
				throw new DaoException(
						"Can not proceed request: " + ADD_NEW + " " + ADD_NEW_IMAGE + " " + e.getMessage(), e);
			}
		}
		return result;
	}

	@Override
	public void suggestNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
								String imageUrl) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW, new String[] { ALIEN_ID })) {
			statement.setString(1, alienName);
			statement.setString(2, Alien.Status.UNDER_CONSIDERATION.name());
			statement.setString(3, alienSmallDescription);
			statement.setString(4, alienFullDescription);
			statement.setString(5, imageUrl);
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					generatedKeys.getInt(1);
				} else {
					throw new DaoException("Can not proceed request: " + ADD_NEW);
				}
			} else {
				throw new DaoException("Can not proceed request: " + ADD_NEW);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_NEW, e.getMessage(), e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_NEW, e);
		}
	}

	@Override
	public boolean suggestNewAlienImage(int alienId, String imageUrl) throws DaoException {
		int rowsAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW_IMAGE)) {
			statement.setInt(1, alienId);
			statement.setString(2, imageUrl);
			statement.setString(3, Alien.Status.UNDER_CONSIDERATION.name());
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_NEW_IMAGE, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_NEW_IMAGE, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean addNewImageToGallery(int alienId, String imageUrl) throws DaoException {
		int rowsAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_NEW_IMAGE)) {
			statement.setInt(1, alienId);
			statement.setString(2, imageUrl);
			statement.setString(3, Alien.Status.NORMAL.name());
			rowsAdded = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", ADD_NEW_IMAGE, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + ADD_NEW_IMAGE, e);
		}
		return rowsAdded == 1;
	}

	@Override
	public boolean updateAlienInfo(int alienId, String alienName, String alienSmallDescription,
			String alienFullDescription) throws DaoException {
		int result;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ALIEN_INFO)) {
			statement.setString(1, alienName);
			statement.setString(2, alienSmallDescription);
			statement.setString(3, alienFullDescription);
			statement.setInt(4, alienId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_ALIEN_INFO, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + UPDATE_ALIEN_INFO, e);
		}
		return result == 1;
	}

	@SuppressWarnings("ThrowFromFinallyBlock")
	@Override
	public boolean updateAlienImageAndAddToGallery(int alienId, String imageUrl) throws DaoException {
		boolean result = false;
		final Connection connection = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement updateImage = connection.prepareStatement(UPDATE_ALIEN_IMAGE);
				PreparedStatement addToGallery = connection.prepareStatement(ADD_NEW_IMAGE)) {
			connection.setAutoCommit(false);
			updateImage.setString(1, imageUrl);
			updateImage.setInt(2, alienId);
			int updateReflectedRows = updateImage.executeUpdate();
			if (updateReflectedRows == 1) {
				addToGallery.setInt(1, alienId);
				addToGallery.setString(2, imageUrl);
				addToGallery.setString(3, Alien.Status.NORMAL.name());
				int addReflectedRows = addToGallery.executeUpdate();
				if (addReflectedRows == 1) {
					connection.commit();
					result = true;
				} else {
					connection.rollback();
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {} {}", UPDATE_ALIEN_IMAGE, ADD_NEW_IMAGE, e.getMessage(),
					e.getStackTrace());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, "Can not rollback on `{}` request: {} {} {}", UPDATE_ALIEN_IMAGE, ADD_NEW_IMAGE,
						e.getMessage(), e.getStackTrace());
				throw new DaoException(
						"Can not proceed request: " + UPDATE_ALIEN_IMAGE + " " + ADD_NEW_IMAGE + " " + e.getMessage(), e);
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Can not return connection on `{}` request: {} {} {}", UPDATE_ALIEN_IMAGE, ADD_NEW_IMAGE,
						e.getMessage(), e.getStackTrace());
				throw new DaoException(
						"Can not proceed request: " + UPDATE_ALIEN_IMAGE + " " + ADD_NEW_IMAGE + " " + e.getMessage(), e);
			}
		}
		return result;
	}

	@Override
	public int findAlienCount() throws DaoException {
		int alienCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALIEN_COUNT)) {
			statement.setString(1, Alien.Status.NORMAL.toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alienCount = resultSet.getInt(ALIEN_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_ALIEN_COUNT, e.getMessage(),
					e.getStackTrace());
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
			statement.setString(2, Comment.Status.NORMAL.name());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alienCommentsCount = resultSet.getInt(ALIEN_COMMENTS_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_ALIEN_COMMENTS_COUNT, e.getMessage(),
					e.getStackTrace());
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
			statement.setString(2, Comment.Status.NORMAL.toString());
			statement.setInt(3, fromRecord);
			statement.setInt(4, aliensPerPageLimit);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int commentId = resultSet.getInt(COMMENT_ID);
				String commentValue = resultSet.getString(COMMENT);
				int userId = resultSet.getInt(USER_ID);
				String userLogin = resultSet.getString(USER_LOGIN_NAME);
				String userImageUrl = resultSet.getString(USER_IMAGE_URL);
				Comment comment = new Comment(commentId, commentValue, new User(userId, userLogin, userImageUrl));
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_COMMENTS_LIMIT, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_COMMENTS_LIMIT, e);
		}
	}

	@Override
	public List<String> findImages(int alienId) throws DaoException {
		List<String> imagesUrls = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_IMAGES_BY_ID)) {
			statement.setInt(1, alienId);
			statement.setString(2, Alien.Status.NORMAL.name());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String imageUrl = resultSet.getString(ALIEN_IMAGE_IMAGE_URL);
				imagesUrls.add(imageUrl);
			}
			return imagesUrls;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_IMAGES_BY_ID, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_IMAGES_BY_ID, e);
		}
	}

	@Override
	public List<Alien> findAllUnapprovedAliens(int fromRecord, int aliensPerPageLimit) throws DaoException {
		List<Alien> aliens = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_LIMIT)) {
			statement.setString(1, Alien.Status.UNDER_CONSIDERATION.name());
			statement.setInt(2, fromRecord);
			statement.setInt(3, aliensPerPageLimit);
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
	public int findUnapprovedAlienCount() throws DaoException {
		int alienCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALIEN_COUNT)) {
			statement.setString(1, Alien.Status.UNDER_CONSIDERATION.toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alienCount = resultSet.getInt(ALIEN_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_ALIEN_COUNT, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_ALIEN_COUNT, e);
		}
		return alienCount;
	}

	@Override
	public int findUnapprovedAliensImagesCount() throws DaoException {
		int alienCount = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALIENS_IMAGES_COUNT)) {
			statement.setString(1, Alien.Status.UNDER_CONSIDERATION.toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alienCount = resultSet.getInt(ALIEN_COUNT);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_ALIENS_IMAGES_COUNT, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_ALIENS_IMAGES_COUNT, e);
		}
		return alienCount;
	}

	@Override
	public List<Alien> findUnapprovedAliensImages(int fromRecord, int imagesPerPageLimit) throws DaoException {
		List<Alien> aliens = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_IMAGES)) {
			statement.setString(1, Alien.Status.UNDER_CONSIDERATION.toString());
			statement.setInt(2, fromRecord);
			statement.setInt(3, imagesPerPageLimit);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int alienId = resultSet.getInt(ALIEN_ID);
				String alienName = resultSet.getString(ALIEN_NAME);
				String imageUrl = resultSet.getString(ALIEN_IMAGE_IMAGE_URL);
				aliens.add(new Alien(alienId, alienName, imageUrl));
			}
			return aliens;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", FIND_IMAGES, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + FIND_IMAGES, e);
		}
	}

	@Override
	public boolean approveAlien(int alienId) throws DaoException {
		int result;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ALIEN_STATUS)) {
			statement.setString(1, Alien.Status.NORMAL.name());
			statement.setInt(2, alienId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_ALIEN_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + UPDATE_ALIEN_STATUS, e);
		}
		return result == 1;
	}

	@Override
	public boolean declineAlien(int alienId) throws DaoException {
		int result;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ALIEN_STATUS)) {
			statement.setString(1, Alien.Status.DECLINED.name());
			statement.setInt(2, alienId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", UPDATE_ALIEN_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + UPDATE_ALIEN_STATUS, e);
		}
		return result == 1;
	}

	@Override
	public boolean approveSuggestedImage(String alienImageUrl) throws DaoException {
		int result;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_IMAGE_STATUS)) {
			statement.setString(1, Alien.Status.NORMAL.name());
			statement.setString(2, Alien.Status.UNDER_CONSIDERATION.name());
			statement.setString(3, alienImageUrl);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", CHANGE_IMAGE_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + CHANGE_IMAGE_STATUS, e);
		}
		return result == 1;
	}

	@Override
	public boolean declineSuggestedImage(String alienImageUrl) throws DaoException {
		int result;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_IMAGE_STATUS)) {
			statement.setString(1, Alien.Status.DECLINED.name());
			statement.setString(2, Alien.Status.UNDER_CONSIDERATION.name());
			statement.setString(3, alienImageUrl);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not proceed `{}` request: {} {}", CHANGE_IMAGE_STATUS, e.getMessage(),
					e.getStackTrace());
			throw new DaoException("Can not proceed request: " + CHANGE_IMAGE_STATUS, e);
		}
		return result == 1;
	}

}

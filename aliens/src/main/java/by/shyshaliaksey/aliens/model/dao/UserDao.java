package by.shyshaliaksey.aliens.model.dao;

import java.util.Map;
import java.util.Optional;

import by.shyshaliaksey.aliens.exception.DaoException;
import by.shyshaliaksey.aliens.model.entity.Token;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.entity.User.Role;

public interface UserDao {

	/**
	 * Finds user by specified id
	 * 
	 * @param userId {@code int} id of user
	 * @return {@code Optional<User>} from database
	 * @throws DaoException
	 * @see User
	 */
	Optional<User> findById(int userId) throws DaoException;

	/**
	 * Finds user by specified login
	 * 
	 * @param userLogin {@code String} login name of user
	 * @return {@code Optional<User>} from database
	 * @throws DaoException
	 * @see User
	 */
	Optional<User> findByLogin(String userLogin) throws DaoException;

	/**
	 * Finds user by specified email
	 * 
	 * @param email {@code String} email of user
	 * @return {@code Optional<User>} from database
	 * @throws DaoException
	 * @see User
	 */
	Optional<User> findByEmail(String email) throws DaoException;

	/**
	 * Finds {@link DatabaseFeedback.Key#PASSWORD} and
	 * {@link DatabaseFeedback.Key#SALT} required for authorization by specified
	 * email
	 * 
	 * @param userEmail {@code String} email of user
	 * @return {@code Map<DatabaseFeedback.Key, Optional<String>>} with
	 *         {@link DatabaseFeedback.Key#PASSWORD} and
	 *         {@link DatabaseFeedback.Key#SALT} keys
	 * @throws DaoException
	 * @see DatabaseFeedback
	 */
	Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(String userEmail) throws DaoException;

	/**
	 * Finds {@link DatabaseFeedback.Key#PASSWORD} and
	 * {@link DatabaseFeedback.Key#SALT} required for authorization by specified id
	 * 
	 * @param userId {@code int} id of user
	 * @return {@code Map<DatabaseFeedback.Key, Optional<String>>} with
	 *         {@link DatabaseFeedback.Key#PASSWORD} and
	 *         {@link DatabaseFeedback.Key#SALT} keys
	 * @throws DaoException
	 * @see DatabaseFeedback
	 */
	Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(int userId) throws DaoException;

	/**
	 * Adds new user with {@link User.Status#CONFIRMATION_AWAITING} status to
	 * database with specified parameters
	 * 
	 * @param email           {@code String} email of user
	 * @param login           {@code String} login of user
	 * @param passwordHashHex {@code String} hashed password in hex
	 * @param saltHex         {@code String} salt in hex
	 * @param defaultImage    {@code String} default image url
	 * @param defaultRole     {@code String} default user role
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 * @see User
	 */
	boolean registerUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage,
			Role defaultRole) throws DaoException;

	/**
	 * Updates user current email with new email
	 * 
	 * @param email    {@code String} current email of user
	 * @param newEmail {@code String} new requested email of user
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 * @see User
	 */
	boolean updateUserEmail(String email, String newEmail) throws DaoException;

	/**
	 * Updates user current login with new login
	 * 
	 * @param login  {@code String} login of user
	 * @param userId {@code int} id of user
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 * @see User
	 */
	boolean updateUserLogin(String login, int userId) throws DaoException;

	/**
	 * Updates password of user with specified id
	 * 
	 * @param hashedPassword {@code String} new hashed password
	 * @param userId         {@code int} id of user
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 * @see User
	 */
	boolean updateUserPassword(String hashedPassword, int userId) throws DaoException;

	/**
	 * Updates profile image of user with specified id
	 * 
	 * @param imageUrl {@code String} url of new image
	 * @param userId   {@code int} id of user
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 * @see User
	 */
	boolean updateProfileImage(String imageUrl, int userId) throws DaoException;

	/**
	 * Updates {@link User.Status} to {@link User.Status#BANNED} of user with
	 * specified login
	 * 
	 * @param userLogin {@code String} login of user
	 * @param banDate   {@code String} end date of user ban
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 * @see User
	 */
	boolean banUser(String userLogin, String banDate) throws DaoException;

	/**
	 * Updates {@link User.Status} to {@link User.Status#NORMAL} of user with
	 * specified login
	 * 
	 * @param userLogin {@code String} login of user
	 * @param unbanDate {@code String} current date
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean unbanUser(String userLogin, String unbanDate) throws DaoException;

	/**
	 * Updates {@link User.Role} to {@link User.Role#ADMIN} of user with specified
	 * login and {@link User.Role#USER} role
	 * 
	 * @param userLogin {@code String} login of user
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean promoteUser(String userLogin) throws DaoException;

	/**
	 * Updates {@link User.Role} to {@link User.Role#USER} of administrator with
	 * specified login and {@link User.Role#ADMIN} role
	 * 
	 * @param adminLogin {@code String} login of administrator
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean demoteAdmin(String adminLogin) throws DaoException;

	/**
	 * Add new comment to specified alien from user with specified id
	 * 
	 * @param userId     {@code int} id of user
	 * @param alienId    {@code int} id of alien
	 * @param newComment {@code String} new comment
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean addNewComment(int userId, int alienId, String newComment) throws DaoException;

	/**
	 * Updates specified comment status to
	 * {@link by.shyshaliaksey.aliens.model.entity.Comment.Status#DELETED}
	 * (Administrator version)
	 * 
	 * @param commentId {@code int} id of comment
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean deleteComment(int commentId) throws DaoException;

	/**
	 * Updates specified comment status to
	 * {@link by.shyshaliaksey.aliens.model.entity.Comment.Status#DELETED} (User
	 * version)
	 * 
	 * @param commentId {@code int} id of comment
	 * @param userId    {@code int} id of user
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean deleteComment(int commentId, int userId) throws DaoException;

	/**
	 * Adds new token for user registration
	 * 
	 * @param email          {@code String} user email
	 * @param token          {@code String} generated token
	 * @param expirationDate {@code String} date of token expiration
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean addNewToken(String email, String token, String expirationDate) throws DaoException;

	/**
	 * Method for adding new token for user email updating
	 * 
	 * @param email          {@code String} user email
	 * @param token          {@code String} generated token
	 * @param expirationDate {@code String} date of token expiration
	 * @param newEmail       {@code String} requested new user email
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean addNewToken(String email, String token, String expirationDate, String newEmail) throws DaoException;

	/**
	 * Updates user status to {@link User.Status#NORMAL}
	 * 
	 * @param email {@code String} user email
	 * @return true if count of affected rows equals 1, false otherwise
	 * @throws DaoException
	 */
	boolean updateUserStatusToNormal(String email) throws DaoException;

	/**
	 * Finds token information by specified token
	 * 
	 * @param tokenRequestedContent {@code String} token
	 * @param status                {@code String} token status
	 * @return {@code Optional<Token>}
	 * @throws DaoException
	 */
	Optional<Token> findToken(String tokenRequestedContent, Token.Status status) throws DaoException;

	/**
	 * Updates token status to {@link Token.Status#EXPIRED}
	 * 
	 * @param tokenRequestedContent {@code String} token
	 * @return true if count of affected rows equals 1, false otherwiseА
	 * @throws DaoException
	 */
	boolean setTokenStatusExpired(String tokenRequestedContent) throws DaoException;

}
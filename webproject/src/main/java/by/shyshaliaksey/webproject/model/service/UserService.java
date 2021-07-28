package by.shyshaliaksey.webproject.model.service;

import java.util.Map;
import java.util.Optional;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.Part;

/**
 * Interface {@code UserService} designed for communication between controller
 * and model layer for actions related to user.
 * 
 * @author Aliaksey Shysh
 *
 */
public interface UserService {

	/**
	 * Authorizes user in system with email and password.
	 * 
	 * Calls methods to validate form input. Fetches login data (hashed password and
	 * salt) from database. Hashes user password with salt and check current hashed
	 * password and password from login data for equality. Returns feedback back to
	 * implementer of {@link by.shyshaliaksey.webproject.controller.command.Command}
	 * interface.
	 * 
	 * 
	 * @param email    {@code String} user email obtained from request
	 * @param password {@code String} user password obtained from request
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> authorizeUser(String email, String password) throws ServiceException;

	/**
	 * Finds user by email.
	 * 
	 * @param email {@code String} user email obtained from request
	 * @return {@code Optional<User>} user from database
	 * @throws ServiceException
	 */
	Optional<User> findUserByEmail(String email) throws ServiceException;

	/**
	 * Registers user in system with provided data.
	 * 
	 * Calls methods to validate form input. Hashes user password with generated
	 * salt. Checks if user with this email or login already exists. Then makes a
	 * call to database in an attempt to register user. Returns feedback back to
	 * implementer of {@link by.shyshaliaksey.webproject.controller.command.Command}
	 * interface.
	 * 
	 * @param email          {@code String} user email obtained from request
	 * @param login          {@code String} user login obtained from request
	 * @param password       {@code String} user password obtained from request
	 * @param passwordRepeat {@code String} user password confirmation obtained from
	 *                       request
	 * @param locale         {@link LocaleAttribute} current locale obtained from
	 *                       user session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> registerUser(String email, String login, String password, String passwordRepeat,
			LocaleAttribute locale) throws ServiceException;

	/**
	 * Finds user by login.
	 * 
	 * @param login {@code String} user login obtained from request
	 * @return {@code Optional<User>} user from database
	 * @throws ServiceException
	 */
	Optional<User> findByLogin(String login) throws ServiceException;

	/**
	 * Makes request for new email.
	 * 
	 * Calls method to validate form input. Checks if user with this email already
	 * exists. Prepares token, adds it to database and sends it via provided email
	 * address. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param email    {@code String} current user email obtained from session
	 * @param newEmail {@code String} requested user email obtained from request
	 * @param userId   {@code int} current user id obtained from session
	 * @param locale   {@code String} current user locale obtained from session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> makeRequestForNewEmail(String email, String newEmail, int userId, LocaleAttribute locale)
			throws ServiceException;

	/**
	 * Updates user login. Calls method to validate form input. Checks if user with
	 * this login already exists. Then makes a call to database in an attempt update
	 * user login. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param login    {@code String} user login obtained from session
	 * @param newLogin {@code String} requested user login obtained from request
	 * @param userId   {@code int} user id obtained from session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> changeLogin(String login, String newLogin, int userId) throws ServiceException;

	/**
	 * Updates user password.
	 * 
	 * Calls method to validate form input. Hashes password and makes an attempt
	 * update user password. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param password        {@code String} user password obtained from request
	 * @param passwordConfirm {@code String} user password confirmation obtained
	 *                        from request
	 * @param userId          {@code int} user id obtained from current user session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> changePassword(String password, String passwordConfirm, int userId)
			throws ServiceException;

	/**
	 * Updates user profile image.
	 * 
	 * Calls method to validate form input. Creates new image name and uploades it
	 * to server. Then makes a call to database in an attempt update user image
	 * profile url. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param serverDeploymentPath {@code String} path to server deployment
	 *                             directory
	 * @param userImage            {@code Part} new user image obtained from request
	 * @param userId               {@code int} user id obtained from current user
	 *                             session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> updateImage(String serverDeploymentPath, Part userImage, int userId)
			throws ServiceException;

	/**
	 * Adds new comment for specified alien to database.
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to add new comment. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param currentUserId {@code int} user id obtained from current user session
	 * @param alienIdString {@code String} alien id obtained from request
	 * @param newComment    {@code String} new comment to add
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> addNewComment(int currentUserId, String alienIdString, String newComment)
			throws ServiceException;

	/**
	 * Deletes comment from database.
	 * 
	 * Checks user rights to remove comment and makes an attempt to delete comment
	 * from database.
	 * 
	 * @param commentIdString {@code String} comment id obtained from request
	 * @param currentUser     {@code User} current user obtained from session
	 * @return {@code true} if deleting was successful, otherwise {@code false}
	 * @throws ServiceException
	 */
	boolean deleteComment(String commentIdString, User currentUser) throws ServiceException;

	/**
	 * Suggests new alien. Calls methods to validate form input. Then makes a call
	 * to database in an attempt to add new alien with
	 * {@link by.shyshaliaksey.webproject.model.entity.Alien.Status#UNDER_CONSIDERATION}
	 * status to database. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param alienName             {@code String} alien name obtained from request
	 * @param alienSmallDescription {@code String} alien small description obtained
	 *                              from request
	 * @param alienFullDescription  {@code String} alien full description obtained
	 *                              from request
	 * @param alienImage            {@code Part} alien image obtained from request
	 * @param serverDeploymentPath  {@code String} path to server deployment
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> suggestNewAlien(String alienName, String alienSmallDescription,
			String alienFullDescription, Part alienImage, String serverDeploymentPath) throws ServiceException;

	/**
	 * Suggests new alien image. Calls methods to validate form input. Then makes a
	 * call to database in an attempt to add new alien image with
	 * {@link by.shyshaliaksey.webproject.model.entity.Alien.Status#UNDER_CONSIDERATION}
	 * status to database. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param alienName            {@code String} alien name obtained from request
	 * @param alienImage           {@code Part} alien image obtained from request
	 * @param serverDeploymentPath {@code String} path to server deployment
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> suggestNewAlienImage(String alienName, Part alienImage, String serverDeploymentPath)
			throws ServiceException;

	/**
	 * Sets new email address by token.
	 * 
	 * Finds token and checks if it expired. Then makes a call to database in an
	 * attempt to update user email address.
	 * 
	 * @param tokenRequested {@code String} token obtained from request (from user
	 *                       mail)
	 * @return {@code true} if email updating was successful, otherwise
	 *         {@code false}
	 * @throws ServiceException
	 */
	boolean setNewEmail(String tokenRequested) throws ServiceException;

	/**
	 * Activates user account by token.
	 * 
	 * Finds token and checks if it expired. Then makes a call to database in an
	 * attempt to activate users account.
	 * 
	 * @param tokenRequested {@code String} token obtained from request (from user
	 *                       mail)
	 * @return {@code true} if account activating was successful, otherwise
	 *         {@code false}
	 * @throws ServiceException
	 */
	boolean activateAccount(String tokenRequested) throws ServiceException;

}
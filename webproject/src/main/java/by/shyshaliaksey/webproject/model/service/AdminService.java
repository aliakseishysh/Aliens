package by.shyshaliaksey.webproject.model.service;

import java.util.Map;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

/**
 * Interface {@code AdminService} designed for communication between
 * controller and service layer for actions related to administrator.
 * @author Aliaksey Shysh
 *
 */
public interface AdminService {

	/**
	 * Bans user by login to {@code dayToBan} days.
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to ban user. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface
	 * 
	 * 
	 * @param userLogin   {@code String} user login obtained from request
	 * @param daysToBan   {@code String} days to ban obtained from request
	 * @param currentUser {@code String} current user obtained from current user
	 *                    session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 * @see by.shyshaliaksey.webproject.model.util.localization.LocaleKey
	 * @see by.shyshaliaksey.webproject.model.service.ValidationService
	 */
	Map<Feedback.Key, Object> banUser(String userLogin, String daysToBan, String currentUser) throws ServiceException;

	/**
	 * Unbans user by login (sets ban date to current date).
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to unban user. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param userLogin   {@code String} user login obtained from request
	 * @param currentUser {@code String} current user obtained from current user
	 *                    session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization
	 * @throws ServiceException
	 * @see by.shyshaliaksey.webproject.model.util.localization.LocaleKey
	 * @see by.shyshaliaksey.webproject.model.service.ValidationService
	 */
	Map<Feedback.Key, Object> unbanUser(String userLogin, String currentUser) throws ServiceException;

	/**
	 * Promotes user to administrator by login.
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to promote user to administrator. Returns feedback back to
	 * implementer of {@link by.shyshaliaksey.webproject.controller.command.Command}
	 * interface.
	 * 
	 * @param userLogin        {@code String} user login obtained from request
	 * @param currentUserLogin {@code String} current user login obtained from
	 *                         current user session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> promoteUser(String userLogin, String currentUserLogin) throws ServiceException;

	/**
	 * Demotes administrator to user by login.
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to demote administrator to user. Returns feedback back to implementer
	 * of {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param adminLogin        {@code String} administrator login obtained from
	 *                          request
	 * @param currentAdminLogin {@code String} current administrator login obtained
	 *                          from current user session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException;

	/**
	 * Adds new alien to database.
	 * 
	 * Calls methods to validate form input. Then makes a call to database in an
	 * attempt to add new alien to database. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface.
	 * 
	 * @param alienName             {@code String} alien name obtained from request
	 * @param alienSmallDescription {@code String} alien small description obtained
	 *                              from request
	 * @param alienFullDescription  {@code String} alien full description obtained
	 *                              from request
	 * @param alienImage            {@code Part} alien image obtained from request
	 * @param serverDeploymentPath  {@code String} path to server deployment
	 *                              directory
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String serverDeploymentPath) throws ServiceException;

	/**
	 * Updates alien information in database.
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to add update alien information in database. Returns feedback back to
	 * implementer of {@link by.shyshaliaksey.webproject.controller.command.Command}
	 * interface.
	 * 
	 * @param alienId               {@code int} alien id obtained from request
	 * @param alienName             {@code String} alien name obtained from request
	 * @param alienSmallDescription {@code String} alien small description obtained
	 *                              from request
	 * @param alienFullDescription  {@code String} alien full description obtained
	 *                              from request
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> updateAlienInfo(int alienId, String alienName, String alienSmallDescription,
			String alienFullDescription) throws ServiceException;

	/**
	 * Approves alien in database. In case of successful approval adds alien profile
	 * image url to gallery.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @return {@code true} if approval and adding to gallery were successful,
	 *         {@code false} otherwise
	 * @throws ServiceException
	 */
	boolean approveAlien(String alienId) throws ServiceException;

	/**
	 * Declines alien in database.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @return {@code true} if declining was successful, {@code false} otherwise
	 * @throws ServiceException
	 */
	boolean declineAlien(String alienId) throws ServiceException;

	/**
	 * Approves alien image in database.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @return {@code true} if approval and adding to gallery were successful,
	 *         {@code false} otherwise
	 * @throws ServiceException
	 */
	boolean approveAlienImage(String alienId) throws ServiceException;

	/**
	 * Declines alien image in database.
	 * 
	 * @param alienImageUrl {@code Part} alien image obtained from request
	 * @return {@code true} if declining was successful, {@code false} otherwise
	 * @throws ServiceException
	 */
	boolean declineAlienImage(String alienImageUrl) throws ServiceException;

	/**
	 * Updates alien image in database.
	 * 
	 * Calls method to validate form input. Then makes a call to database in an
	 * attempt to update alien profile image in database. Returns feedback back to
	 * implementer of {@link by.shyshaliaksey.webproject.controller.command.Command}
	 * interface.
	 * 
	 * @param alienId              {@code int} alien id obtained from request
	 * @param alienImage           {@code Part} alien image obtained from request
	 * @param serverDeploymentPath {@code String} path to server deployment
	 *                             directory
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and feedback keys for future localization.
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> updateAlienImage(int alienId, Part alienImage, String serverDeploymentPath)
			throws ServiceException;
}

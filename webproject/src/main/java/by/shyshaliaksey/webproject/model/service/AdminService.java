package by.shyshaliaksey.webproject.model.service;

import java.util.Map;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Feedback.Key;
import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface AdminService {

	/**
	 * Bans user by login to {@code dayToBan} days.
	 * 
	 * Calls method to validate form input. Then makes an attempt to ban user.
	 * Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface
	 * 
	 * 
	 * @param userLogin   {@code String} user login obtained from request
	 * @param daysToBan   {@code String} days to ban obtained from request
	 * @param currentUser {@code String} current user obtained from current user
	 *                    session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and keys for future localization
	 * @throws ServiceException
	 * @see by.shyshaliaksey.webproject.model.util.localization.LocaleKey
	 * @see by.shyshaliaksey.webproject.model.service.ValidationService
	 */
	Map<Feedback.Key, Object> banUser(String userLogin, String daysToBan, String currentUser) throws ServiceException;

	/**
	 * Unbans user by login (sets ban date to current date).
	 * 
	 * Calls method to validate form input. Then makes an attempt to unban user.
	 * Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface
	 * 
	 * @param userLogin   {@code String} user login obtained from request
	 * @param currentUser {@code String} current user obtained from current user
	 *                    session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and keys for future localization
	 * @throws ServiceException
	 * @see by.shyshaliaksey.webproject.model.util.localization.LocaleKey
	 * @see by.shyshaliaksey.webproject.model.service.ValidationService
	 */
	Map<Feedback.Key, Object> unbanUser(String userLogin, String currentUser) throws ServiceException;

	/**
	 * Promotes user to administrator by login.
	 * 
	 * Calls method to validate form input. Then makes an attempt to promote user to
	 * administrator. Returns feedback back to implementer of
	 * {@link by.shyshaliaksey.webproject.controller.command.Command} interface
	 * 
	 * @param userLogin        {@code String} user login obtained from request
	 * @param currentUserLogin {@code String} current user login obtained from
	 *                         current user session
	 * @return {@code Map<Feedback.Key, Object>} that contains information about
	 *         status code and keys for future localization
	 * @throws ServiceException
	 */
	Map<Feedback.Key, Object> promoteUser(String userLogin, String currentUserLogin) throws ServiceException;

	Map<Feedback.Key, Object> demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException;

	Map<Feedback.Key, Object> addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException;

	Map<Feedback.Key, Object> updateAlienInfo(int alienId, String alienName, String alienSmallDescription,
			String alienFullDescription) throws ServiceException;

	boolean approveAlien(String alienId) throws ServiceException;

	boolean declineAlien(String alienId) throws ServiceException;

	boolean approveAlienImage(String alienId) throws ServiceException;

	boolean declineAlienImage(String alienImageUrl) throws ServiceException;

	Map<Key, Object> updateAlienImage(int alienId, Part alienImage, String rootFolder, String serverDeploymentPath,
			String websiteUrl) throws ServiceException;
}

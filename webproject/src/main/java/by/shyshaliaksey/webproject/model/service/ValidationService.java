package by.shyshaliaksey.webproject.model.service;

import java.util.Map;

import by.shyshaliaksey.webproject.controller.command.Feedback.Key;
import by.shyshaliaksey.webproject.exception.ServiceException;

/**
 * Interface {@code ValidationService} designed for validation form inputs.
 * 
 * @author Aliaksey Shysh
 *
 */
public interface ValidationService {

	/**
	 * Validates alien information form input and adds feedback to result object
	 * 
	 * @param result                {@code Map<Key, Object>} object that contains
	 *                              information about validation (feedback keys)
	 * @param alienName             {@code String} alien name
	 * @param alienSmallDescription {@code String} alien small description
	 * @param alienFullDescription  {@code String} alien full description
	 * @throws ServiceException
	 */
	void validateAlienInfoFormInput(Map<Key, Object> result, String alienName, String alienSmallDescription,
			String alienFullDescription) throws ServiceException;

	/**
	 * Validates ban form input and adds feedback to result object
	 * 
	 * @param result    {@code Map<Key, Object>} object that contains information
	 *                  about validation (feedback keys)
	 * @param userLogin {@code String} user login
	 * @param daysToBan {@code int} number of days to ban user
	 * @throws ServiceException
	 */
	void validateBanFormInput(Map<Key, Object> result, String userLogin, String daysToBan) throws ServiceException;

	/**
	 * Validates login form input and adds feedback to result object
	 * 
	 * @param result {@code Map<Key, Object>} object that contains information about
	 *               validation (feedback keys)
	 * @param login  {@code String} user login
	 * @throws ServiceException
	 */
	void validateLoginFormInput(Map<Key, Object> result, String login) throws ServiceException;

	/**
	 * Validates email form input and adds feedback to result object
	 * 
	 * @param result {@code Map<Key, Object>} object that contains information about
	 *               validation (feedback keys)
	 * @param email  {@code String} user email
	 * @throws ServiceException
	 */
	void validateEmailFormInput(Map<Key, Object> result, String email) throws ServiceException;

	/**
	 * Validates password form input and adds feedback to result object
	 * 
	 * @param result   {@code Map<Key, Object>} object that contains information
	 *                 about validation (feedback keys)
	 * @param password {@code String} user password
	 * @throws ServiceException
	 */
	void validatePasswordFormInput(Map<Key, Object> result, String password) throws ServiceException;

	/**
	 * Validates password confirmation form input and adds feedback to result object
	 * 
	 * @param result   {@code Map<Key, Object>} object that contains information
	 *                 about validation (feedback keys)
	 * @param password {@code String} user password confirmation
	 * @throws ServiceException
	 */
	void validatePasswordConfirmationFormInput(Map<Key, Object> result, String password) throws ServiceException;

	/**
	 * Validates passwords equality and adds feedback to result object
	 * 
	 * @param result               {@code Map<Key, Object>} object that contains
	 *                             information about validation (feedback keys)
	 * @param password             {@code String} user password
	 * @param passwordConfirmation {@code String} user password confirmation
	 * @throws ServiceException
	 */
	void validatePasswordEquality(Map<Key, Object> result, String password, String passwordConfirmation)
			throws ServiceException;

	/**
	 * Validates image form input and adds feedback to result object
	 * 
	 * @param result        {@code Map<Key, Object>} object that contains
	 *                      information about validation (feedback keys)
	 * @param fileExtension {@code String} extension of file
	 * @param fileSize      {@code long} size of file in {@code bytes}
	 * @throws ServiceException
	 */
	void validateImageFormInput(Map<Key, Object> result, String fileExtension, long fileSize) throws ServiceException;

	/**
	 * Validates comment form input and adds feedback to result object
	 * 
	 * @param result  {@code Map<Key, Object>} object that contains information
	 *                about validation (feedback keys)
	 * @param comment {@code String} user comment
	 * @throws ServiceException
	 */
	void validateCommentFormInput(Map<Key, Object> result, String comment) throws ServiceException;

	/**
	 * Validates alien name input and adds feedback to result object
	 * 
	 * @param result    {@code Map<Key, Object>} object that contains information
	 *                  about validation (feedback keys)
	 * @param alienName {@code String} alien name
	 * @throws ServiceException
	 */
	void validateAlienNameFormInput(Map<Key, Object> result, String alienName) throws ServiceException;
}

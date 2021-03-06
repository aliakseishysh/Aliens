package by.shyshaliaksey.aliens.model.service;

import java.util.Map;

import by.shyshaliaksey.aliens.controller.command.Feedback;

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
	 * @see Feedback
	 */
	void validateAlienInfoFormInput(Map<Feedback.Key, Object> result, String alienName, String alienSmallDescription,
			String alienFullDescription);

	/**
	 * Validates ban form input and adds feedback to result object
	 * 
	 * @param result    {@code Map<Key, Object>} object that contains information
	 *                  about validation (feedback keys)
	 * @param userLogin {@code String} user login
	 * @param daysToBan {@code int} number of days to ban user
	 * @see Feedback
	 */
	void validateBanFormInput(Map<Feedback.Key, Object> result, String userLogin, String daysToBan);

	/**
	 * Validates login form input and adds feedback to result object
	 * 
	 * @param result {@code Map<Key, Object>} object that contains information about
	 *               validation (feedback keys)
	 * @param login  {@code String} user login
	 * @see Feedback
	 */
	void validateLoginFormInput(Map<Feedback.Key, Object> result, String login);

	/**
	 * Validates email form input and adds feedback to result object
	 * 
	 * @param result {@code Map<Key, Object>} object that contains information about
	 *               validation (feedback keys)
	 * @param email  {@code String} user email
	 * @see Feedback
	 */
	void validateEmailFormInput(Map<Feedback.Key, Object> result, String email);

	/**
	 * Validates password form input and adds feedback to result object
	 * 
	 * @param result   {@code Map<Key, Object>} object that contains information
	 *                 about validation (feedback keys)
	 * @param password {@code String} user password
	 * @see Feedback
	 */
	void validatePasswordFormInput(Map<Feedback.Key, Object> result, String password);

	/**
	 * Validates password confirmation form input and adds feedback to result object
	 * 
	 * @param result   {@code Map<Key, Object>} object that contains information
	 *                 about validation (feedback keys)
	 * @param password {@code String} user password confirmation
	 * @see Feedback
	 */
	void validatePasswordConfirmationFormInput(Map<Feedback.Key, Object> result, String password);

	/**
	 * Validates passwords equality and adds feedback to result object
	 * 
	 * @param result               {@code Map<Key, Object>} object that contains
	 *                             information about validation (feedback keys)
	 * @param password             {@code String} user password
	 * @param passwordConfirmation {@code String} user password confirmation
	 * @see Feedback
	 */
	void validatePasswordEquality(Map<Feedback.Key, Object> result, String password, String passwordConfirmation)
	;

	/**
	 * Validates image form input and adds feedback to result object
	 * 
	 * @param result        {@code Map<Key, Object>} object that contains
	 *                      information about validation (feedback keys)
	 * @param fileExtension {@code String} extension of file
	 * @param fileSize      {@code long} size of file in {@code bytes}
	 * @see Feedback
	 */
	void validateImageFormInput(Map<Feedback.Key, Object> result, String fileExtension, long fileSize);

	/**
	 * Validates comment form input and adds feedback to result object
	 * 
	 * @param result  {@code Map<Key, Object>} object that contains information
	 *                about validation (feedback keys)
	 * @param comment {@code String} user comment
	 * @see Feedback
	 */
	void validateCommentFormInput(Map<Feedback.Key, Object> result, String comment);

	/**
	 * Validates alien name input and adds feedback to result object
	 * 
	 * @param result    {@code Map<Key, Object>} object that contains information
	 *                  about validation (feedback keys)
	 * @param alienName {@code String} alien name
	 * @see Feedback
	 */
	void validateAlienNameFormInput(Map<Feedback.Key, Object> result, String alienName);

}

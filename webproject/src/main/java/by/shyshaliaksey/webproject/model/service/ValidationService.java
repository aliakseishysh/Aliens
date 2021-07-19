package by.shyshaliaksey.webproject.model.service;

import java.util.Map;

import by.shyshaliaksey.webproject.controller.command.Feedback.Key;
import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface ValidationService {

	void validateAlienFormInput(Map<Key, Object> result, String alienName, String alienSmallDescription,
			String alienFullDescription, Part alienImage) throws ServiceException;
	void validateBanFormInput(Map<Key, Object> result, String userLogin, String daysToBan) throws ServiceException;
	void validateLoginFormInput(Map<Key, Object> result, String login) throws ServiceException;
	void validateEmailFormInput(Map<Key, Object> result, String email) throws ServiceException;
	void validatePasswordFormInput(Map<Key, Object> result, String password) throws ServiceException;
	void validatePasswordConfirmationFormInput(Map<Key, Object> result, String password) throws ServiceException;
	void validatePasswordEquality(Map<Key, Object> result, String password, String passwordConfirmation) throws ServiceException;
	void validateImageFormInput(Map<Key, Object> result, String fileExtension, long fileSize) throws ServiceException;
	void validateCommentFormInput(Map<Key, Object> result, String comment) throws ServiceException;
	void validateAlienNameFormInput(Map<Key, Object> result, String alienName) throws ServiceException;
}

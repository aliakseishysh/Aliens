package by.shyshaliaksey.webproject.model.service.impl;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Feedback.Key;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.FormPattern;
import by.shyshaliaksey.webproject.model.service.ValidationService;
import by.shyshaliaksey.webproject.model.util.localization.LocaleKey;
import jakarta.servlet.http.Part;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public void validateAlienFormInput(Map<Feedback.Key, Object> result, String alienName, String alienSmallDescription,
			String alienFullDescription, Part alienImage) throws ServiceException {
		if (validateAlienName(alienName)) {
			result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.ALIEN_NAME_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
		if (validateAlienSmallDescription(alienSmallDescription)) {
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK,
					LocaleKey.ALIEN_SMALL_DESCRIPTION_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
		if (validateAlienFullDescription(alienFullDescription)) {
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK,
					LocaleKey.ALIEN_FULL_DESCRIPTION_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
		if (alienImage != null && validateImageExtension(FilenameUtils.getExtension(alienImage.getSubmittedFileName()))
				&& validateImageSize(alienImage.getSize())) {
			result.put(Feedback.Key.IMAGE_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.IMAGE_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.IMAGE_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
	}

	@Override
	public void validateBanFormInput(Map<Feedback.Key, Object> result, String userLogin, String daysToBan)
			throws ServiceException {
		if (validateLogin(userLogin)) {
			result.put(Feedback.Key.LOGIN_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.LOGIN_FEEDBACK_INVALID.getValue());
		}
		int daysToBanInt = -1;
		try {
			daysToBanInt = Integer.parseInt(daysToBan);
			if (validateDaysToBan(daysToBanInt)) {
				result.put(Feedback.Key.DAYS_TO_BAN_STATUS, Boolean.TRUE);
				result.put(Feedback.Key.DAYS_TO_BAN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
				result.put(Feedback.Key.DAYS_TO_BAN_STATUS, Boolean.FALSE);
				result.put(Feedback.Key.DAYS_TO_BAN_FEEDBACK, LocaleKey.DAYS_TO_BAN_FEEDBACK_INVALID.getValue());
			}
		} catch (NumberFormatException e) {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.DAYS_TO_BAN_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.DAYS_TO_BAN_FEEDBACK, LocaleKey.DAYS_TO_BAN_FEEDBACK_INVALID.getValue());
		}

	}

	@Override
	public void validateLoginFormInput(Map<Feedback.Key, Object> result, String userLogin) throws ServiceException {
		if (validateLogin(userLogin)) {
			result.put(Feedback.Key.LOGIN_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.LOGIN_FEEDBACK_INVALID.getValue());
		}
	}

	@Override
	public void validateEmailFormInput(Map<Key, Object> result, String email) throws ServiceException {
		if (validateEmail(email)) {
			result.put(Feedback.Key.EMAIL_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_FEEDBACK_INVALID.getValue());
		}
	}

	@Override
	public void validatePasswordFormInput(Map<Key, Object> result, String password) throws ServiceException {
		if (validatePassword(password)) {
			result.put(Feedback.Key.PASSWORD_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID.getValue());
		}
	}

	@Override
	public void validatePasswordConfirmationFormInput(Map<Key, Object> result, String password)
			throws ServiceException {
		if (validatePassword(password)) {
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
					LocaleKey.PASSWORD_CONFIRMATION_FEEDBACK_INVALID.getValue());
		}
	}
	
	@Override
	public void validatePasswordEquality(Map<Key, Object> result, String password, String passwordConfirmation)
			throws ServiceException {
		if (password.equals(passwordConfirmation)) {
			result.put(Feedback.Key.PASSWORD_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.PASSWORD_FEEDBACK,
					LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
			result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
					LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
		}

	}
	
	@Override
	public void validateImageFormInput(Map<Key, Object> result, String fileExtension, long fileSize) throws ServiceException {
		if (validateImageExtension(fileExtension)) {
			if (validateImageSize(fileSize)) {
				result.put(Feedback.Key.IMAGE_STATUS, Boolean.TRUE);
				result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
			} else {
				result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
				result.put(Feedback.Key.IMAGE_STATUS, Boolean.FALSE);
				result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.IMAGE_FEEDBACK_INVALID_SIZE.getValue());
			}
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.IMAGE_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.IMAGE_FEEDBACK, LocaleKey.IMAGE_FEEDBACK_INVALID_EXTENSION.getValue());
		}
	}

	@Override
	public void validateCommentFormInput(Map<Key, Object> result, String comment) throws ServiceException {
		if (validateComment(comment)) {
			result.put(Feedback.Key.COMMENT_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.COMMENT_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
			result.put(Feedback.Key.COMMENT_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.COMMENT_FEEDBACK, LocaleKey.COMMENT_FEEDBACK_INVALID.getValue());
		}
	}
	
	@Override 
	public void validateAlienNameFormInput(Map<Key, Object> result, String alienName) throws ServiceException {
		if (validateAlienName(alienName)) {
			result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.ALIEN_NAME_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
	}
	
	private boolean validateEmail(String email) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_EMAIL.getValue(), email);
		return result;
	}

	private boolean validateLogin(String login) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_LOGIN.getValue(), login);
		return result;
	}

	public boolean validatePassword(String password) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_PASSWORD.getValue(), password);
		return result;
	}

	private boolean validateImageExtension(String imageExtension) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_IMAGE_EXTENSION.getValue(), imageExtension);
		return result;
	}

	private boolean validateImageSize(long imageSize) throws ServiceException {
		boolean result = imageSize <= Long.parseLong(FormPattern.VALID_IMAGE_SIZE.getValue());
		return result;
	}

	public boolean validateDaysToBan(int daysToBan) throws ServiceException {
		boolean result = daysToBan > 0;
		return result;
	}

	private boolean validateAlienName(String alienName) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_ALIEN_NAME.getValue(), alienName);
		return result;
	}

	private boolean validateAlienSmallDescription(String alienSmallDescription) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_ALIEN_SMALL_DESCRIPTION.getValue(), alienSmallDescription);
		return result;
	}

	private boolean validateAlienFullDescription(String alienFullDescription) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_ALIEN_FULL_DESCRIPTION.getValue(), alienFullDescription);
		return result;
	}

	private boolean validateComment(String comment) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_COMMENT.getValue(), comment);
		return result;
	}

}

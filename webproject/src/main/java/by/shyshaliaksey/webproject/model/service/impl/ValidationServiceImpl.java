package by.shyshaliaksey.webproject.model.service.impl;

import java.util.Map;
import java.util.regex.Pattern;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Feedback.Key;
import by.shyshaliaksey.webproject.model.service.FormPattern;
import by.shyshaliaksey.webproject.model.service.ValidationService;
import by.shyshaliaksey.webproject.model.util.localization.LocaleKey;

/**
 * Implementer of {@link ValidationService} designed for validation form inputs.
 * 
 * @author Aliaksey Shysh
 *
 */
public final class ValidationServiceImpl implements ValidationService {

	@Override
	public void validateAlienInfoFormInput(Map<Feedback.Key, Object> result, String alienName,
			String alienSmallDescription, String alienFullDescription) {
		validateAlienNameFormInput(result, alienName);
		validateAlienSmallDescriptionFormInput(result, alienSmallDescription);
		validateAlienFullDescriptionFormInput(result, alienFullDescription);
	}

	@Override
	public void validateBanFormInput(Map<Feedback.Key, Object> result, String userLogin, String daysToBan) {
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
	public void validateLoginFormInput(Map<Feedback.Key, Object> result, String userLogin) {
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
	public void validateEmailFormInput(Map<Feedback.Key, Object> result, String email) {
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
	public void validatePasswordFormInput(Map<Feedback.Key, Object> result, String password) {
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
	public void validatePasswordConfirmationFormInput(Map<Feedback.Key, Object> result, String password) {
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
	public void validatePasswordEquality(Map<Feedback.Key, Object> result, String password,
			String passwordConfirmation) {
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
	public void validateImageFormInput(Map<Feedback.Key, Object> result, String fileExtension, long fileSize) {
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
	public void validateCommentFormInput(Map<Feedback.Key, Object> result, String comment) {
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
	public void validateAlienNameFormInput(Map<Feedback.Key, Object> result, String alienName) {
		if (validateAlienName(alienName)) {
			result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_NAME_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_NAME_FEEDBACK, LocaleKey.ALIEN_NAME_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
	}

	@Override
	public void validateAlienSmallDescriptionFormInput(Map<Key, Object> result, String smallDescription) {
		if (validateAlienSmallDescription(smallDescription)) {
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK,
					LocaleKey.ALIEN_SMALL_DESCRIPTION_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
	}

	@Override
	public void validateAlienFullDescriptionFormInput(Map<Key, Object> result, String fullDescription) {
		if (validateAlienFullDescription(fullDescription)) {
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS, Boolean.TRUE);
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
		} else {
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS, Boolean.FALSE);
			result.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK,
					LocaleKey.ALIEN_FULL_DESCRIPTION_FEEDBACK_INVALID.getValue());
			result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
		}
	}

	private static boolean validateEmail(String email) {
		return email != null ? Pattern.matches(FormPattern.VALID_EMAIL.getValue(), email) : false;
	}

	private static boolean validateLogin(String login) {
		return login != null ? Pattern.matches(FormPattern.VALID_LOGIN.getValue(), login) : false;
	}

	private static boolean validatePassword(String password) {
		return password != null ? Pattern.matches(FormPattern.VALID_PASSWORD.getValue(), password) : false;
	}

	private static boolean validateImageExtension(String imageExtension) {
		return imageExtension != null ? Pattern.matches(FormPattern.VALID_IMAGE_EXTENSION.getValue(), imageExtension)
				: false;
	}

	private static boolean validateImageSize(long imageSize) {
		return imageSize <= Long.parseLong(FormPattern.MAX_VALID_IMAGE_SIZE.getValue()) && imageSize > 0;
	}

	private static boolean validateDaysToBan(int daysToBan) {
		return Pattern.matches(FormPattern.VALID_DAYS_IN_BAN.getValue(), Integer.toString(daysToBan));
	}

	private static boolean validateAlienName(String alienName) {
		return alienName != null ? Pattern.matches(FormPattern.VALID_ALIEN_NAME.getValue(), alienName) : false;
	}

	private static boolean validateAlienSmallDescription(String alienSmallDescription) {
		return alienSmallDescription != null
				? Pattern.matches(FormPattern.VALID_ALIEN_SMALL_DESCRIPTION.getValue(), alienSmallDescription)
				: false;
	}

	private static boolean validateAlienFullDescription(String alienFullDescription) {
		return alienFullDescription != null
				? Pattern.matches(FormPattern.VALID_ALIEN_FULL_DESCRIPTION.getValue(), alienFullDescription)
				: false;
	}

	private static boolean validateComment(String comment) {
		return comment != null ? Pattern.matches(FormPattern.VALID_COMMENT.getValue(), comment) : false;
	}

}

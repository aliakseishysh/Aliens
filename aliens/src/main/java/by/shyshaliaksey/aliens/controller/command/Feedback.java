package by.shyshaliaksey.aliens.controller.command;

import by.shyshaliaksey.aliens.controller.EnumValue;

/**
 * Class with feedback keys and status codes
 */
public class Feedback {

	/**
	 * Enum that defines feedback keys
	 */
	public enum Key implements EnumValue {
		EMPTY_MESSAGE(""), 
		RESPONSE_CODE("response_code"),
		EMAIL_STATUS("email_status"), 
		EMAIL_FEEDBACK("email_feedback"), 
		LOGIN_STATUS("login_status"),
		LOGIN_FEEDBACK("login_feedback"), 
		PASSWORD_STATUS("password_status"), 
		PASSWORD_FEEDBACK("password_feedback"),
		PASSWORD_CONFIRMATION_STATUS("password_confirmation_status"),
		PASSWORD_CONFIRMATION_FEEDBACK("password_confirmation_feedback"), 
		IMAGE_STATUS("image_status"),
		IMAGE_PATH("image_path"), 
		IMAGE_FEEDBACK("image_feedback"), 
		DAYS_TO_BAN_STATUS("days_to_ban_status"),
		DAYS_TO_BAN_FEEDBACK("days_to_ban_feedback"), 
		ALIEN_STATUS("alien_status"),
		ALIEN_NAME_STATUS("alien_name_status"), 
		ALIEN_NAME_FEEDBACK("alien_name_feedback"),
		ALIEN_SMALL_DESCRIPTION_STATUS("alien_small_description_status"),
		ALIEN_SMALL_DESCRIPTION_FEEDBACK("alien_small_description_feedback"),
		ALIEN_FULL_DESCRIPTION_STATUS("alien_full_description_status"),
		ALIEN_FULL_DESCRIPTION_FEEDBACK("alien_full_description_feedback"),
		COMMENT_STATUS("comment_status"),
		COMMENT_FEEDBACK("comment_feedback"), 
		LOGOUT_STATUS("logout_status");

		private final String value;

		Key(String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}
	}

	/**
	 *	Enum that defines status codes for server response
	 */
	public enum Code {
		OK(200),
		WRONG_INPUT(400),
		NOT_AUTHORIZED(403),
		INTERNAL_SERVER_ERROR(500);

		private final Integer statusCode;

		Code(Integer statusCode) {
			this.statusCode = statusCode;
		}

		public Integer getStatusCode() {
			return statusCode;
		}
	}

}

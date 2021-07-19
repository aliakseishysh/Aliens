package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.EnumValue;

public class Feedback {

	public enum Key implements EnumValue {
		EMPTY_MESSAGE(""),
		PASSWORD("password"),
		SALT("salt"),
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
		
		private String value;
		
		private Key(String value) {
			this.value = value;
		}
		
		@Override
		public String getValue() {
			return value;
		}
	}
	
	public enum Code {
		OK(200),
		WRONG_INPUT(400),
		NOT_AUTHORIZED(403),
		NOT_FOUND(404),
		INTERNAL_SERVER_ERROR(500);
		
		private Integer statusCode;
		
		private Code(Integer statusCode) {
			this.statusCode = statusCode;
		}
		
		public Integer getStatusCode() {
			return statusCode;
		}
	}
	
}

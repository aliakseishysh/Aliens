package by.shyshaliaksey.webproject.model.entity.feedback;

import by.shyshaliaksey.webproject.controller.EnumValue;

public enum ErrorFeedback implements EnumValue  {

	LOGIN_RESULT_INFO_EMAIL_STATUS("email_status"),
	LOGIN_RESULT_INFO_PASSWORD_STATUS("password_status"),
	LOGIN_RESULT_INFO_EMAIL_FEEDBACK("email_info_message"),
	LOGIN_RESULT_INFO_PASSWORD_FEEDBACK("password_info_message"),
	STANDARD_EMAIL_FEEDBACK("Please enter a valid email address"),
	STANDARD_PASSWORD_FEDDBACK("Please enter a valid password"),
	PASSWORD_INCORRECT_FOR_EMAIL("Your password is incorrect for this email"),
	NO_USER_WITH_EMAIL("No user with this email"),
	NO_USER_WITH_LOGIN("No user with this login"),
	REGISTER_STANDARD_EMAIL_FEEDBACK("Please enter a valid email address"),
	REGISTER_STANDARD_LOGIN_FEEDBACK("Please enter a valid login"),
	REGISTER_STANDARD_PASSWORD_FEEDBACK("Please enter a valid password"),
	REGISTER_STANDARD_PASSWORD_CONFIRM_FEEDBACK("Please enter a valid password confirmation"),
	REGISTER_RESULT_INFO_EMAIL_STATUS("register_result_email_status"),
	REGISTER_RESULT_INFO_LOGIN_STATUS("register_result_login_status"),
	REGISTER_RESULT_INFO_PASSWORD_STATUS("register_result_password_status"),
	REGISTER_RESULT_INFO_PASSWORD_CONFIRM_STATUS("register_result_password_confirmation_status"),
	REGISTER_RESULT_INFO_EMAIL_FEEDBACK("register_email_server_answer"),
	REGISTER_RESULT_INFO_LOGIN_FEEDBACK("register_login_server_answer"),
	REGISTER_RESULT_INFO_PASSWORD_FEEDBACK("register_password_server_answer"),
	REGISTER_RESULT_INFO_PASSWORD_CONFIRM_FEEDBACK("register_password_confirmation_server_answer"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_EMAIL("Please check your email for validity"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_LOGIN("Please check your login for validity"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORD("Please check your password for validity"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORD_CONFIRMATION("Please check your password confirmation for validity"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_EMAIL_USER_EXISTS("User with this email already exists"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_USER_EXISTS("User with this login already exists"),
	REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS("Passwords are not equals"),
	UPDATE_STANDARD_EMAIL_FEEDBACK("Please enter a valid email address"),
	UPDATE_EMAIL_RESULT_INFO_STATUS("update_email_status"),
	UPDATE_EMAIL_RESULT_INFO_FEEDBACK_INVALID_EMAIL("Please check your email for validity"),
	UPDATE_EMAIL_RESULT_INFO_FEEDBACK_INVALID_EMAIL_USER_EXISTS("User with this email already exists"),
	UPDATE_EMAIL_RESULT_INFO_FEEDBACK_INVALID_AUTHORIZATION_ERROR("You can't change email of another user"),
	UPDATE_EMAIL_RESULT_INFO_EMAIL_FEEDBACK("update_email_feedback"),
	UPDATE_LOGIN_RESULT_INFO_STATUS("update_login_status"),
	UPDATE_LOGIN_RESULT_INFO_LOGIN_FEEDBACK("update_login_feedback"),
	UPDATE_LOGIN_STANDARD_LOGIN_FEEDBACK("Please enter a valid login"),
	UPDATE_LOGIN_RESULT_INFO_FEEDBACK_INVALID_LOGIN_USER_EXISTS("User with this login already exists"),
	UPDATE_LOGIN_RESULT_INFO_FEEDBACK_INVALID_AUTHORIZATION_ERROR("You can't change login of another user"),
	UPDATE_LOGIN_RESULT_INFO_FEEDBACK_INVALID_LOGIN("Please check your login for validity"),
	UPDATE_PASSWORD_STANDARD_PASSWORD_FEEDBACK("Please enter a valid password"),
	UPDATE_PASSWORD_STANDARD_PASSWORD_CONFIRMATION_FEEDBACK("Please enter a valid password"),
	UPDATE_PASSWORD_RESULT_INFO_PASSWORD_STATUS("update_password_password_status"),
	UPDATE_PASSWORD_RESULT_INFO_PASSWORD_FEEDBACK("update_password_password_feedback"),
	UPDATE_PASSWORD_RESULT_INFO_PASSWORD_CONFIRMATION_STATUS("update_password_password_confirmation_status"),
	UPDATE_PASSWORD_RESULT_INFO_PASSWORD_CONFIRMATION_FEEDBACK("update_password_password_confirmation_feedback"),
	UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORD("Please check your password for validity"),
	UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORD_CONFIRMATION("Please check your password confirmation for validity"),
	UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS("Passwords are not equals"),
	UPDATE_PASSWORD_RESULT_INFO_FEEDBACK_INVALID_NO_USER_WITH_ID("Can't set password for current user"),
	UPDATE_IMAGE_RESULT_INFO_IMAGE_STATUS("update_image_image_status"),
	UPDATE_IMAGE_RESULT_INFO_IMAGE_FEEDBACK("update_image_image_feedback"),
	UPDATE_IMAGE_STANDARD_IMAGE_FEEDBACK("Please check your image for validity"),
	UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_IMAGE("Please check your image size and extension"),
	UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_EXTENSION("Please check your image extension"),
	UPDATE_IMAGE_RESULT_INFO_FEEDBACK_INVALID_SIZE("Please check your image size"),
	BAN_UNBAN_USER_LOGIN_STATUS("ban_unban_user_login_status"),
	BAN_UNBAN_USER_DAYS_TO_BAN_STATUS("ban_unban_user_days_to_ban_status"),
	BAN_UNBAN_USER_RESULT_INFO_LOGIN_FEEDBACK("ban_unban_user_login_feedback"),
	BAN_UNBAN_USER_RESULT_INFO_DAYS_TO_BAN_FEEDBACK("ban_unban_user_days_to_ban_feedback"),
	BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK("Please enter a valid login"),
	BAN_UNBAN_USER_STANDARD_DAYS_TO_BAN_FEEDBACK("Please enter a valid days of ban"),
	BAN_UNBAN_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN("Please check your login for validity"),
	BAN_UNBAN_USER_RESULT_INFO_FEEDBACK_INVALID_DAYS_TO_BAN("Please check days to ban for validity"),
	PROMOTE_DEMOTE_USER_LOGIN_STATUS("promote_demote_user_login_status"),
	PROMOTE_DEMOTE_USER_RESULT_INFO_LOGIN_FEEDBACK("promote_demote_user_login_feedback"),
	PROMOTE_DEMOTE_USER_STANDARD_LOGIN_FEEDBACK("Please enter a valid login"),
	PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN("Please check your login for validity"),
	PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_PROMOTE_YOURSELF("You can't promote yourself"),
	PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_DEMOTE_YOURSELF("You can't demote yourself"),
	PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_CAN_NOT_FIND_USER_FOR_PROMOTING("Can't find suitable user for promoting"),
	PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_CAN_NOT_FIND_ADMIN_FOR_DEMOTING("Can't find suitable admin for demoting");
	
	 
	
	private String value;
	
	private ErrorFeedback(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}






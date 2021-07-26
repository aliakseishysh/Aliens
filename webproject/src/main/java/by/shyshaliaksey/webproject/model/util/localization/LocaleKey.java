package by.shyshaliaksey.webproject.model.util.localization;

import by.shyshaliaksey.webproject.controller.EnumValue;

public enum LocaleKey implements EnumValue {

	// Suggest alien
	PAGE_SUGGEST_ALIEN_TITLE("page_suggest_alien_title"),
	PAGE_SUGGEST_ALIEN_H1("page_suggest_alien_h1"),
	PAGE_SUGGEST_ALIEN_SUBMIT("page_suggest_alien_submit"),
	// Alien profile page
	PAGE_ALIEN_PROFILE_TITLE("page_alien_profile_title"),
	PAGE_ALIEN_PROFILE_H1("page_alien_profile_h1"),
	PAGE_ALIEN_PROFILE_ALIEN_IMAGE_ALT("page_alien_profile_alien_image_alt"),
	PAGE_ALIEN_PROFILE_ALIEN_DESCRIPTION("page_alien_profile_alien_description"),
	PAGE_ALIEN_PROFILE_COMMENTS("page_alien_profile_comments"),
	PAGE_ALIEN_PROFILE_NO_COMMENTS("page_alien_profile_no_comments"),
	// Banned Page
	PAGE_BANNED_TITLE("page_banned_title"),
	PAGE_BANNED_H1("page_banned_h1"),
	PAGE_BANNED_H2("page_banned_h2"),
	// Home page
	PAGE_HOME_TITLE("page_home_title"),
	PAGE_HOME_H1("page_home_h1"),
	PAGE_HOME_ALIENBAR("page_home_alienbar"),
	PAGE_HOME_CONTACT_ADMIN("page_home_contact_admin"),
	PAGE_HOME_ADMIN_EMAIL("page_home_admin_email"),
	PAGE_HOME_YEAR("page_home_year"),
	// Login page
	PAGE_LOGIN_TITLE("page_login_title"),
	PAGE_LOGIN_H1("page_login_h1"),
	// Profile page
	PAGE_PROFILE_TITLE("page_profile_title"),
	PAGE_PROFILE_UNKNOWN_ROLE("page_profile_unknown_role"),
	// Register page
	PAGE_REGISTER_TITLE("page_register_title"),
	PAGE_REGISTER_H1("page_register_h1"),
	// Admin page
	PAGE_ADMIN("page_admin_title"),
	PAGE_ADMIN_H1("page_admin_h1"),
	// admin page suggested aliens
	PAGE_ADMIN_SUGGESTED_ALIENS_TITLE("page_admin_suggested_aliens_title"),
	PAGE_ADMIN_SUGGESTED_ALIENS_H1("page_admin_suggested_aliens_h1"),
	PAGE_ADMIN_SUGGESTED_APPROVE_BUTTON("page_admin_suggested_approve_button"),
	PAGE_ADMIN_SUGGESTED_DECLINE_BUTTON("page_admin_suggested_decline_button"),
	// admin page suggested images
	PAGE_ADMIN_SUGGESTED_ALIENS_IMAGES_TITLE("page_admin_suggested_aliens_images_title"),
	PAGE_ADMIN_SUGGESTED_ALIENS_IMAGES_H1("page_admin_suggested_aliens_images_h1"),
	// Template admin profile
	TEMPLATE_ADMIN_PROFILE("template_admin_profile"),
	// Template alien rating
	TEMPLATE_ALIEN_RATING_AVERAGE_RATING("template_alien_rating_average_rating"),
	// Template comment
	TEMPLATE_COMMENT_IMAGE_ALT("template_comment_image_alt"),
	// Template nav
	TEMPLATE_NAV_ALIENS("template_nav_aliens"),
	TEMPLATE_NAV_HOME("template_nav_home"),
	TEMPLATE_NAV_SUGGEST("template_nav_suggest"),
	TEMPLATE_NAV_LOGIN("template_nav_login"),
	TEMPLATE_NAV_REGISTER("template_nav_register"),
	TEMPLATE_NAV_PROFILE("template_nav_profile"),
	TEMPLATE_NAV_ADMIN_PAGE("tamplate_nav_admin_page"),
	TEMPLATE_NAV_SUGGESTED_ALIENS("template_nav_suggested_aliens"),
	TEMPLATE_NAV_SUGGESTED_IMAGES("template_nav_suggested_images"),
	TEMPLATE_NAV_LOGOUT("template_nav_logout"),
	// Template post
	TEMPLATE_POST_IMAGE_ALT("template_post_image_alt"),
	// Template user image
	TEMPLATE_USER_IMAGE_ALT("template_user_image_alt"),
	// Template user profile
	TEMPLATE_USER_PROFILE_H1("template_user_profile_h1"),
	// Form alien create/suggest
	FORM_ALIEN_SUGGEST_LEGEND("form_alien_suggest_legend"),
	FORM_ALIEN_SUGGEST_IMAGE_LEGEND("form_alien_suggest_image_legend"),
	FORM_ALIEN_CREATE_LEGEND("form_alien_create_legend"),
	FORM_ALIEN_CREATE_NAME("form_alien_create_name"),
	FORM_ALIEN_CREATE_NAME_PLACEHOLDER("form_alien_create_name_placeholder"),
	FORM_ALIEN_CREATE_SMALL_DESCRIPTION("form_alien_create_small_description"),
	FORM_ALIEN_CREATE_SMALL_DESCRIPTION_PLACEHOLDER("form_alien_create_small_description_placeholder"),
	FORM_ALIEN_CREATE_FULL_DESCRIPTION("form_alien_create_full_description"),
	FORM_ALIEN_CREATE_FULL_DESCRIPTION_PLACEHOLDER("form_alien_create_full_description_placeholder"),
	FORM_ALIEN_CREATE_IMAGE("form_alien_create_image"),
	FORM_ALIEN_CREATE_IMAGE_LABEL("form_alien_create_image_label"),
	FORM_ALIEN_CREATE_SUBMIT("form_alien_create_submit"),
	// Form alien update
	FORM_ALIEN_UPDATE_LEGEND("form_alien_update_legend"),
	FORM_ALIEN_UPDATE_NAME("form_alien_update_name"),
	FORM_ALIEN_UPDATE_NAME_PLACEHOLDER("form_alien_update_name_placeholder"),
	FORM_ALIEN_UPDATE_SMALL_DESCRIPTION("form_alien_update_small_description"),
	FORM_ALIEN_UPDATE_SMALL_DESCRIPTION_PLACEHOLDER("form_alien_update_small_description_placeholder"),
	FORM_ALIEN_UPDATE_FULL_DESCRIPTION("form_alien_update_full_description"),
	FORM_ALIEN_UPDATE_FULL_DESCRIPTION_PLACEHOLDER("form_alien_update_full_description_placeholder"),
	FORM_ALIEN_UPDATE_IMAGE("form_alien_update_image"),
	FORM_ALIEN_UPDATE_IMAGE_LABEL("form_alien_update_image_label"),
	FORM_ALIEN_UPDATE_SUBMIT("form_alien_update_submit"),
	// Form user ban unban
	FORM_USER_BAN_UNBAN_LEGEND("form_user_ban_unban_legend"),
	FORM_USER_BAN_UNBAN_LOGIN("form_user_ban_unban_login"),
	FORM_USER_BAN_UNBAN_LOGIN_PLACEHOLDER("form_user_ban_unban_login_placeholder"),
	FORM_USER_BAN_UNBAN_DAYS_IN_BAN("form_user_ban_unban_days_in_ban"),
	FORM_USER_BAN_UNBAN_DAYS_IN_BAN_PLACEHOLDER("form_user_ban_unban_days_in_ban_placeholder"),
	FORM_USER_BAN_UNBAN_SUBMIT_BAN("form_user_ban_unban_submit_ban"),
	FORM_USER_BAN_UNBAN_SUBMIT_UNBAN("form_user_ban_unban_submit_unban"),
	CANT_FIND_SUITABLE_USER("cant_find_suitable_user"),
	
	
	// Form email update
	FORM_EMAIL_UPDATE_LEGEND("form_email_update_legend"),
	FORM_EMAIL_UPDATE_EMAIL("form_email_update_email"),
	FORM_EMAIL_UPDATE_EMAIL_PLACEHOLDER("form_email_update_email_placeholder"),
	FORM_EMAIL_UPDATE_SUBMIT("form_email_update_submit"),
	// Form image update
	FORM_IMAGE_UPDATE_LEGEND("form_image_update_legend"),
	FORM_IMAGE_UPDATE_IMAGE("form_image_update_image"),
	FORM_IMAGE_UPDATE_IMAGE_LABEL("form_image_update_image_label"),
	FORM_IMAGE_UPDATE_SUBMIT("form_image_update_submit"),
	// Form login update
	FORM_LOGIN_UPDATE_LEGEND("form_login_update_legend"),
	FORM_LOGIN_UPDATE_LOGIN("form_login_update_login"),
	FORM_LOGIN_UPDATE_LOGIN_PLACEHOLDER("form_login_update_login_placeholder"),
	FORM_LOGIN_UPDATE_SUBMIT("form_login_update_submit"),
	// Form login
	FORM_LOGIN_LEGEND("form_login_legend"),
	FORM_LOGIN_EMAIL("form_login_email"),
	FORM_LOGIN_PLACEHOLDER("form_login_placeholder"),
	FORM_LOGIN_PASSWORD("form_login_password"),
	FORM_LOGIN_PASSWORD_PLACEHOLDER("form_login_password_placeholder"),
	FORM_LOGIN_SUBMIT("form_login_submit"),
	FORM_LOGIN_NEED_AN_ACCOUNT("form_login_need_an_account"),
	FORM_LOGIN_SIGN_UP_NOW("form_login_sign_up_now"),
	// Form new comment
	FORM_NEW_COMMENT_LEGEND("form_new_comment_legend"),
	FORM_NEW_COMMENT_LABEL("form_new_comment_label"),
	FORM_NEW_COMMENT_PLACEHOLDER("form_new_comment_placeholder"),
	FORM_NEW_COMMENT_SUBMIT("form_new_comment_submit"),
	// Form password update
	FORM_PASSWORD_UPDATE_LEGEND("form_password_update_legend"),
	FORM_PASSWORD_UPDATE_PASSWORD_LABEL("form_password_update_password_label"),
	FORM_PASSWORD_UPDATE_PASSWORD_PLACEHOLDER("form_password_update_password_placeholder"),
	FORM_PASSWORD_UPDATE_PASSWORD_CONFIRMATION_LABEL("form_password_update_password_confirmation_label"),
	FORM_PASSWORD_UPDATE_PASSWORD_CONFIRMATION_PLACEHOLDER("form_password_update_password_confirmation_placeholder"),
	FORM_PASSWORD_UPDATE_SUBMIT("form_password_update_submit"),
	// Form promote demote
	FORM_PROMOTE_DEMOTE_LEGEND("form_promote_demote_legend"),
	FORM_PROMOTE_DEMOTE_LOGIN_LABEL("form_promote_demote_login_label"),
	FORM_PROMOTE_DEMOTE_LOGIN_PLACEHOLDER("form_promote_demote_login_placeholder"),
	FORM_PROMOTE_DEMOTE_SUBMIT_PROMOTE("form_promote_demote_submit_promote"),
	FORM_PROMOTE_DEMOTE_SUBMIT_DEMOTE("form_promote_demote_submit_demote"),
	// form register
	FORM_REGISTER_LEGEND("form_register_legend"),
	FORM_REGISTER_EMAIL_LABEL("form_register_email_label"),
	FORM_REGISTER_EMAIL_PLACEHOLDER("form_register_email_placeholder"),
	FORM_REGISTER_LOGIN_LABEL("form_register_login_label"),
	FORM_REGISTER_LOGIN_PLACEHOLDER("form_register_login_placeholder"),
	FORM_REGISTER_PASSWORD_LABEL("form_register_password_label"),
	FORM_REGISTER_PASSWORD_PLACEHOLDER("form_register_password_placeholder"),
	FORM_REGISTER_PASSWORD_CONFIRMATION_LABEL("form_register_password_confirmation_label"),
	FORM_REGISTER_PASSWORD_CONFIRMATION_PLACEHOLDER("form_register_password_confirmation_placeholder"),
	FORM_REGISTER_PASSWORD_SUBMIT("form_register_password_submit"),
	FORM_REGISTER_ALREADY_HAVE_AN_ACCOUNT("form_register_already_have_an_account"),
	FORM_REGISTER_SIGN_IN("form_register_sign_in"),
	
	// feedback
	STANDARD_EMAIL_FEEDBACK("standard_email_feedback"),
	STANDARD_LOGIN_FEEDBACK("standard_login_feedback"),
	STANDARD_PASSWORD_FEEDBACK("standard_password_feedback"),
	STANDARD_PASSWORD_CONFIRMATION_FEEDBACK("standard_password_confirmation_feedback"),
	STANDARD_IMAGE_FEEDBACK("standard_image_feedback"),
	STANDARD_DAYS_TO_BAN_FEEDBACK("standard_days_to_ban_feedback"),
	STANDARD_ALIEN_NAME_FEEDBACK("standard_alien_name_feedback"),
	STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK("standard_alien_small_description_feedback"),
	STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK("standard_alien_full_description_feedback"),
	STANDARD_COMMENT_FEEDBACK("standard_comment_feedback"),
	
	
	
	INTERNAL_SERVER_ERROR("internal_server_error"),
	
	NO_USER_WITH_EMAIL("no_user_with_email"),
	EMAIL_FEEDBACK_INVALID("email_feedback_invalid"),
	EMAIL_FEEDBACK_INVALID_USER_EXISTS("email_feedback_invalid_user_exists"),
	EMAIL_FEEDBACK_INVALID_AUTHORIZATION_ERROR("email_feedback_invalid_authorization_error"),
	EMAIL_PASSWORD_FEEDBACK_INVALID("email_password_feedback_invalid"),
	
	LOGIN_FEEDBACK_INVALID("login_feedback_invalid"),
	LOGIN_FEEDBACK_INVALID_USER_EXISTS("login_feedback_invalid_user_exists"),
	LOGIN_FEEDBACK_INVALID_USER_NOT_EXIST("login_feedback_invalid_user_not_exist"),
	LOGIN_FEEDBACK_INVALID_AUTHORIZATION_ERROR("login_feedback_invalid_authorization_error"),
	LOGIN_FEEDBACK_INVALID_PROMOTE_YOURSELF("login_feedback_invalid_promote_yourself"),
	LOGIN_FEEDBACK_INVALID_DEMOTE_YOURSELF("login_feedback_invalid_demote_yourself"),
	LOGIN_FEEDBACK_INVALID_CAN_NOT_FIND_USER_FOR_PROMOTING("login_feedback_invalid_can_not_find_user_for_promoting"),
	LOGIN_FEEDBACK_INVALID_CAN_NOT_FIND_ADMIN_FOR_DEMOTING("login_feedback_invalid_can_not_find_admin_for_demoting"),

	PASSWORD_FEEDBACK_INVALID("password_feedback_invalid"),
	PASSWORD_CONFIRMATION_FEEDBACK_INVALID("password_confirmation_feedback_invalid"),
	PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL("password_feedback_invalid_passwords_are_not_equal"),
	PASSWORD_FEEDBACK_INVALID_NO_USER_WITH_ID("password_feedback_invalid_no_user_with_id"),
	
	IMAGE_FEEDBACK_INVALID("image_feedback_invalid"),
	IMAGE_FEEDBACK_INVALID_EXTENSION("image_feedback_invalid_extension"),
	IMAGE_FEEDBACK_INVALID_SIZE("image_feedback_invalid_size"),
	
	DAYS_TO_BAN_FEEDBACK_INVALID("days_to_ban_feedback_invalid"),
	
	ALIEN_NAME_FEEDBACK_INVALID_ALREADY_EXISTS("alien_name_feedback_invalid_already_exists"),
	ALIEN_NAME_FEEDBACK_INVALID_DOES_NOT_EXIST("alien_name_feedback_invalid_does_not_exist"),
	ALIEN_NAME_FEEDBACK_INVALID("alien_name_feedback_invalid"),
	ALIEN_SMALL_DESCRIPTION_FEEDBACK_INVALID("alien_small_description_feedback_invalid"),
	ALIEN_FULL_DESCRIPTION_FEEDBACK_INVALID("alien_full_description_feedback_invalid"),
	
	COMMENT_FEEDBACK_INVALID("comment_feedback_invalid"),
	
	// email
	EMAIL_SUBJECT_REGISTER("email_subject_register"),
	EMAIL_SUBJECT_CHANGE_EMAIL("email_subject_change_email"),
	EMAIL_CONTENT_REGISTER("email_content_register"),
	EMAIL_CONTENT_CHANGE_EMAIL("email_content_change_email"),
	CHECK_YOUR_EMAIL("check_your_email"),
	
	// error pages
	ERROR_PAGE("error_page"),
	ERROR_PAGE_400("error_page_400"),
	ERROR_PAGE_400_BAD_REQUEST("error_page_400_bad_request"),
	ERROR_PAGE_403("error_page_403"),
	ERROR_PAGE_403_FORBIDDEN("error_page_403_forbidden"),
	ERROR_PAGE_404("error_page_404"),
	ERROR_PAGE_404_NOT_FOUND("error_page_404_not_found"),
	ERROR_PAGE_500("error_page_500"),
	ERROR_PAGE_500_INTERNAL_SERVER_ERROR("error_page_500_internal_server_error"),
	
	
	EMPTY_MESSAGE("empty_message");
	
	private String value;
	
	private LocaleKey(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

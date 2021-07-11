package by.shyshaliaksey.webproject.controller;

public enum LocaleKey implements EnumValue {

	// About page
	PAGE_ABOUT_TITLE("page_about_title"),
	PAGE_ABOUT_H1("page_about_h1"),
	// Alien profile page
	PAGE_ALIEN_PROFILE_TITLE("page_alien_profile_title"),
	PAGE_ALIEN_PROFILE_H1("page_alien_profile_h1"),
	PAGE_ALIEN_PROFILE_ALIEN_IMAGE_ALT("image_alt"),
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
	// Template admin profile
	TEMPLATE_ADMIN_PROFILE("template_admin_profile"),
	// Template alien rating
	TEMPLATE_ALIEN_RATING_AVERAGE_RATING("template_alien_rating_average_rating"),
	// Template comment
	TEMPLATE_COMMENT_IMAGE_ALT("template_comment_image_alt"),
	// Template nav
	TEMPLATE_NAV_ALIENS("template_nav_aliens"),
	TEMPLATE_NAV_HOME("template_nav_home"),
	TEMPLATE_NAV_ABOUT("template_nav_about"),
	TEMPLATE_NAV_LOGIN("template_nav_login"),
	TEMPLATE_NAV_REGISTER("template_nav_register"),
	TEMPLATE_NAV_PROFILE("template_nav_profile"),
	TEMPLATE_NAV_LOGOUT("template_nav_logout"),
	// Template post
	TEMPLATE_POST_IMAGE_ALT("template_post_image_alt"),
	// Template user image
	TEMPLATE_USER_IMAGE_ALT("template_user_image_alt"),
	// Template user profile
	TEMPLATE_USER_PROFILE_H1("template_user_profile_h1"),
	// Form alien create
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
	FORM_REGISTER_SIGN_IN("form_register_sign_in");
	
	private String value;
	
	private LocaleKey(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

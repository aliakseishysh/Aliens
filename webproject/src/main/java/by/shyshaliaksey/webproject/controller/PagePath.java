package by.shyshaliaksey.webproject.controller;

public enum PagePath implements EnumValue  {
	
	// Pages
	PAGE_BANNED_JSP("/WEB-INF/jsp/page_banned.jsp"),
	PAGE_PROFILE_JSP("/WEB-INF/jsp/page_profile.jsp"),
	PAGE_ABOUT_JSP("/WEB-INF/jsp/page_about.jsp"),
	PAGE_ALIEN_PROFILE_JSP("/WEB-INF/jsp/page_alien_profile.jsp"),
	PAGE_HOME_JSP("/WEB-INF/jsp/page_home.jsp"),
	PAGE_LOGIN_JSP("/WEB-INF/jsp/page_login.jsp"),
	PAGE_REGISTER_JSP("/WEB-INF/jsp/page_register.jsp"),
	PAGE_FORGOT_PASSWORD_JSP("/WEB-INF/jsp/page_forgot_password.jsp"),
	// Templates
	TEMPLATE_ADMIN_PROFILE_JSP("/WEB-INF/jsp/templates/template_admin_profile.jsp"),
	TEMPLATE_NAV_JSP("/WEB-INF/jsp/templates/template_nav.jsp"),
	TEMPLATE_POST_JSP("/WEB-INF/jsp/templates/template_post.jsp"),
	TEMPLATE_USER_IMAGE_JSP("/WEB-INF/jsp/templates/template_user_image.jsp"),
	TEMPLATE_USER_PROFILE_JSP("/WEB-INF/jsp/templates/template_user_profile.jsp"),
	TEMPLATE_ALIEN_RATING_JSP("/WEB-INF/jsp/templates/template_alien_rating.jsp"),
	TEMPLATE_COMMENT_JSP("/WEB-INF/jsp/templates/template_comment.jsp"),
	TEMPLATE_ALIEN_PROFILE_PAGINATION("/WEB-INF/jsp/templates/template_alien_profile_pagination.jsp"),
	TEMPLATE_PAGE_HOME_PAGINATION("/WEB-INF/jsp/templates/template_page_home_pagination.jsp"),
	// Error Pages
	ERROR_PAGE_403_JSP("/WEB-INF/jsp/error_pages/error_page_403.jsp"),
	ERROR_PAGE_404_JSP("/WEB-INF/jsp/error_pages/error_page_404.jsp"),
	ERROR_PAGE_500_JSP("/WEB-INF/jsp/error_pages/error_page_500.jsp"),
	// FORMS
	FORM_ALIEN_UPDATE_JSP("/WEB-INF/jsp/templates/forms/form_alien_update.jsp"),
	FORM_ALIEN_CREATE_JSP("/WEB-INF/jsp/templates/forms/form_alien_create.jsp"),
	FORM_BAN_UNBAN_USER_JSP("/WEB-INF/jsp/templates/forms/form_ban_unban.jsp"),
	FORM_UPDATE_EMAIL_JSP("/WEB-INF/jsp/templates/forms/form_email_update.jsp"),
	FORM_UPDATE_IMAGE_JSP("/WEB-INF/jsp/templates/forms/form_image_update.jsp"),
	FORM_UPDATE_LOGIN_JSP("/WEB-INF/jsp/templates/forms/form_login_update.jsp"),
	FORM_LOGIN_JSP("/WEB-INF/jsp/templates/forms/form_login.jsp"),
	FORM_UPDATE_PASSWORD_JSP("/WEB-INF/jsp/templates/forms/form_password_update.jsp"),
	FORM_PROMOTE_DEMOTE_JSP("/WEB-INF/jsp/templates/forms/form_promote_demote.jsp"),
	FORM_REGISTER("/WEB-INF/jsp/templates/forms/form_register.jsp"),
	FORM_NEW_COMMENT("/WEB-INF/jsp/templates/forms/form_new_comment.jsp"),
	FORM_RESTORE_PASSWORD_REQUEST_LINK("/WEB-INF/jsp/templates/forms/form_restore_password_request_link.jsp");
	
	private String value;
	
	private PagePath(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

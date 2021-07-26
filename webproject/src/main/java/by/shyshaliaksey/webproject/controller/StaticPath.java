package by.shyshaliaksey.webproject.controller;

/**
 * Enum with different static paths: images, folders and jsp pages
 */
public enum StaticPath implements EnumValue {

	// Images
	IMAGE_DEFAULT("/images/standard.png"),
	// Folders
	ROOT_FOLDER(""), IMAGE_FOLDER("/images/"), ALIEN_IMAGE_FOLDER("/images/alien/"),
	PROFILE_IMAGE_FOLDER("/images/profile/"),
	// Pages
	PAGE_BANNED_JSP("/WEB-INF/jsp/page_banned.jsp"), PAGE_PROFILE_JSP("/WEB-INF/jsp/page_profile.jsp"),
	PAGE_ABOUT_JSP("/WEB-INF/jsp/page_about.jsp"), PAGE_ALIEN_PROFILE_JSP("/WEB-INF/jsp/page_alien_profile.jsp"),
	PAGE_ADMIN_FUNCTIONAL_JSP("/WEB-INF/jsp/page_admin_functional.jsp"), PAGE_HOME_JSP("/WEB-INF/jsp/page_home.jsp"),
	PAGE_LOGIN_JSP("/WEB-INF/jsp/page_login.jsp"), PAGE_REGISTER_JSP("/WEB-INF/jsp/page_register.jsp"),
	PAGE_SUGGEST_ALIEN_JSP("/WEB-INF/jsp/page_user_suggest_alien.jsp"),
	PAGE_ADMIN_SUGGESTED_ALIENS_JSP("/WEB-INF/jsp/page_admin_suggested_aliens.jsp"),
	PAGE_ADMIN_SUGGESTED_ALIENS_INAGES_JSP("/WEB-INF/jsp/page_admin_suggested_aliens_images.jsp"),
	// Error Pages
	ERROR_PAGE_400_JSP("/WEB-INF/jsp/error_pages/error_page_400.jsp"),
	ERROR_PAGE_403_JSP("/WEB-INF/jsp/error_pages/error_page_403.jsp"),
	ERROR_PAGE_404_JSP("/WEB-INF/jsp/error_pages/error_page_404.jsp"),
	ERROR_PAGE_500_JSP("/WEB-INF/jsp/error_pages/error_page_500.jsp"),
	// Templates
	TEMPLATE_ADMIN_PROFILE_JSP("/WEB-INF/jsp/templates/template_admin_profile.jsp"),
	TEMPLATE_NAV_JSP("/WEB-INF/jsp/templates/template_nav.jsp"),
	TEMPLATE_POST_JSP("/WEB-INF/jsp/templates/template_post.jsp"),
	TEMPLATE_USER_IMAGE_JSP("/WEB-INF/jsp/templates/template_user_image.jsp"),
	TEMPLATE_USER_PROFILE_JSP("/WEB-INF/jsp/templates/template_user_profile.jsp"),
	TEMPLATE_ALIEN_RATING_JSP("/WEB-INF/jsp/templates/template_alien_rating.jsp"),
	TEMPLATE_ALIEN_JSP("/WEB-INF/jsp/templates/template_alien.jsp"),
	TEMPLATE_COMMENT_JSP("/WEB-INF/jsp/templates/template_comment.jsp"),
	TEMPLATE_ALIENBAR_JSP("/WEB-INF/jsp/templates/template_alienbar.jsp"),
	TEMPLATE_USER_SUGGEST_ALIEN("/WEB-INF/jsp/templates/template_user_suggest_alien.jsp"),
	TEMPLATE_CAROUSEL("/WEB-INF/jsp/templates/template_carousel.jsp"),
	TEMPLATE_TOAST_JSP("/WEB-INF/jsp/templates/template_toast.jsp"),
	TEMPLATE_PAGINATION_JSP("/WEB-INF/jsp/templates/template_pagination.jsp"),
	// Forms
	FORM_ALIEN_UPDATE_INFO_JSP("/WEB-INF/jsp/templates/forms/form_alien_update_info.jsp"),
	FORM_ALIEN_UPDATE_IMAGE_JSP("/WEB-INF/jsp/templates/forms/form_alien_update_image.jsp"),
	FORM_ALIEN_CREATE_JSP("/WEB-INF/jsp/templates/forms/form_alien_create.jsp"),
	FORM_ALIEN_SUGGEST_JSP("/WEB-INF/jsp/templates/forms/form_alien_suggest.jsp"),
	FORM_ALIEN_SUGGEST_IMAGE_JSP("/WEB-INF/jsp/templates/forms/form_alien_image_suggest.jsp"),
	FORM_BAN_UNBAN_USER_JSP("/WEB-INF/jsp/templates/forms/form_ban_unban.jsp"),
	FORM_UPDATE_EMAIL_JSP("/WEB-INF/jsp/templates/forms/form_email_update.jsp"),
	FORM_UPDATE_IMAGE_JSP("/WEB-INF/jsp/templates/forms/form_image_update.jsp"),
	FORM_UPDATE_LOGIN_JSP("/WEB-INF/jsp/templates/forms/form_login_update.jsp"),
	FORM_LOGIN_JSP("/WEB-INF/jsp/templates/forms/form_login.jsp"),
	FORM_UPDATE_PASSWORD_JSP("/WEB-INF/jsp/templates/forms/form_password_update.jsp"),
	FORM_PROMOTE_DEMOTE_JSP("/WEB-INF/jsp/templates/forms/form_promote_demote.jsp"),
	FORM_REGISTER("/WEB-INF/jsp/templates/forms/form_register.jsp"),
	FORM_NEW_COMMENT("/WEB-INF/jsp/templates/forms/form_new_comment.jsp");

	private String value;

	private StaticPath(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}

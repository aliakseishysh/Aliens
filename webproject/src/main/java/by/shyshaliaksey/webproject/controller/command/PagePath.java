package by.shyshaliaksey.webproject.controller.command;

public enum PagePath implements EnumValue  {
	
	INDEX_JSP("/index.jsp"),
	NAV_JSP("/WEB-INF/jsp/nav.jsp"),
	HOME_JSP("/WEB-INF/jsp/home.jsp"),
	POST_JSP("/WEB-INF/jsp/post.jsp"),
	ABOUT_JSP("/WEB-INF/jsp/about.jsp"),
	LOGIN_JSP("/WEB-INF/jsp/login.jsp"),
	REGISTER_JSP("/WEB-INF/jsp/register.jsp"),
	PROFILE_JSP("/jsp/profile_page.jsp"),
	USER_IMAGE_JSP("/WEB-INF/jsp/user_image.jsp"),
	USER_PROFILE_JSP("/WEB-INF/jsp/user_profile.jsp"),
	ADMIN_PROFILE_JSP("/WEB-INF/jsp/admin_profile.jsp"),
	ALIEN_PROFILE_JSP("/WEB-INF/jsp/alien_profile.jsp"),
	SESSION_VARIABLES_JSP("/WEB-INF/jsp/session_variables.jsp"),
	ERROR_PAGE_JSP("/error_page.jsp"),
	UPDATE_EMAIL_FORM_JSP("/WEB-INF/jsp/forms/email_update.jsp"),
	UPDATE_LOGIN_FORM_JSP("/WEB-INF/jsp/forms/login_update.jsp"),
	UPDATE_IMAGE_FORM_JSP("/WEB-INF/jsp/forms/image_update.jsp"),
	UPDATE_PASSWORD_FORM_JSP("/WEB-INF/jsp/forms/password_update.jsp"),
	BAN_UNBAN_USER_FORM_JSP("/WEB-INF/jsp/forms/ban_unban.jsp");
	
	private String value;
	
	private PagePath(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

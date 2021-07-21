package by.shyshaliaksey.webproject.controller;

public enum FilePath implements EnumValue  {

	IMAGE_DEFAULT("/images/standard.png"),
	CSS_MAIN("/css/main.css"),
	CSS_RAITING("/css/raiting.css"),
	CSS_CUSTOM_FILE("/css/custom_file.css"),
	CSS_COMMENT("/css/comment.css"),
	CSS_CAROUSEL("/css/carousel.css"),
	CSS_SUGGESTED_IMAGE("/css/suggested_image.css"),
	JS_PAGINATION("/js/pagination.js"),
	JS_RAITING("/js/raiting.js"),
	JS_HOME("/js/home.js"),
	JS_LOGIN("/js/login.js"),
	JS_NAV("/js/nav.js"),
	JS_REGISTER("/js/register.js"),
	JS_USER_PROFILE("/js/user_profile.js"),
	JS_ADMIN_PROFILE("/js/admin_profile.js"),
	JS_ALIEN_PROFILE("/js/alien_profile.js"),
	JS_ALIEN_SUGGEST("/js/alien_suggest.js"),
	JS_SUGGESTED_ALIENS("/js/admin_page_suggested_aliens.js"),
	JS_SUGGESTED_ALIENS_IMAGES("/js/admin_page_suggested_aliens_images.js"),
	JS_UTIL("/js/util.js"),
	JS_CAROUSEL("/js/carousel.js");
	// TODO JS_RESTORE_PASSWORD_REQUEST_LINK("/js/restore_password_request_link.js");
	
	private String value;
	
	private FilePath(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

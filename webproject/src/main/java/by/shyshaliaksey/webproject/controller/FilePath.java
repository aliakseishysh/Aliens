package by.shyshaliaksey.webproject.controller;

public enum FilePath implements EnumValue  {

	IMAGE_DEFAULT("/images/standard.png"),
	CSS_MAIN("/css/main.css"),
	CSS_RAITING("/css/raiting.css"),
	JS_RAITING("/js/raiting.js"),
	JS_HOME("/js/home.js"),
	JS_LOGIN("/js/login.js"),
	JS_REGISTER("/js/register.js"),
	JS_USER_PROFILE("/js/user_profile.js"),
	JS_ADMIN_PROFILE("/js/admin_profile.js"),
	JS_ALIEN_PROFILE("/js/alien_profile.js");
	
	private String value;
	
	private FilePath(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

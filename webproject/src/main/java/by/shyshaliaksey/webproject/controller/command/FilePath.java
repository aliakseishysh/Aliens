package by.shyshaliaksey.webproject.controller.command;

public enum FilePath {

	IMAGE_DEFAULT("/images/standard.png"),
	CSS_MAIN("/css/main.css"),
	CSS_RAITING("/css/raiting.css"),
	JS_RAITING("/js/raiting.js"),
	JS_USER_PROFILE("/js/user_profile.js");
	
	private String value;
	
	private FilePath(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}

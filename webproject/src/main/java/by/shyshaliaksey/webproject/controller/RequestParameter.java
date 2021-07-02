package by.shyshaliaksey.webproject.controller;

public enum RequestParameter implements EnumValue  {

	COMMAND("command"),
	ALIEN_ID("alien-id"),
	ALIEN_NAME("alienName"),
	ALIEN_SMALL_DESCRIPTION("alienSmallDescription"),
	ALIEN_FULL_DESCRIPTION("alienFullDescription"),
	ALIEN_NEW_IMAGE("alienNewImage"),
	RATING_VALUE("ratingValue"),
	EMAIL("email"),
	NEW_EMAIL("new_email"),
	LOGIN("login"),
	NEW_LOGIN("new_login"),
	USER_ID("user-id"),
	PASSWORD("password"),
	PASSWORD_CONFIRM("password_confirm"),
	IMAGE("image"),
	NEW_IMAGE("new_image"),
	DAYS_TO_BAN("days_to_ban"),
	NEW_COMMENT("new-comment"),
	COMMENT_ID("comment-id"),
	PAGE("page");

	private String value;
	
	private RequestParameter(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	public static RequestParameter fromString(String parameterName) {
        for (RequestParameter parameterValue : RequestParameter.values()) {
            if (parameterValue.getValue().equals(parameterName)) {
                return parameterValue;
            }
        }
        return null;
    }
	
}

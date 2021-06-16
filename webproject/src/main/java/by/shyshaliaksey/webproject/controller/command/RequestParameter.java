package by.shyshaliaksey.webproject.controller.command;

public enum RequestParameter {

	COMMAND("command"),
	ALIEN_ID("alien-id"),
	ALIEN_NAME("alienName"),
	RATING_VALUE("ratingValue"),
	EMAIL("email"),
	LOGIN("login"),
	PASSWORD("password"),
	PASSWORD_CONFIRM("password_confirm");

	private String value;
	
	private RequestParameter(String value) {
		this.value = value;
	}
	
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

package by.shyshaliaksey.webproject.controller.command;

public enum CommandValue {

	OPEN_HOME_PAGE("home"),
	OPEN_ABOUT_PAGE("about"),
	OPEN_LOGIN_PAGE("login"),
	OPEN_REGISTER_PAGE("register"),
	OPEN_USER_PROFILE_PAGE("user-profile"),
	OPEN_ALIEN_PROFILE_PAGE("alien-profile"),
	REGISTER_USER("register-user"),
	LOGIN_USER("login-user"),
	LOGOUT_USER("logout-user"),
	UPDATE_RATING("update-alien-rating"),
	UPDATE_USER_INFO("update-user-info"),
	UPDATE_USER_IMAGE("update-user-image"),
	UPDATE_USER_PASSWORD("update-password"),
	FIND_USER_RATE("update-user-rate"),
	FORGOT_PASSWORD("forgot-password");

	private String value;
	
	private CommandValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static CommandValue fromString(String commandName) {
        for (CommandValue commandValue : CommandValue.values()) {
            if (commandValue.getValue().equals(commandName)) {
                return commandValue;
            }
        }
        return null;
    }
	
}

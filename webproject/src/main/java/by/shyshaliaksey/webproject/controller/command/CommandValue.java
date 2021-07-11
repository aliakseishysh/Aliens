package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.EnumValue;

public enum CommandValue implements EnumValue {

	OPEN_HOME_PAGE("home"),
	OPEN_ABOUT_PAGE("about"),
	OPEN_LOGIN_PAGE("login"),
	OPEN_REGISTER_PAGE("register"),
	OPEN_USER_PROFILE_PAGE("user-profile"),
	OPEN_SERVER_ERROR_PAGE("server-error-page"),
	CHANGE_LOCALE("change-locale"),
	OPEN_404_ERROR_PAGE("404-error-page"),
	OPEN_BANNED_PAGE("banned-page"),
	OPEN_ALIEN_PROFILE_PAGE("alien-profile"),
	OPEN_FORGOT_PASSWORD_PAGE("forgot-password"),
	LOAD_EMAIL_UPDATE_FORM("email-update-form"),
	LOAD_LOGIN_UPDATE_FORM("login-update-form"),
	LOAD_IMAGE_UPDATE_FORM("image-update-form"),
	LOAD_PASSWORD_UPDATE_FORM("password-update-form"),
	LOAD_ALIEN_UPDATE_FORM("alien-update-form"),
	LOAD_ALIEN_CREATE_FORM("alien-create-form"),
	LOAD_BAN_UNBAN_FORM("ban-unban-form"),
	LOAD_PROMOTE_DEMOTE_FORM("promote-denote-form"),
	LOAD_USER_IMAGE("load-user-image"),
	REGISTER_USER("register-user"),
	LOGIN_USER("login-user"),
	LOGOUT_USER("logout-user"),
	UPDATE_RATING("update-alien-rating"),
	UPDATE_USER_EMAIL("update-user-email"),
	UPDATE_USER_LOGIN("update-user-login"),
	UPDATE_USER_IMAGE("update-user-image"),
	UPDATE_USER_PASSWORD("update-password"),
	FIND_USER_RATE("update-user-rate"),
	BAN_USER("ban-user"),
	UNBAN_USER("unban-user"),
	PROMOTE_USER("pronote-user"),
	DEMOTE_ADMIN("denote-admin"),
	UPDATE_ALIEN("update-alien"),
	ADD_NEW_ALIEN("add-new-alien"),
	ADD_NEW_COMMENT("add-new-comment"),
	DELETE_COMMENT("delete-comment");
	// REQUEST_RESTORE_PASSWORD_TOKEN("request-restore-password-token"); TODO if have time

	private String value;
	
	private CommandValue(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	public static CommandValue fromString(String commandName) {
		CommandValue result = OPEN_404_ERROR_PAGE;
        for (CommandValue commandValue : CommandValue.values()) {
            if (commandValue.getValue().equals(commandName)) {
                result = commandValue;
            }
        }
        return result;
    }
	
}

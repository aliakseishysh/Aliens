package by.shyshaliaksey.webproject.controller.command;

public class CommandFactory {

	private CommandFactory() {
	}

	public static Command defineCommand(String commandName) {
		CommandValue commandValue = CommandValue.fromString(commandName);
		Command command = switch (commandValue) {
		case OPEN_HOME_PAGE -> CommandValue.OPEN_HOME_PAGE.getCommand();
		case OPEN_ABOUT_PAGE -> CommandValue.OPEN_ABOUT_PAGE.getCommand();
		case OPEN_LOGIN_PAGE -> CommandValue.OPEN_LOGIN_PAGE.getCommand();
		case OPEN_REGISTER_PAGE -> CommandValue.OPEN_REGISTER_PAGE.getCommand();
		case OPEN_USER_PROFILE_PAGE -> CommandValue.OPEN_USER_PROFILE_PAGE.getCommand();
		case OPEN_ALIEN_PROFILE_PAGE -> CommandValue.OPEN_ALIEN_PROFILE_PAGE.getCommand();
		case OPEN_ADMIN_FUNCTIONAL_PAGE -> CommandValue.OPEN_ADMIN_FUNCTIONAL_PAGE.getCommand();
		case OPEN_ADMIN_SUGGESTED_ALIENS_PAGE -> CommandValue.OPEN_ADMIN_SUGGESTED_ALIENS_PAGE.getCommand();
		case OPEN_USER_SUGGEST_ALIEN_PAGE -> CommandValue.OPEN_USER_SUGGEST_ALIEN_PAGE.getCommand();
		case OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE -> CommandValue.OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE.getCommand();
		case OPEN_BANNED_PAGE -> CommandValue.OPEN_BANNED_PAGE.getCommand();
		case OPEN_SERVER_ERROR_PAGE -> CommandValue.OPEN_SERVER_ERROR_PAGE.getCommand();
		case OPEN_404_ERROR_PAGE -> CommandValue.OPEN_404_ERROR_PAGE.getCommand();
		
		case ADMIN_APPROVE_ALIEN -> CommandValue.ADMIN_APPROVE_ALIEN.getCommand();
		case ADMIN_DECLINE_ALIEN -> CommandValue.ADMIN_DECLINE_ALIEN.getCommand();
		case ADMIN_APPROVE_ALIEN_IMAGE -> CommandValue.ADMIN_APPROVE_ALIEN_IMAGE.getCommand();
		case ADMIN_DECLINE_ALIEN_IMAGE -> CommandValue.ADMIN_DECLINE_ALIEN_IMAGE.getCommand();
		case SUGGEST_ALIEN -> CommandValue.SUGGEST_ALIEN.getCommand();
		case SUGGEST_ALIEN_IMAGE -> CommandValue.SUGGEST_ALIEN_IMAGE.getCommand();
		case CHANGE_LOCALE -> CommandValue.CHANGE_LOCALE.getCommand();
		case REGISTER_USER -> CommandValue.REGISTER_USER.getCommand();
		case LOGIN_USER -> CommandValue.LOGIN_USER.getCommand();
		case LOGOUT_USER -> CommandValue.LOGOUT_USER.getCommand();
		case UPDATE_RATING -> CommandValue.UPDATE_RATING.getCommand();
		case UPDATE_USER_EMAIL -> CommandValue.UPDATE_USER_EMAIL.getCommand();
		case UPDATE_USER_LOGIN -> CommandValue.UPDATE_USER_LOGIN.getCommand();
		case UPDATE_USER_IMAGE -> CommandValue.UPDATE_USER_IMAGE.getCommand();
		case UPDATE_USER_PASSWORD -> CommandValue.UPDATE_USER_PASSWORD.getCommand();
		case FIND_USER_RATE -> CommandValue.FIND_USER_RATE.getCommand();
		case BAN_USER -> CommandValue.BAN_USER.getCommand();
		case UNBAN_USER -> CommandValue.UNBAN_USER.getCommand();
		case PROMOTE_USER -> CommandValue.PROMOTE_USER.getCommand();
		case DEMOTE_ADMIN -> CommandValue.DEMOTE_ADMIN.getCommand();
		case UPDATE_ALIEN -> CommandValue.UPDATE_ALIEN.getCommand();
		case ADD_NEW_ALIEN -> CommandValue.ADD_NEW_ALIEN.getCommand();
		case ADD_NEW_COMMENT -> CommandValue.ADD_NEW_COMMENT.getCommand();
		case DELETE_COMMENT -> CommandValue.DELETE_COMMENT.getCommand();
		//TODO case OPEN_FORGOT_PASSWORD_PAGE -> new DELETE_POTENTIALYOpenForgotPasswordPageCommand();
		// case REQUEST_RESTORE_PASSWORD_TOKEN -> new RequestRestorePasswordTokenCommand(); TODO if have time
		default -> throw new IllegalArgumentException("Value is not present in CommandFactory: " + commandName);
		};
		return command;
	}

}

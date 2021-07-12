package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.command.impl.admin.AddNewAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.BanUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.DemoteAdminCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.PromoteUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.UnbanUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.UpdateAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.locale.ChangeLocaleCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.Open404ErrorPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAboutPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAlienProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenBannedPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenHomePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenLoginPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenRegisterPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenServerErrorPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenUserProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.FindUserRateCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.UpdateRatingCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.AddNewCommentCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.DeleteCommentCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LoginUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LogoutUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.RegisterUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserEmailCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserLoginCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserPasswordCommand;

public enum CommandValue implements EnumValue {

	OPEN_HOME_PAGE("home", new OpenHomePageCommand()),
	OPEN_ABOUT_PAGE("about", new OpenAboutPageCommand()),
	OPEN_LOGIN_PAGE("login", new OpenLoginPageCommand()),
	OPEN_REGISTER_PAGE("register", new OpenRegisterPageCommand()),
	OPEN_USER_PROFILE_PAGE("user-profile", new OpenUserProfilePageCommand()),
	OPEN_ALIEN_PROFILE_PAGE("alien-profile", new OpenAlienProfilePageCommand()),
	OPEN_BANNED_PAGE("banned-page", new OpenBannedPageCommand()),
	OPEN_SERVER_ERROR_PAGE("server-error-page", new OpenServerErrorPageCommand()),
	OPEN_404_ERROR_PAGE("404-error-page", new Open404ErrorPageCommand()),
	CHANGE_LOCALE("change-locale", new ChangeLocaleCommand()),
	// OPEN_FORGOT_PASSWORD_PAGE("forgot-password"),
	REGISTER_USER("register-user", new RegisterUserCommand()),
	LOGIN_USER("login-user", new LoginUserCommand()),
	LOGOUT_USER("logout-user", new LogoutUserCommand()),
	UPDATE_RATING("update-alien-rating", new UpdateRatingCommand()),
	UPDATE_USER_EMAIL("update-user-email", new UpdateUserEmailCommand()),
	UPDATE_USER_LOGIN("update-user-login", new UpdateUserLoginCommand()),
	UPDATE_USER_IMAGE("update-user-image", new UpdateUserImageCommand()),
	UPDATE_USER_PASSWORD("update-password", new UpdateUserPasswordCommand()),
	FIND_USER_RATE("update-user-rate", new FindUserRateCommand()),
	BAN_USER("ban-user", new BanUserCommand()),
	UNBAN_USER("unban-user", new UnbanUserCommand()),
	PROMOTE_USER("pronote-user", new PromoteUserCommand()),
	DEMOTE_ADMIN("denote-admin", new DemoteAdminCommand()),
	UPDATE_ALIEN("update-alien", new UpdateAlienCommand()),
	ADD_NEW_ALIEN("add-new-alien", new AddNewAlienCommand()),
	ADD_NEW_COMMENT("add-new-comment", new AddNewCommentCommand()),
	DELETE_COMMENT("delete-comment", new DeleteCommentCommand());
	// REQUEST_RESTORE_PASSWORD_TOKEN("request-restore-password-token"); TODO if have time

	private String value;
	private Command command;
	
	private CommandValue(String value, Command command) {
		this.value = value;
		this.command = command;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	public Command getCommand() {
		return command;
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

package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.command.impl.admin.AddNewAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.AdminApproveAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.AdminApproveAlienImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.AdminDeclineAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.AdminDeclineAlienImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.BanUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.DemoteAdminCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.PromoteUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.UnbanUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.UpdateAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.locale.ChangeLocaleCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.Open404ErrorPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAboutPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAdminFunctionalPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAdminSuggestedAliensImagesPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAdminSuggestedAliensPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAlienProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenBannedPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenHomePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenLoginPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenRegisterPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenServerErrorPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenSuggestAlienPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenUserProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.FindUserRateCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.UpdateRatingCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.AddNewCommentCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.DeleteCommentCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LoginUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LogoutUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.RegisterUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.SuggestNewAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.SuggestNewAlienImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserEmailCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserLoginCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserPasswordCommand;

public enum CommandValue implements EnumValue {

	OPEN_HOME_PAGE("home-page", new OpenHomePageCommand()),
	OPEN_ABOUT_PAGE("about-page", new OpenAboutPageCommand()),
	OPEN_LOGIN_PAGE("login-page", new OpenLoginPageCommand()),
	OPEN_REGISTER_PAGE("register-page", new OpenRegisterPageCommand()),
	OPEN_USER_PROFILE_PAGE("user-profile-page", new OpenUserProfilePageCommand()),
	OPEN_ALIEN_PROFILE_PAGE("alien-profile-page", new OpenAlienProfilePageCommand()),
	OPEN_ADMIN_FUNCTIONAL_PAGE("admin-functional-page", new OpenAdminFunctionalPageCommand()),
	OPEN_ADMIN_SUGGESTED_ALIENS_PAGE("suggested-aliens-page", new OpenAdminSuggestedAliensPageCommand()),
	OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE("suggested-aliens-images-page", new OpenAdminSuggestedAliensImagesPageCommand()),
	OPEN_USER_SUGGEST_ALIEN_PAGE("suggest-alien-page", new OpenSuggestAlienPageCommand()),
	OPEN_BANNED_PAGE("banned-page", new OpenBannedPageCommand()),
	OPEN_SERVER_ERROR_PAGE("server-error-page", new OpenServerErrorPageCommand()),
	OPEN_404_ERROR_PAGE("404-error-page", new Open404ErrorPageCommand()),
	
	ADMIN_APPROVE_ALIEN("admin-approve-alien", new AdminApproveAlienCommand()),
	ADMIN_DECLINE_ALIEN("admin-decline-alien", new AdminDeclineAlienCommand()),
	ADMIN_APPROVE_ALIEN_IMAGE("admin-approve-alien-image", new AdminApproveAlienImageCommand()),
	ADMIN_DECLINE_ALIEN_IMAGE("admin-decline-alien-image", new AdminDeclineAlienImageCommand()),
	SUGGEST_ALIEN("suggest-alien", new SuggestNewAlienCommand()),
	SUGGEST_ALIEN_IMAGE("suggest-alien-image", new SuggestNewAlienImageCommand()),
	CHANGE_LOCALE("change-locale", new ChangeLocaleCommand()),
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
	// OPEN_FORGOT_PASSWORD_PAGE("forgot-password"), TODO if have time

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

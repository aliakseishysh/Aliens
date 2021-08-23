package by.shyshaliaksey.aliens.controller.command;

import by.shyshaliaksey.aliens.controller.EnumValue;
import by.shyshaliaksey.aliens.controller.command.impl.admin.AddNewAlienCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.AdminApproveAlienCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.AdminApproveAlienImageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.AdminDeclineAlienCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.AdminDeclineAlienImageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.BanUserCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.DemoteAdminCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.PromoteUserCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.UnbanUserCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.UpdateAlienInfoCommand;
import by.shyshaliaksey.aliens.controller.command.impl.guest.LoginUserCommand;
import by.shyshaliaksey.aliens.controller.command.impl.guest.RegisterUserCommand;
import by.shyshaliaksey.aliens.controller.command.impl.admin.UpdateAlienImageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.locale.ChangeLocaleCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenAdminFunctionalPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenAdminSuggestedAliensImagesPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenAdminSuggestedAliensPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenAlienProfilePageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenBannedPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenHomePageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenLoginPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenRegisterPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenSuggestAlienPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.OpenUserProfilePageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.error.Open400ErrorPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.error.Open403ErrorPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.error.Open404ErrorPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.open.error.Open500ErrorPageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.rating.FindUserRateCommand;
import by.shyshaliaksey.aliens.controller.command.impl.rating.UpdateRatingCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.AddNewCommentCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.DeleteCommentCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.LogoutUserCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.SuggestNewAlienCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.SuggestNewAlienImageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.UpdateUserEmailCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.UpdateUserImageCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.UpdateUserLoginCommand;
import by.shyshaliaksey.aliens.controller.command.impl.user.UpdateUserPasswordCommand;

/**
 * Enum {@code CommandDefiner} designed for storing command object for purpose
 * of not creating new every time at request. And for defining command from
 * string.
 * 
 * @author Aliaksey Shysh
 *
 */
public enum CommandDefiner implements EnumValue {

	OPEN_HOME_PAGE("home-page", new OpenHomePageCommand()), OPEN_LOGIN_PAGE("login-page", new OpenLoginPageCommand()),
	OPEN_REGISTER_PAGE("register-page", new OpenRegisterPageCommand()),
	OPEN_USER_PROFILE_PAGE("user-profile-page", new OpenUserProfilePageCommand()),
	OPEN_ALIEN_PROFILE_PAGE("alien-profile-page", new OpenAlienProfilePageCommand()),
	OPEN_ADMIN_FUNCTIONAL_PAGE("admin-functional-page", new OpenAdminFunctionalPageCommand()),
	OPEN_ADMIN_SUGGESTED_ALIENS_PAGE("suggested-aliens-page", new OpenAdminSuggestedAliensPageCommand()),
	OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE("suggested-aliens-images-page",
			new OpenAdminSuggestedAliensImagesPageCommand()),
	OPEN_USER_SUGGEST_ALIEN_PAGE("suggest-alien-page", new OpenSuggestAlienPageCommand()),
	OPEN_BANNED_PAGE("banned-page", new OpenBannedPageCommand()),
	OPEN_400_ERROR_PAGE("400-error-page", new Open400ErrorPageCommand()),
	OPEN_403_ERROR_PAGE("403-error-page", new Open403ErrorPageCommand()),
	OPEN_404_ERROR_PAGE("404-error-page", new Open404ErrorPageCommand()),
	OPEN_500_ERROR_PAGE("500-error-page", new Open500ErrorPageCommand()),

	ADMIN_APPROVE_ALIEN("admin-approve-alien", new AdminApproveAlienCommand()),
	ADMIN_DECLINE_ALIEN("admin-decline-alien", new AdminDeclineAlienCommand()),
	ADMIN_APPROVE_ALIEN_IMAGE("admin-approve-alien-image", new AdminApproveAlienImageCommand()),
	ADMIN_DECLINE_ALIEN_IMAGE("admin-decline-alien-image", new AdminDeclineAlienImageCommand()),
	SUGGEST_ALIEN("suggest-alien", new SuggestNewAlienCommand()),
	SUGGEST_ALIEN_IMAGE("suggest-alien-image", new SuggestNewAlienImageCommand()),
	CHANGE_LOCALE("change-locale", new ChangeLocaleCommand()),
	REGISTER_USER("register-user", new RegisterUserCommand()), LOGIN_USER("login-user", new LoginUserCommand()),
	LOGOUT_USER("logout-user", new LogoutUserCommand()),
	UPDATE_RATING("update-alien-rating", new UpdateRatingCommand()),
	UPDATE_USER_EMAIL("update-user-email", new UpdateUserEmailCommand()),
	UPDATE_USER_LOGIN("update-user-login", new UpdateUserLoginCommand()),
	UPDATE_USER_IMAGE("update-user-image", new UpdateUserImageCommand()),
	UPDATE_USER_PASSWORD("update-password", new UpdateUserPasswordCommand()),
	FIND_USER_RATE("update-user-rate", new FindUserRateCommand()), BAN_USER("ban-user", new BanUserCommand()),
	UNBAN_USER("unban-user", new UnbanUserCommand()), PROMOTE_USER("promote-user", new PromoteUserCommand()),
	DEMOTE_ADMIN("denote-admin", new DemoteAdminCommand()),
	UPDATE_ALIEN_INFO("update-alien-info", new UpdateAlienInfoCommand()),
	UPDATE_ALIEN_IMAGE("update-alien-image", new UpdateAlienImageCommand()),
	ADD_NEW_ALIEN("add-new-alien", new AddNewAlienCommand()),
	ADD_NEW_COMMENT("add-new-comment", new AddNewCommentCommand()),
	DELETE_COMMENT("delete-comment", new DeleteCommentCommand());

	private final String value;
	private final Command command;

	CommandDefiner(String value, Command command) {
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

	/**
	 * Defines command from string to {@code Command}
	 * 
	 * @param commandName {@code String} command name obtained from request
	 * @return {@code Command} object corresponding to {@code commandName}
	 */
	public static Command defineCommand(String commandName) {
		CommandDefiner commandDefiner = CommandDefiner.fromString(commandName);
		return commandDefiner.getCommand();
	}

	/**
	 * Defines {@code CommandDefiner} value from string
	 * 
	 * @param commandName {@code String} command name obtained from request
	 * @return {@code CommandDefiner} object corresponding to {@code commandName}
	 */
	public static CommandDefiner fromString(String commandName) {
		CommandDefiner result = OPEN_404_ERROR_PAGE;
		for (CommandDefiner commandDefiner : CommandDefiner.values()) {
			if (commandDefiner.getValue().equals(commandName)) {
				result = commandDefiner;
			}
		}
		return result;
	}

}

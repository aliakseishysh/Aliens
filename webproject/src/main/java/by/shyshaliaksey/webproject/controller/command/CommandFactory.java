package by.shyshaliaksey.webproject.controller.command;

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
import by.shyshaliaksey.webproject.controller.command.impl.open.DELETE_POTENTIALYOpenForgotPasswordPageCommand;
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
import by.shyshaliaksey.webproject.controller.command.impl.user.DELETE_POTENTIALYRequestRestorePasswordTokenCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserEmailCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserLoginCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserPasswordCommand;

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
		//TODO case OPEN_FORGOT_PASSWORD_PAGE -> new DELETE_POTENTIALYOpenForgotPasswordPageCommand();
		case OPEN_BANNED_PAGE -> CommandValue.OPEN_BANNED_PAGE.getCommand();
		case OPEN_SERVER_ERROR_PAGE -> CommandValue.OPEN_SERVER_ERROR_PAGE.getCommand();
		case OPEN_404_ERROR_PAGE -> CommandValue.OPEN_404_ERROR_PAGE.getCommand();
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
		// case REQUEST_RESTORE_PASSWORD_TOKEN -> new RequestRestorePasswordTokenCommand(); TODO if have time
		default -> throw new IllegalArgumentException("Value is not present in CommandFactory: " + commandName);
		};
		return command;
	}

}

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
		case OPEN_HOME_PAGE -> new OpenHomePageCommand();
		case OPEN_USER_PROFILE_PAGE -> new OpenUserProfilePageCommand();
		case OPEN_ALIEN_PROFILE_PAGE -> new OpenAlienProfilePageCommand();
		case OPEN_LOGIN_PAGE -> new OpenLoginPageCommand();
		case OPEN_ABOUT_PAGE -> new OpenAboutPageCommand();
		case OPEN_REGISTER_PAGE -> new OpenRegisterPageCommand();
		case OPEN_FORGOT_PASSWORD_PAGE -> new DELETE_POTENTIALYOpenForgotPasswordPageCommand();
		case OPEN_SERVER_ERROR_PAGE -> new OpenServerErrorPageCommand();
		case OPEN_BANNED_PAGE -> new OpenBannedPageCommand();
		case REGISTER_USER -> new RegisterUserCommand();
		case LOGIN_USER -> new LoginUserCommand();
		case LOGOUT_USER -> new LogoutUserCommand();
		case UPDATE_RATING -> new UpdateRatingCommand();
		case UPDATE_USER_EMAIL -> new UpdateUserEmailCommand();
		case UPDATE_USER_LOGIN -> new UpdateUserLoginCommand();
		case UPDATE_USER_IMAGE -> new UpdateUserImageCommand();
		case UPDATE_USER_PASSWORD -> new UpdateUserPasswordCommand();
		case FIND_USER_RATE -> new FindUserRateCommand();
		case BAN_USER -> new BanUserCommand();
		case UNBAN_USER -> new UnbanUserCommand();
		case PROMOTE_USER -> new PromoteUserCommand();
		case DEMOTE_ADMIN -> new DemoteAdminCommand();
		case UPDATE_ALIEN -> new UpdateAlienCommand();
		case ADD_NEW_ALIEN -> new AddNewAlienCommand();
		case ADD_NEW_COMMENT -> new AddNewCommentCommand();
		case DELETE_COMMENT -> new DeleteCommentCommand();
		case CHANGE_LOCALE -> new ChangeLocaleCommand();
		case OPEN_404_ERROR_PAGE -> new Open404ErrorPageCommand();
		// case REQUEST_RESTORE_PASSWORD_TOKEN -> new RequestRestorePasswordTokenCommand(); TODO if have time
		default -> throw new IllegalArgumentException("Value is not present in CommandFactory: " + commandName);
		};
		return command;
	}

}

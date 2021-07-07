package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.command.impl.admin.AddNewAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.BanUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.DemoteAdminCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.PromoteUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.UnbanUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.admin.UpdateAlienCommand;
import by.shyshaliaksey.webproject.controller.command.impl.alien.AddNewCommentCommand;
import by.shyshaliaksey.webproject.controller.command.impl.alien.DeleteCommentCommand;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadAlienCreateForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadAlienUpdateForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadBanUnbanForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadPromoteDemoteForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadUserEmailUpdateForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadUserImage;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadUserImageUpdateForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadUserLoginUpdateForm;
import by.shyshaliaksey.webproject.controller.command.impl.load.LoadUserPasswordUpdateForm;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAboutPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAlienProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenBannedPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenForgotPasswordPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenHomePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenLoginPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenRegisterPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenServerErrorPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenUserProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.FindUserRateCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.UpdateRatingCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LoginUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LogoutUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.RegisterUserCommand;
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
		case OPEN_FORGOT_PASSWORD_PAGE -> new OpenForgotPasswordPageCommand();
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
		case LOAD_EMAIL_UPDATE_FORM -> new LoadUserEmailUpdateForm();
		case LOAD_LOGIN_UPDATE_FORM -> new LoadUserLoginUpdateForm();
		case LOAD_IMAGE_UPDATE_FORM -> new LoadUserImageUpdateForm();
		case LOAD_PASSWORD_UPDATE_FORM -> new LoadUserPasswordUpdateForm();
		case LOAD_USER_IMAGE -> new LoadUserImage();
		case LOAD_BAN_UNBAN_FORM -> new LoadBanUnbanForm();
		case LOAD_PROMOTE_DEMOTE_FORM -> new LoadPromoteDemoteForm();
		case LOAD_ALIEN_UPDATE_FORM -> new LoadAlienUpdateForm();
		case BAN_USER -> new BanUserCommand();
		case UNBAN_USER -> new UnbanUserCommand();
		case PROMOTE_USER -> new PromoteUserCommand();
		case DEMOTE_ADMIN -> new DemoteAdminCommand();
		case UPDATE_ALIEN -> new UpdateAlienCommand();
		case ADD_NEW_ALIEN -> new AddNewAlienCommand();
		case ADD_NEW_COMMENT -> new AddNewCommentCommand();
		case DELETE_COMMENT -> new DeleteCommentCommand();
		default -> throw new IllegalArgumentException("Value is not present in CommandFactory: " + commandName);
		};
		return command;
	}

}

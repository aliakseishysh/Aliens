package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAboutPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenAlienProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenHomePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenLoginPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenRegisterPageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.open.OpenUserProfilePageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.FindUserRateCommand;
import by.shyshaliaksey.webproject.controller.command.impl.rating.UpdateRatingCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LoginUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.LogoutUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.RegisterUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.user.UpdateUserInfoCommand;
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
		case REGISTER_USER -> new RegisterUserCommand();
		case LOGIN_USER -> new LoginUserCommand();
		case LOGOUT_USER -> new LogoutUserCommand();
		case UPDATE_RATING -> new UpdateRatingCommand();
		case UPDATE_USER_INFO -> new UpdateUserInfoCommand();
		case UPDATE_USER_IMAGE -> new UpdateUserImageCommand();
		case UPDATE_USER_PASSWORD -> new UpdateUserPasswordCommand();
		case FIND_USER_RATE -> new FindUserRateCommand();
		default -> throw new IllegalArgumentException("Value is not present in CommandValue: " + commandName);
		};
		return command;
	}

}

package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.command.impl.FindUserRateCommand;
import by.shyshaliaksey.webproject.controller.command.impl.LoginUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.LogoutUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.RegisterUserCommand;
import by.shyshaliaksey.webproject.controller.command.impl.UpdateRatingCommand;
import by.shyshaliaksey.webproject.controller.command.impl.UpdateUserImageCommand;
import by.shyshaliaksey.webproject.controller.command.impl.UpdateUserInfoCommand;
import by.shyshaliaksey.webproject.controller.command.impl.UpdateUserPasswordCommand;
import by.shyshaliaksey.webproject.controller.command.impl.redirect.RedirectAboutCommand;
import by.shyshaliaksey.webproject.controller.command.impl.redirect.RedirectAlienProfileCommand;
import by.shyshaliaksey.webproject.controller.command.impl.redirect.RedirectHomeCommand;
import by.shyshaliaksey.webproject.controller.command.impl.redirect.RedirectLoginCommand;
import by.shyshaliaksey.webproject.controller.command.impl.redirect.RedirectRegisterCommand;
import by.shyshaliaksey.webproject.controller.command.impl.redirect.RedirectUserProfileCommand;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {

	private CommandFactory() {
	}

	public static Command defineCommand(String commandName) {
		CommandValue commandValue = CommandValue.valueOf(commandName);
		Command command = switch (commandValue) {
		case REDIRECT_HOME -> new RedirectHomeCommand();
		case REDIRECT_USER_PROFILE -> new RedirectUserProfileCommand();
		case REDIRECT_ALIEN_PROFILE -> new RedirectAlienProfileCommand();
		case REDIRECT_LOGIN -> new RedirectLoginCommand();
		case REDIRECT_ABOUT -> new RedirectAboutCommand();
		case REDIRECT_REGISTER -> new RedirectRegisterCommand();
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

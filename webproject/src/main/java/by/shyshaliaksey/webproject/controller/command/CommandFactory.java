package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.controller.command.redirect.RedirectAboutCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectAlienProfileCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectHomeCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectLoginCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectRegisterCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectUserProfileCommand;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {

	private static final String COMMAND_ATTRIBUTE = "command";

	private CommandFactory() {
	}

	public static Command defineCommand(HttpServletRequest request) {
		String commandString = request.getParameter(COMMAND_ATTRIBUTE);
		CommandValue commandValue = CommandValue.valueOf(commandString);
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
		default -> throw new IllegalArgumentException("Value is not present in CommandValue: " + commandString);
		};
		return command;
	}

}

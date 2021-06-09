package by.shyshaliaksey.webproject.controller.command;

import java.util.List;

import by.shyshaliaksey.webproject.controller.command.redirect.RedirectAboutCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectAlienProfileCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectHomeCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectLoginCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectRegisterCommand;
import by.shyshaliaksey.webproject.controller.command.redirect.RedirectUserProfileCommand;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {

	private static final String COMMAND_ATTRIBUTE = "command";
	
	public static Command defineCommand(HttpServletRequest request) {
		Command command;
		String commandString = request.getParameter(COMMAND_ATTRIBUTE);
		CommandValue commandValue = CommandValue.valueOf(commandString);
		switch (commandValue) {
		case REDIRECT_HOME:
			command = new RedirectHomeCommand();
			break;
		case REDIRECT_USER_PROFILE:
			command = new RedirectUserProfileCommand();
			break;
		case REDIRECT_ALIEN_PROFILE:
			command = new RedirectAlienProfileCommand();
			break;
		case REDIRECT_LOGIN:
			command = new RedirectLoginCommand();
			break;
		case REDIRECT_ABOUT:
			command = new RedirectAboutCommand();
			break;
		case REDIRECT_REGISTER:
			command = new RedirectRegisterCommand();
			break;
		case REGISTER_USER:
			command = new RegisterUserCommand();
			break;
		case LOGIN_USER:
			command = new LoginUserCommand();
			break;
		case LOGOUT_USER:
			command = new LogoutUserCommand();
			break;
		default:
			throw new IllegalArgumentException("Value not present in CommandValue: " + commandString);
		}
		return command;
	}
	
}

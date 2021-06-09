package by.shyshaliaksey.webproject.controller.command;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandFactory {

	private static final String COMMAND_ATTRIBUTE = "command";
	
	public static List<Command> defineCommand(HttpServletRequest request) {
		List<Command> commands = new ArrayList<>();
		String commandString = request.getParameter(COMMAND_ATTRIBUTE);
		CommandValue commandValue = CommandValue.valueOf(commandString);
		switch (commandValue) {
		case REDIRECT_HOME:
			commands.add(new GetAliensListCommand());
			commands.add(new RedirectCommand(commandValue));
			break;
		case REDIRECT_USER_PROFILE:
			commands.add(new SetCurrentUser());
			commands.add(new RedirectCommand(commandValue));
			break;
		case REDIRECT_ALIEN_PROFILE:
			commands.add(new SetCurrentAlien());
			commands.add(new RedirectCommand(commandValue));
			break;
		case REDIRECT_LOGIN:
			commands.add(new RedirectCommand(commandValue));
			break;
		case REDIRECT_ABOUT:
			commands.add(new RedirectCommand(commandValue));
			break;
		case REDIRECT_REGISTER:
			commands.add(new RedirectCommand(commandValue));
			break;
		case REGISTER_USER:
			commands.add(new RegisterUserCommand());
			/*
			 * TODO variation if bad registration??
			 */
			commands.add(new RedirectCommand(CommandValue.REDIRECT_LOGIN));
			break;
		case LOGIN_USER:
			commands.add(new LoginUserCommand());
			commands.add(new SetCurrentUser());
			commands.add(new GetAliensListCommand());
			commands.add(new RedirectCommand(CommandValue.REDIRECT_HOME));
			break;
		case LOGOUT_USER:
			commands.add(new LogoutUserCommand());
			commands.add(new GetAliensListCommand());
			commands.add(new RedirectCommand(CommandValue.REDIRECT_HOME));
			break;
		default:
			throw new IllegalArgumentException("Value not present in CommandValue: " + commandString);
		}
		return commands;
	}
	
}

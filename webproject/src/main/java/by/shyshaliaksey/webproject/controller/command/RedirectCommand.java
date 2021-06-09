package by.shyshaliaksey.webproject.controller.command;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectCommand extends Command {

	private CommandValue commandValue;
	
	public RedirectCommand(CommandValue commandValue) {
		this.commandValue = commandValue;
	}
	
	@Override
	public void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		String pathToForward = createPath();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pathToForward);
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String createPath() {
		String basePath = "/WEB-INF/jsp/";
		String resultPath = "";
		switch(commandValue) {
		case REDIRECT_HOME:
			resultPath = basePath + "home.jsp";
			break;
		case REDIRECT_ABOUT:
			resultPath = basePath + "about.jsp";
			break;
		case REDIRECT_LOGIN:
			resultPath = basePath + "login.jsp";
			break;
		case REDIRECT_REGISTER:
			resultPath = basePath + "register.jsp";
			break;
		case REDIRECT_USER_PROFILE:
			resultPath = basePath + "profile_page.jsp";
			break;
		case REDIRECT_ALIEN_PROFILE:
			resultPath = basePath + "alien_profile.jsp";
			break;
		default:
			throw new IllegalArgumentException("No such commandValue: " + commandValue);
		}
		return resultPath;
	}

}

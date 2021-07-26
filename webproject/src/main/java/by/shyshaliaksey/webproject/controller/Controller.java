package by.shyshaliaksey.webproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static by.shyshaliaksey.webproject.controller.PagePath.ERROR_PAGE_500_JSP;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandAccessChecker;
import by.shyshaliaksey.webproject.controller.command.CommandFactory;
import by.shyshaliaksey.webproject.controller.command.Router;

/**
 * Servlet implementation class Controller
 */
@MultipartConfig
@WebServlet(name="Controller", urlPatterns={"/controller"})
public class Controller extends HttpServlet {
       
	private static final Logger logger = LogManager.getRootLogger();
	private static final long serialVersionUID = -6656382914151361780L;
	
    public Controller() {
        super();
    }
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
	
	/**
	 * CMethod to process all requests from users. <br>
	 * Defines command from request parameter, checks if this user has access (by role) to proceed this command. <br>
	 * Sends response to user.
	 * 
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		Command command = CommandFactory.defineCommand(commandName);
		Map<CommandAccessChecker.MapKey, Object> isUserHasPremission = CommandAccessChecker.isUserHasPermission(command, request, response);
		Router router;
		if (isUserHasPremission.get(CommandAccessChecker.MapKey.RESULT) == Boolean.TRUE) {
			router = command.execute(request, response);
		} else {
			router = (Router) isUserHasPremission.get(CommandAccessChecker.MapKey.ROUTER);
		}
		switch (router.getRouterType()) {
		case AJAX_RESPONSE:			
			response.getWriter().write(router.getResponseParameter());					
			break;
		case FORWARD:
			request.getRequestDispatcher(router.getPagePath()).forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(request.getContextPath() + router.getPagePath());
			break;
		default:
			logger.log(Level.ERROR, "Invalid RouterType value: {}", commandName);
			request.getRequestDispatcher(ERROR_PAGE_500_JSP.getValue()).forward(request, response);
		}
	}

}

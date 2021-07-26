package by.shyshaliaksey.webproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static by.shyshaliaksey.webproject.controller.StaticPath.ERROR_PAGE_500_JSP;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandAccessChecker;
import by.shyshaliaksey.webproject.controller.command.CommandDefiner;
import by.shyshaliaksey.webproject.controller.command.Router;

/**
 * Servlet implementation class Controller
 */
@MultipartConfig
@WebServlet(name = "Controller", urlPatterns = { "/controller" })
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
	 * Method to process all requests from users. Defines command from request
	 * parameter, checks if this user has access (by role) to proceed this command.
	 * Sends response to user.
	 * 
	 * @param request  Extends the jakarta.servlet.ServletRequest interface to
	 *                 provide request information for HTTP servlets. The servlet
	 *                 container creates an HttpServletRequest object and passes it
	 *                 as an argument to the servlet's service methods (doGet,
	 *                 doPost, etc).
	 * @param response Extends the ServletResponse interface to provide
	 *                 HTTP-specific functionality in sending a response. For
	 *                 example, it has methods to access HTTP headers and cookies.
	 *                 The servlet container creates an HttpServletResponse object
	 *                 and passes it as an argument to theservlet's service methods
	 *                 (doGet, doPost, etc).
	 * @see CommandDefiner
	 * @see CommandAccessChecker
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		Command command = CommandDefiner.defineCommand(commandName);
		Map<CommandAccessChecker.MapKey, Object> isUserHasPremission = CommandAccessChecker.isUserHasPermission(command,
				request, response);
		Router router;
		if (isUserHasPremission.get(CommandAccessChecker.MapKey.RESULT) == Boolean.TRUE) {
			router = command.execute(request, response);
		} else {
			router = (Router) isUserHasPremission.get(CommandAccessChecker.MapKey.ROUTER);
		}
		switch (router.getRouterType()) {
		case AJAX_RESPONSE:
			response.getWriter().write(router.getJsonResponse());
			break;
		case FORWARD:
			request.getRequestDispatcher(router.getPageToGo()).forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(request.getContextPath() + router.getPageToGo());
			break;
		default:
			logger.log(Level.ERROR, "Invalid RouterType value: {}", commandName);
			request.getRequestDispatcher(ERROR_PAGE_500_JSP.getValue()).forward(request, response);
		}
	}

}

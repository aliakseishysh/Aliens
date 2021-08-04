package by.shyshaliaksey.aliens.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface for all commands in project
 * @see Command#execute(HttpServletRequest, HttpServletResponse)
 */
public interface Command {

	/**
	 * Method for executing command <br>
	 * If method name changed, change <b>final String methodName</b> variable from
	 * {@link by.shyshaliaksey.aliens.controller.command.CommandAccessChecker#isUserHasPermission}
	 * to new method name
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
	 * @return Router to proceed forward/redirect/ajax response
	 * @see Router
	 */
	public abstract Router execute(HttpServletRequest request, HttpServletResponse response);

}

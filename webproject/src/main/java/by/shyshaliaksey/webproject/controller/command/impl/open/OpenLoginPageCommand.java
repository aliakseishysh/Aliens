package by.shyshaliaksey.webproject.controller.command.impl.open;

import by.shyshaliaksey.webproject.controller.StaticPath;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UtilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening login page
 * and user account activation on token receiving.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenLoginPageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.GUEST})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getParameter(RequestParameter.TOKEN.getValue());
		Router router;
		// account activation
		if (token != null) {
			UtilService utilService = ServiceProvider.getInstance().getUtilService();
			try {
				utilService.activateAccount(token);
				router = new Router(StaticPath.PAGE_LOGIN_JSP.getValue(), null, Type.FORWARD);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Account activation failed: {} {}", e.getMessage(), e.getStackTrace());
				response.setStatus(Feedback.Code.WRONG_INPUT.getStatusCode());
				router = new Router(StaticPath.PAGE_LOGIN_JSP.getValue(), null, Type.FORWARD);
			}
		} else {
			router = new Router(StaticPath.PAGE_LOGIN_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

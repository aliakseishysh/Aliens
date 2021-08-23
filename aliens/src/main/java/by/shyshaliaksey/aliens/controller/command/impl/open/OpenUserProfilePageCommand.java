package by.shyshaliaksey.aliens.controller.command.impl.open;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Feedback;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import by.shyshaliaksey.aliens.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening profile
 * page and updating email on token receiving.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenUserProfilePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		UserService userService = ServiceProvider.getInstance().getUserService();
		try {
			String token = request.getParameter(RequestParameter.TOKEN.getValue());
			if (token != null) {
				try {
					userService.setNewEmail(token);
				} catch (ServiceException e) {
					response.setStatus(Feedback.Code.WRONG_INPUT.getStatusCode());
					router = new Router(StaticPath.ERROR_PAGE_400_JSP.getValue(), null, Type.FORWARD);
					logger.log(Level.ERROR, "Setting new email failed for token: {}", token);
					return router;
				}
			}
			User user = (User) request.getSession(false).getAttribute(RequestAttribute.CURRENT_USER.getValue());
			Optional<User> currentUser = userService.findByLogin(user.getLogin());
			if (currentUser.isPresent()) {
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), currentUser.get());
				router = new Router(StaticPath.PAGE_PROFILE_JSP.getValue(), null, Type.FORWARD);
			} else {
				router = new Router(StaticPath.ERROR_PAGE_404_JSP.getValue(), null, Type.FORWARD);
				logger.log(Level.INFO, "No user with such user login: {}", user.getLogin());
			}
		} catch (ServiceException e) {
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
			logger.log(Level.ERROR, "Exception occurred while redirecting to {}: {}", StaticPath.PAGE_PROFILE_JSP,
					e.getMessage());
		}
		return router;
	}

}

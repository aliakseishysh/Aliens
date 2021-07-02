package by.shyshaliaksey.webproject.controller.command.impl.open;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenUserProfilePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession(false).getAttribute(RequestAttribute.CURRENT_USER.getValue());
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			Optional<User> currentUser = userService.findByLogin(user.getLogin());
			if (currentUser.isPresent()) {
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), currentUser.get());
				// request.getSession().setAttribute(RequestAttribute.LOGIN_NAME.getValue(), currentUser.get().getLogin());
				router = new Router(PagePath.PAGE_PROFILE_JSP.getValue(), null, RouterType.FORWARD);
			} else {
				router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
				logger.log(Level.INFO, "No user with such user login: {}", user.getLogin());
			}
		} catch (ServiceException e) {
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
			logger.log(Level.ERROR, "Exception occured while redirecting to {}: {}", PagePath.PAGE_PROFILE_JSP,
					e.getMessage());
		}
		return router;
	}

}

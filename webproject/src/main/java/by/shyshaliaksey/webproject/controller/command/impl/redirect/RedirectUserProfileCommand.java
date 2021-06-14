package by.shyshaliaksey.webproject.controller.command.impl.redirect;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectUserProfileCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String login = (String) request.getSession(false).getAttribute("login_name");
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			Optional<User> currentUser = userService.findByLogin(login);
			if (currentUser.isPresent()) {
				request.getSession().setAttribute("currentUser", currentUser.get());
				router = new Router(PagePath.USER_PROFILE_JSP, null, RouterType.FORWARD);
			} else {
				router = new Router(PagePath.ERROR_PAGE_JSP, null, RouterType.REDIRECT);
				logger.log(Level.INFO, "No user with such login: {}", login);
			}
		} catch (ServiceException e) {
			router = new Router(PagePath.ERROR_PAGE_JSP, null, RouterType.REDIRECT);
			logger.log(Level.ERROR, "Exception occured while redirecting to {}: {}", PagePath.USER_PROFILE_JSP,
					e.getMessage());
		}
		return router;
	}

}

package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter(RequestParameter.EMAIL.getValue());
		String password = request.getParameter(RequestParameter.PASSWORD.getValue());
		Router router;
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		AlienService alienService = serviceProvider.getAlienService();
		try {
			boolean logInResult = userService.userLogIn(email, password);
			if (logInResult) {
				Optional<User> userOptional = userService.findUserByEmail(email);
				if (userOptional.isPresent()) {
					User user = userOptional.get();
					request.getSession(true).setAttribute(RequestAttribute.LOGIN_NAME.getValue(), user.getLogin());
					request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), user);
					List<Alien> aliens = alienService.findAllAliens();
					request.getSession().setAttribute(RequestAttribute.ALIEN_LIST.getValue(), aliens);
					router = new Router(PagePath.HOME_JSP.getValue(), null, RouterType.FORWARD);
				} else {
					router = new Router(PagePath.LOGIN_JSP.getValue(), null, RouterType.FORWARD);
				}
			} else {
				router = new Router(PagePath.LOGIN_JSP.getValue(), null, RouterType.FORWARD);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while user logining: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
	}

}

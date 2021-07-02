package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserLoginCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		// String currentUser = request.getParameter(RequestAttribute.CURRENT_USER.getValue());
		String login = request.getParameter(RequestParameter.LOGIN.getValue());
		String newLogin = request.getParameter(RequestParameter.NEW_LOGIN.getValue());
		int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			Boolean loginResult = userService.changeLogin(login, newLogin, userId);
			Optional<User> user = userService.findByLogin(newLogin);
			if (loginResult && user.isPresent()) {
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), user.get());
			}
			router = new Router(null, loginResult.toString(), RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while email updating: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
		
	}

}

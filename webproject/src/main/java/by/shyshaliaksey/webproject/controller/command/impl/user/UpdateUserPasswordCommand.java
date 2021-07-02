package by.shyshaliaksey.webproject.controller.command.impl.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserPasswordCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String password = request.getParameter(RequestParameter.PASSWORD.getValue());
		String passwordConfirm = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
		int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			Boolean passwordResult = userService.changePassword(password, passwordConfirm, userId);
			router = new Router(null, passwordResult.toString(), RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while email updating: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
	}

}

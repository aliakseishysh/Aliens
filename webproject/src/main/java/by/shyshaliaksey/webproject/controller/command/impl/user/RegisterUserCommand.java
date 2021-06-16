package by.shyshaliaksey.webproject.controller.command.impl.user;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.shyshaliaksey.webproject.controller.command.FilePath.IMAGE_DEFAULT;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter(RequestParameter.EMAIL.getValue());
		String login = request.getParameter(RequestParameter.LOGIN.getValue());
		String password = request.getParameter(RequestParameter.PASSWORD.getValue());
		String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			boolean registerResult = userService.registerUser(email, login, password, IMAGE_DEFAULT.getValue(), Role.USER);
			if (registerResult) {
				router = new Router(PagePath.LOGIN_JSP.getValue(), null, RouterType.FORWARD);
			} else {
				router = new Router(PagePath.REGISTER_JSP.getValue(), null, RouterType.FORWARD);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while register: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
	}

}

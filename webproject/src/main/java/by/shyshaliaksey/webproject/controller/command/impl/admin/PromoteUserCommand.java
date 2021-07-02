package by.shyshaliaksey.webproject.controller.command.impl.admin;


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
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PromoteUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			String userLogin = request.getParameter(RequestParameter.LOGIN.getValue());
			String currentUserLogin = (String) request.getAttribute(RequestAttribute.CURRENT_USER.getValue());
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AdminService adminService = serviceProvider.getAdminService();
			boolean result = adminService.promoteUser(userLogin, currentUserLogin);
			router = new Router(null, String.valueOf(result), RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while user banning: {} {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;	
	}

}

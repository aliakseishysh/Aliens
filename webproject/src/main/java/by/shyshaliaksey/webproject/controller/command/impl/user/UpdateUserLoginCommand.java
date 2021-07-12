	package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginUpdateResultInfo;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserLoginCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({Role.USER, Role.ADMIN})
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
			LoginUpdateResultInfo loginUpdateResult = userService.changeLogin(login, newLogin, userId);
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.UPDATE_LOGIN_RESULT_INFO_STATUS.getValue(), loginUpdateResult.isLoginCorrect())
					.put(ErrorFeedback.UPDATE_LOGIN_RESULT_INFO_LOGIN_FEEDBACK.getValue(), loginUpdateResult.getLoginErrorInfo())
					.toString();
			if (loginUpdateResult.isLoginCorrect()) {
				User user = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
				user.setLogin(newLogin);
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), user);
				response.setStatus(200);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			} else {
				response.setStatus(400);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while email updating: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
		
	}

}

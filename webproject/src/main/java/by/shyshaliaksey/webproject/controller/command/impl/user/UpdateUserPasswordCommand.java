package by.shyshaliaksey.webproject.controller.command.impl.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.PasswordUpdateResultInfo;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserPasswordCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String password = request.getParameter(RequestParameter.PASSWORD.getValue());
		String passwordConfirm = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
		int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			PasswordUpdateResultInfo passwordUpdateResult = userService.changePassword(password, passwordConfirm, userId);
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_PASSWORD_STATUS.getValue(), passwordUpdateResult.isPasswordCorrect())
					.put(ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_PASSWORD_FEEDBACK.getValue(), passwordUpdateResult.getPasswordErrorInfo())
					.put(ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_PASSWORD_CONFIRMATION_STATUS.getValue(), passwordUpdateResult.isPasswordConfirmationCorrect())
					.put(ErrorFeedback.UPDATE_PASSWORD_RESULT_INFO_PASSWORD_CONFIRMATION_FEEDBACK.getValue(), passwordUpdateResult.getPasswordConfirmationErrorInfo())
					.toString();
			if (passwordUpdateResult.isPasswordCorrect() && passwordUpdateResult.isPasswordConfirmationCorrect()) {
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

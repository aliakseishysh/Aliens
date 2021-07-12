package by.shyshaliaksey.webproject.controller.command.impl.user;


import static by.shyshaliaksey.webproject.controller.FilePath.IMAGE_DEFAULT;

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
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.RegisterResultInfo;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.GUEST})
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
			RegisterResultInfo registerResult = userService.registerUser(email, login, password, passwordRepeat, IMAGE_DEFAULT.getValue(), Role.USER);
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.REGISTER_RESULT_INFO_EMAIL_STATUS.getValue(), registerResult.isEmailCorrect())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_LOGIN_STATUS.getValue(), registerResult.isLoginCorrect())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_STATUS.getValue(), registerResult.isPasswordCorrect())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_CONFIRM_STATUS.getValue(), registerResult.isPasswordConfirmationCorrect())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_EMAIL_FEEDBACK.getValue(), registerResult.getEmailErrorInfo())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_LOGIN_FEEDBACK.getValue(), registerResult.getLoginErrorInfo())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_FEEDBACK.getValue(), registerResult.getPasswordErrorInfo())
					.put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_CONFIRM_FEEDBACK	.getValue(), registerResult.getPasswordConfirmationErrorInfo())
					.toString();
			
			if (registerResult.isEmailCorrect() && registerResult.isLoginCorrect() 
					&& registerResult.isPasswordCorrect() && registerResult.isPasswordConfirmationCorrect()) {
				response.setStatus(200);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			} else {
				response.setStatus(400);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while register: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

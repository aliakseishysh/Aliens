package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.ErrorAttribute;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginResultInfo;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter(RequestParameter.EMAIL.getValue());
		String password = request.getParameter(RequestParameter.PASSWORD.getValue());
		Router router;
		User user = null;
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		try {
			LoginResultInfo loginResult = userService.userLogIn(email, password);
			// TODO to separate class, create new constants
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_STATUS.getValue(), loginResult.isEmailCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_STATUS.getValue(), loginResult.isPasswordCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_FEEDBACK.getValue(), loginResult.getEmailErrorInfo())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_FEEDBACK	.getValue(), loginResult.getPasswordErrorInfo())
					.toString();
			if (loginResult.isEmailCorrect() && loginResult.isPasswordCorrect()) {
				user = userService.findUserByEmail(email).get();
				request.getSession(true).setAttribute(RequestAttribute.CURRENT_USER.getValue(), user);
				request.getSession().setAttribute(RequestAttribute.LOGIN_NAME.getValue(), user.getLogin());
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER_ROLE.getValue(),
						user.getRole().getValue());
				response.setStatus(200);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);

			} else {
				response.setStatus(400);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while user logining: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}

		if (userService.isUserBanned(request.getSession())) {
			request.getSession().setAttribute(ErrorAttribute.Name.BAN_INFO.name(), user.getBannedToDate().toString());
			router = new Router(PagePath.PAGE_BANNED_JSP.getValue(), null, RouterType.REDIRECT);
		}

		return router;
	}

}

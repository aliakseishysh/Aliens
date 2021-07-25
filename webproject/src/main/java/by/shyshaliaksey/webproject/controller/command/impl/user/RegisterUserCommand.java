package by.shyshaliaksey.webproject.controller.command.impl.user;


import static by.shyshaliaksey.webproject.controller.FilePath.IMAGE_DEFAULT;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.InitParameter;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	
	@AllowedRoles({Role.GUEST})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String email = request.getParameter(RequestParameter.EMAIL.getValue());
			String login = request.getParameter(RequestParameter.LOGIN.getValue());
			String password = request.getParameter(RequestParameter.PASSWORD.getValue());
			String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
			String websiteUrl = request.getServletContext().getInitParameter(InitParameter.WEB_SITE_URL.getValue());
			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			result = userService.registerUser(email, login, password, passwordRepeat, IMAGE_DEFAULT.getValue(), Role.USER, websiteUrl, localeAttribute);
			
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.EMAIL_STATUS.getValue(),
							result.get(Feedback.Key.EMAIL_STATUS))
					.put(Feedback.Key.LOGIN_STATUS.getValue(),
							result.get(Feedback.Key.LOGIN_STATUS))
					.put(Feedback.Key.PASSWORD_STATUS.getValue(),
							result.get(Feedback.Key.PASSWORD_STATUS))
					.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS.getValue(),
							result.get(Feedback.Key.PASSWORD_CONFIRMATION_STATUS))
					.put(Feedback.Key.EMAIL_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.EMAIL_FEEDBACK).toString()))
					.put(Feedback.Key.LOGIN_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.LOGIN_FEEDBACK).toString()))
					.put(Feedback.Key.PASSWORD_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_FEEDBACK).toString()))
					.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK).toString()))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while register: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

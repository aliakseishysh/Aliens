package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for user log in through
 * service layer.
 * 
 * @author Aliaksey Shysh
 * 
 * @see UserService#userLogIn(String, String)
 * 
 */
public class LoginUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.GUEST })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String email = request.getParameter(RequestParameter.EMAIL.getValue());
			String password = request.getParameter(RequestParameter.PASSWORD.getValue());
			User user = null;
			UserService userService = ServiceProvider.getInstance().getUserService();
			result = userService.userLogIn(email, password);

			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession()
					.getAttribute(SessionAttribute.CURRENT_LOCALE.name());

			String jsonResponse = new JSONObject()
					.put(Feedback.Key.EMAIL_STATUS.getValue(), result.get(Feedback.Key.EMAIL_STATUS))
					.put(Feedback.Key.PASSWORD_STATUS.getValue(), result.get(Feedback.Key.PASSWORD_STATUS))
					.put(Feedback.Key.EMAIL_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.EMAIL_FEEDBACK).toString()))
					.put(Feedback.Key.PASSWORD_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_FEEDBACK).toString()))
					.toString();
			if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS))
					&& Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))) {
				user = userService.findUserByEmail(email).get();
				request.getSession(true).setAttribute(RequestAttribute.CURRENT_USER.getValue(), user);
				request.getSession().setAttribute(RequestAttribute.LOGIN_NAME.getValue(), user.getLogin());
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER_ROLE.getValue(),
						user.getRole().getValue());
				if (user.getBannedToDate() != null) {
					request.getSession().setAttribute(SessionAttribute.BAN_INFO.name(),
							user.getBannedToDate().toString());
				}
			}
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, Type.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while user logining: {} {}", e.getMessage(), e.getStackTrace());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

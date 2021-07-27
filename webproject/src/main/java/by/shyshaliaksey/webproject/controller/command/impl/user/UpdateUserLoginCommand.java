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
 * Implementer of {@link Command} interface, designed for updating user login
 * through service layer.
 * 
 * @author Aliaksey Shysh
 * 
 * @see UserService#changeLogin(String, String, int)
 * 
 */
public class UpdateUserLoginCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String login = request.getParameter(RequestParameter.LOGIN.getValue());
			String newLogin = request.getParameter(RequestParameter.NEW_LOGIN.getValue());
			int userId = ((User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue())).getId();
			UserService userService = ServiceProvider.getInstance().getUserService();
			result = userService.changeLogin(login, newLogin, userId);

			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession()
					.getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.LOGIN_STATUS.getValue(), result.get(Feedback.Key.LOGIN_STATUS))
					.put(Feedback.Key.LOGIN_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.LOGIN_FEEDBACK).toString()))
					.toString();
			if (Boolean.TRUE.equals(result.get(Feedback.Key.LOGIN_STATUS))) {
				User user = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
				user.setLogin(newLogin);
				request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), user);
			}
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, Type.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while login updating: {} {}", e.getMessage(), e.getStackTrace());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

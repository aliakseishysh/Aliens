package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserPasswordCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	
	@AllowedRoles({Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String password = request.getParameter(RequestParameter.PASSWORD.getValue());
			String passwordConfirm = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
			int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
			result =  userService.changePassword(password, passwordConfirm, userId);
			
			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.PASSWORD_STATUS.getValue(),
							result.get(Feedback.Key.PASSWORD_STATUS))
					.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS.getValue(),
							result.get(Feedback.Key.PASSWORD_CONFIRMATION_STATUS))
					.put(Feedback.Key.PASSWORD_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_FEEDBACK).toString()))
					.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK).toString()))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while password updating: {}", e.getMessage(), e);
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

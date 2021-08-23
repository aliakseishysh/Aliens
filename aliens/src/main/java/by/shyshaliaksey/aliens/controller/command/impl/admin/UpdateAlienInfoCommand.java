package by.shyshaliaksey.aliens.controller.command.impl.admin;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.SessionAttribute;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Feedback;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.service.AdminService;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import by.shyshaliaksey.aliens.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for updating alien profile
 * information (name, small description, full description) through service
 * layer.
 * 
 * @author Aliaksey Shysh
 * 
 * @see AdminService#demoteAdmin(String, String)
 * 
 */
public class UpdateAlienInfoCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AdminService adminService = ServiceProvider.getInstance().getAdminService();

	@AllowedRoles({ Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			String alienSmallDescription = request.getParameter(RequestParameter.ALIEN_SMALL_DESCRIPTION.getValue());
			String alienFullDescription = request.getParameter(RequestParameter.ALIEN_FULL_DESCRIPTION.getValue());
			result = adminService.updateAlienInfo(alienId, alienName, alienSmallDescription, alienFullDescription);

			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession()
					.getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.ALIEN_NAME_STATUS.getValue(), result.get(Feedback.Key.ALIEN_NAME_STATUS))
					.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS.getValue(),
							result.get(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS))
					.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS.getValue(),
							result.get(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS))
					.put(Feedback.Key.ALIEN_NAME_FEEDBACK.getValue(),
							localeAttribute
									.getLocalizedMessage(result.get(Feedback.Key.ALIEN_NAME_FEEDBACK).toString()))
					.put(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(
									result.get(Feedback.Key.ALIEN_SMALL_DESCRIPTION_FEEDBACK).toString()))
					.put(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK.getValue(), localeAttribute
							.getLocalizedMessage(result.get(Feedback.Key.ALIEN_FULL_DESCRIPTION_FEEDBACK).toString()))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, Type.AJAX_RESPONSE);
		} catch (ServiceException | NumberFormatException e) {
			response.setStatus(Feedback.Code.INTERNAL_SERVER_ERROR.getStatusCode());
			logger.log(Level.ERROR, "Exception occurred while alien info updating: {} {}", e.getMessage(), e.getStackTrace());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

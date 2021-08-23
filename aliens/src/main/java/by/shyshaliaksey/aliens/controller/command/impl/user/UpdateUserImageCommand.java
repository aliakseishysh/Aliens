package by.shyshaliaksey.aliens.controller.command.impl.user;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.SessionAttribute;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Feedback;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import by.shyshaliaksey.aliens.model.service.UserService;
import by.shyshaliaksey.aliens.model.util.localization.LocaleAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Implementer of {@link Command} interface, designed for updating user image
 * through model layer.
 * 
 * @author Aliaksey Shysh
 *
 * 
 */
public class UpdateUserImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();

	@AllowedRoles({ Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			Part part = request.getPart(RequestParameter.NEW_IMAGE.getValue());
			int userId = ((User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue())).getId();
			String serverDeploymentPath = request.getServletContext().getRealPath(StaticPath.ROOT_FOLDER.getValue());
			result = userService.updateImage(serverDeploymentPath, part, userId);

			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession()
					.getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.IMAGE_STATUS.getValue(), result.get(Feedback.Key.IMAGE_STATUS))
					.put(Feedback.Key.IMAGE_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.IMAGE_FEEDBACK).toString()))
					.put(Feedback.Key.IMAGE_PATH.getValue(), result.get(Feedback.Key.IMAGE_PATH).toString()).toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, Type.AJAX_RESPONSE);
		} catch (ServiceException | IOException | ServletException e) {
			response.setStatus(Feedback.Code.INTERNAL_SERVER_ERROR.getStatusCode());
			logger.log(Level.ERROR, "IOException occurred while image updating: {} {}", e.getMessage(),
					e.getStackTrace());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

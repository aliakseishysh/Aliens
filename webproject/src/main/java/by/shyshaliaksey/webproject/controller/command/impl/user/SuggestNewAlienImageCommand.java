package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Implementer of {@link Command} interface, designed for suggesting new alien
 * image through model layer.
 * 
 * @author Aliaksey Shysh
 * 
 * @see UserService#suggestNewAlienImage(String, Part, String, String)
 * 
 */
public class SuggestNewAlienImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.USER })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			Part alienImage = request.getPart(RequestParameter.ALIEN_NEW_IMAGE.getValue());
			String serverDeploymentPath = request.getServletContext().getRealPath(StaticPath.ROOT_FOLDER.getValue());
			UserService userService = ServiceProvider.getInstance().getUserService();
			result = userService.suggestNewAlienImage(alienName, alienImage, serverDeploymentPath);
			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession()
					.getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.ALIEN_NAME_STATUS.getValue(), result.get(Feedback.Key.ALIEN_NAME_STATUS))
					.put(Feedback.Key.IMAGE_STATUS.getValue(), result.get(Feedback.Key.IMAGE_STATUS))
					.put(Feedback.Key.ALIEN_NAME_FEEDBACK.getValue(),
							localeAttribute
									.getLocalizedMessage(result.get(Feedback.Key.ALIEN_NAME_FEEDBACK).toString()))
					.put(Feedback.Key.IMAGE_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.IMAGE_FEEDBACK).toString()))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, Type.AJAX_RESPONSE);
		} catch (ServiceException | IOException | ServletException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while alien adding: {} {}", e.getMessage(), e.getStackTrace());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

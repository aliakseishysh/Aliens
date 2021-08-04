package by.shyshaliaksey.aliens.controller.command.impl.admin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Feedback;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.service.AdminService;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for declining alien image
 * suggested by user through model layer.
 * 
 * @author Aliaksey Shysh
 *
 * @see AdminService#declineAlienImage(String)
 * 
 */
public class AdminDeclineAlienImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AdminService adminService = ServiceProvider.getInstance().getAdminService();

	@AllowedRoles({ Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			String imageName = request.getParameter(RequestParameter.IMAGE.getValue());
			boolean result = adminService.declineAlienImage(StaticPath.ALIEN_IMAGE_FOLDER.getValue() + imageName);

			String jsonResponse = new JSONObject().put(Feedback.Key.ALIEN_STATUS.getValue(), result).toString();
			if (result) {
				response.setStatus(Feedback.Code.OK.getStatusCode());
			} else {
				response.setStatus(Feedback.Code.INTERNAL_SERVER_ERROR.getStatusCode());
			}
			router = new Router(null, jsonResponse, Type.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(Feedback.Code.INTERNAL_SERVER_ERROR.getStatusCode());
			logger.log(Level.ERROR, "Exception occured while alien image approving: {} {}", e.getMessage(), e.getStackTrace());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

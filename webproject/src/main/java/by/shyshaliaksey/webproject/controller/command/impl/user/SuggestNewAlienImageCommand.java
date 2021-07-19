package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.FolderPath;
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
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class SuggestNewAlienImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();

	@AllowedRoles({Role.USER})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			Part alienImage = request.getPart(RequestParameter.ALIEN_NEW_IMAGE.getValue());
			String rootFolder = request.getServletContext()
					.getInitParameter(InitParameter.WEB_APP_ROOT_FOLDER_PARAMETER.getValue());
			String serverDeploymentPath = request.getServletContext()
					.getRealPath(FolderPath.ALIEN_IMAGE_FOLDER.getValue());
			result = userService.suggestNewAlienImage(alienName, alienImage, rootFolder, serverDeploymentPath);
			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			// TODO to separate class, create new constants
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.ALIEN_NAME_STATUS.getValue(),
							result.get(Feedback.Key.ALIEN_NAME_STATUS))
					.put(Feedback.Key.IMAGE_STATUS.getValue(),
							result.get(Feedback.Key.IMAGE_STATUS))
					.put(Feedback.Key.ALIEN_NAME_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.ALIEN_NAME_FEEDBACK).toString()))
					.put(Feedback.Key.IMAGE_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.IMAGE_FEEDBACK).toString()))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException | IOException | ServletException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while alien adding: {} {} {}", e.getMessage(), e.getStackTrace(),
					e);
			router = new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

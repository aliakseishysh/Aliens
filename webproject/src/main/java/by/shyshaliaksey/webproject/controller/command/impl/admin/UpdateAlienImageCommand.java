package by.shyshaliaksey.webproject.controller.command.impl.admin;


import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.controller.InitParameter;
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
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateAlienImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AdminService adminService = ServiceProvider.getInstance().getAdminService();
	
	@AllowedRoles({Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
			Part alienImage = request.getPart(RequestParameter.ALIEN_NEW_IMAGE.getValue());
			String rootFolder = request.getServletContext()
					.getInitParameter(InitParameter.WEB_APP_ROOT_FOLDER_PARAMETER.getValue());
			String serverDeploymentPath = request.getServletContext()
					.getRealPath(FolderPath.ROOT_FOLDER.getValue());
			String websiteUrl = request.getServletContext().getInitParameter(InitParameter.WEB_SITE_URL.getValue());
			result = adminService.updateAlienImage(alienId, alienImage, rootFolder, serverDeploymentPath, websiteUrl);
			
			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.IMAGE_STATUS.getValue(),
							result.get(Feedback.Key.IMAGE_STATUS))
					.put(Feedback.Key.IMAGE_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.IMAGE_FEEDBACK).toString()))
					.put(Feedback.Key.IMAGE_PATH.getValue(), result.get(Feedback.Key.IMAGE_PATH))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException | IOException | ServletException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while alien image updating: {} {} {}", e.getMessage(), e.getStackTrace(),
					e);
			router = new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}
package by.shyshaliaksey.webproject.controller.command.impl.admin;


import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.controller.InitParameter;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.AddNewUpdateAlienResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateAlienCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			String alienSmallDescription = request.getParameter(RequestParameter.ALIEN_SMALL_DESCRIPTION.getValue());
			String alienFullDescription = request.getParameter(RequestParameter.ALIEN_FULL_DESCRIPTION.getValue());
			Part alienImage = request.getPart(RequestParameter.ALIEN_NEW_IMAGE.getValue());
			String rootFolder = request.getServletContext()
					.getInitParameter(InitParameter.WEB_APP_ROOT_FOLDER_PARAMETER.getValue());
			String serverDeploymentPath = request.getServletContext()
					.getRealPath(FolderPath.ALIEN_IMAGE_FOLDER.getValue());
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AdminService adminService = serviceProvider.getAdminService();
			AddNewUpdateAlienResultInfo addNewAlienResult = adminService.updateAlien(alienId, alienName, alienSmallDescription, alienFullDescription, alienImage, rootFolder, serverDeploymentPath);
			// TODO to separate class, create new constants
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_STATUS.getValue(),
							addNewAlienResult.isAlienNameCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_STATUS.getValue(),
							addNewAlienResult.isAlienSmallDescriptionCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_FEEDBACK.getValue(),
							addNewAlienResult.isAlienFullDescriptionCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_FEEDBACK.getValue(),
							addNewAlienResult.isAlienImageCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_FEEDBACK.getValue(),
							addNewAlienResult.getAlienNameErrorInfo())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_FEEDBACK.getValue(),
							addNewAlienResult.getAlienSmallDescriptionErrorInfo())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_FEEDBACK.getValue(),
							addNewAlienResult.getAlienFullDescriptionErrorInfo())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_FEEDBACK.getValue(),
							addNewAlienResult.getAlienImageErrorInfo())
					.toString();
			if (addNewAlienResult.isAlienNameCorrect() && addNewAlienResult.isAlienSmallDescriptionCorrect()
					&& addNewAlienResult.isAlienFullDescriptionCorrect() && addNewAlienResult.isAlienImageCorrect()) {
				response.setStatus(200);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			} else {
				response.setStatus(400);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			}
		} catch (ServiceException | IOException | ServletException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while alien adding: {} {} {}", e.getMessage(), e.getStackTrace(),
					e);
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}
	

}

package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.ImageUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginUpdateResultInfo;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateUserImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			Part part = request.getPart(RequestParameter.NEW_IMAGE.getValue());
			int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			UserService userService = serviceProvider.getUserService();
			String rootFolder = request.getServletContext().getInitParameter(InitParameter.WEB_APP_ROOT_FOLDER_PARAMETER.getValue());
			String serverDeploymentPath = request.getServletContext().getRealPath(FolderPath.PROFILE_IMAGE_FOLDER.getValue());
			
			ImageUpdateResultInfo imageUpdateResult = userService.updateImage(serverDeploymentPath, rootFolder, part, userId);
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.UPDATE_IMAGE_RESULT_INFO_IMAGE_STATUS.getValue(), imageUpdateResult.isImageCorrect())
					.put(ErrorFeedback.UPDATE_IMAGE_RESULT_INFO_IMAGE_FEEDBACK.getValue(), imageUpdateResult.getImageErrorInfo())
					.toString();
			if (imageUpdateResult.isImageCorrect()) {
				response.setStatus(200);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			} else {
				response.setStatus(400);
				router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
			}
		} catch (IOException | ServletException | ServiceException e) {
			logger.log(Level.ERROR, "IOException occured while image updating: {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;		
	}

}

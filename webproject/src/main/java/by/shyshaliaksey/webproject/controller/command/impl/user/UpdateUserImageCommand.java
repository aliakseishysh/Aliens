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
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.FolderPath;
import by.shyshaliaksey.webproject.controller.command.InitParameter;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateUserImageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			// String realPath = request.getServletContext().getRealPath(FolderPath.PROFILE_IMAGE_FOLDER.getValue());
			Part part = request.getPart(RequestParameter.NEW_IMAGE.getValue());
			int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			UserService userService = serviceProvider.getUserService();
			String rootFolder = request.getServletContext().getInitParameter(InitParameter.WEB_APP_ROOT_FOLDER_PARAMETER.getValue());
			String serverDeploymentPath = request.getServletContext().getRealPath(FolderPath.PROFILE_IMAGE_FOLDER.getValue());
			boolean result = userService.updateImage(serverDeploymentPath, rootFolder, part, userId);
			router = new Router(null, String.valueOf(result), RouterType.AJAX_RESPONSE);
		} catch (IOException e) {
			logger.log(Level.ERROR, "IOException occured while image updating: {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
		} catch (ServletException e) {
			logger.log(Level.ERROR, "ServletException occured while image updating: {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while image updating: {} {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
		
		//		// String currentUser = request.getParameter(RequestAttribute.CURRENT_USER.getValue());
//		String login = request.getParameter(RequestParameter.LOGIN.getValue());
//		String newLogin = request.getParameter(RequestParameter.NEW_LOGIN.getValue());
//		int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
//		ServiceProvider serviceProvider = ServiceProvider.getInstance();
//		UserService userService = serviceProvider.getUserService();
//		Router router;
//		try {
//			Boolean loginResult = userService.changeLogin(login, newLogin, userId);
//			Optional<User> user = userService.findByLogin(newLogin);
//			if (loginResult && user.isPresent()) {
//				request.getSession().setAttribute(RequestAttribute.CURRENT_USER.getValue(), user.get());
//			}
//			router = new Router(null, loginResult.toString(), RouterType.AJAX_RESPONSE);
//		} catch (ServiceException e) {
//			logger.log(Level.ERROR, "Exception occured while email updating: {}", e.getMessage());
//			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
//		}
//		return router;
//				
	}

}

package by.shyshaliaksey.webproject.controller.command.impl.admin;


import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.controller.InitParameter;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateAlienCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			String alienSmallDescription = request.getParameter(RequestParameter.ALIEN_SMALL_DESCRIPTION.getValue());
			String alienFullDescription = request.getParameter(RequestParameter.ALIEN_FULL_DESCRIPTION.getValue());
			Part alienImage = request.getPart(RequestParameter.ALIEN_NEW_IMAGE.getValue());
			String rootFolder = request.getServletContext().getInitParameter(InitParameter.WEB_APP_ROOT_FOLDER_PARAMETER.getValue());
			String serverDeploymentPath = request.getServletContext().getRealPath(FolderPath.ALIEN_IMAGE_FOLDER.getValue());
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AlienService alienService = serviceProvider.getAlienService();
			boolean result = alienService.updateAlien(alienId, alienName, alienSmallDescription, alienFullDescription, alienImage, rootFolder, serverDeploymentPath);
			router = new Router(null, String.valueOf(result), RouterType.AJAX_RESPONSE);
		} catch (IOException | ServletException | ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while alien updating: {} {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;	
	}

}

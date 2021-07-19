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
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class AdminDeclineAlienCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AdminService adminService = ServiceProvider.getInstance().getAdminService();
	
	@AllowedRoles({Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			String alienId = request.getParameter(RequestParameter.ALIEN_ID.getValue());
			boolean result = adminService.declineAlien(alienId);
			
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.ALIEN_STATUS.getValue(), result)
					.toString();
			if (result) {
				response.setStatus(Feedback.Code.OK.getStatusCode());
			} else {
				response.setStatus(Feedback.Code.INTERNAL_SERVER_ERROR.getStatusCode());
			}
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(Feedback.Code.INTERNAL_SERVER_ERROR.getStatusCode());
			logger.log(Level.ERROR, "Exception occured while alien declining: {} {} {}", e.getMessage(), e.getStackTrace(),
					e);
			router = new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

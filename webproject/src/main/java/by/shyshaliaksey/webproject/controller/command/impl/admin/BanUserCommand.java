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
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class BanUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final AdminService adminService = ServiceProvider.getInstance().getAdminService();
	
	@AllowedRoles({Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		Map<Feedback.Key, Object> result;
		try {
			String userLogin = request.getParameter(RequestParameter.LOGIN.getValue());
			String daysToBan = request.getParameter(RequestParameter.DAYS_TO_BAN.getValue());
			result = adminService.banUser(userLogin, daysToBan);
			LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());
			
			String jsonResponse = new JSONObject()
					.put(Feedback.Key.LOGIN_STATUS.getValue(),
							result.get(Feedback.Key.LOGIN_STATUS))
					.put(Feedback.Key.DAYS_TO_BAN_STATUS.getValue(),
							result.get(Feedback.Key.DAYS_TO_BAN_STATUS))
					.put(Feedback.Key.LOGIN_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.LOGIN_FEEDBACK).toString()))
					.put(Feedback.Key.DAYS_TO_BAN_FEEDBACK.getValue(),
							localeAttribute.getLocalizedMessage(result.get(Feedback.Key.DAYS_TO_BAN_FEEDBACK).toString()))
					.toString();
			response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while user banning: {} {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;	
	}
	
}

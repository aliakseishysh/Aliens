package by.shyshaliaksey.webproject.controller.command.impl.admin;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.feedback.BanUnbanUserResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.PromoteDemoteUserResultInfo;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BanUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			String userLogin = request.getParameter(RequestParameter.LOGIN.getValue());
			String daysToBan = request.getParameter(RequestParameter.DAYS_TO_BAN.getValue());
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AdminService adminService = serviceProvider.getAdminService();
			BanUnbanUserResultInfo banUnbanUserResult = adminService.banUser(userLogin, daysToBan);	
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.BAN_UNBAN_USER_LOGIN_STATUS.getValue(), banUnbanUserResult.isLoginCorrect())
					.put(ErrorFeedback.BAN_UNBAN_USER_RESULT_INFO_LOGIN_FEEDBACK.getValue(), banUnbanUserResult.getLoginErrorInfo())
					.put(ErrorFeedback.BAN_UNBAN_USER_DAYS_TO_BAN_STATUS.getValue(), banUnbanUserResult.isDaysToBanCorrect())
					.put(ErrorFeedback.BAN_UNBAN_USER_RESULT_INFO_DAYS_TO_BAN_FEEDBACK.getValue(), banUnbanUserResult.getDaysToBanErrorInfo())
					.toString();
			if (banUnbanUserResult.isLoginCorrect()) {
				response.setStatus(200);
			} else {
				response.setStatus(400);
			}
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while user banning: {} {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;	
	}

}

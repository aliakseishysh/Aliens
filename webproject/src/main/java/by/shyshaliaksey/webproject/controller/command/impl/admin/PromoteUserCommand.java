package by.shyshaliaksey.webproject.controller.command.impl.admin;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.PromoteDemoteUserResultInfo;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PromoteUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			String userLogin = request.getParameter(RequestParameter.LOGIN.getValue());
			String currentUserLogin = (String) request.getAttribute(RequestAttribute.CURRENT_USER.getValue());
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AdminService adminService = serviceProvider.getAdminService();
			PromoteDemoteUserResultInfo promoteUserResult = adminService.promoteUser(userLogin, currentUserLogin);			
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.PROMOTE_DEMOTE_USER_LOGIN_STATUS.getValue(), promoteUserResult.isLoginCorrect())
					.put(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_LOGIN_FEEDBACK.getValue(), promoteUserResult.getLoginErrorInfo())
					.toString();
			if (promoteUserResult.isLoginCorrect()) {
				response.setStatus(200);
			} else {
				response.setStatus(400);
			}
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while user banning: {} {} {}", e.getMessage(), e.getStackTrace(), e);
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;	
	}

}

package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.Optional;

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
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.AddNewCommentResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddNewCommentCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID.getValue()));
		int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
		String newComment = request.getParameter(RequestParameter.NEW_COMMENT.getValue());
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		Router router;
		try {
			AddNewCommentResultInfo addNewCommentResult = userService.addNewComment(userId, alienId, newComment);
			String jsonResponse = new JSONObject()
					.put(ErrorFeedback.LOGIN_RESULT_INFO_EMAIL_STATUS.getValue(), addNewCommentResult.isCommentCorrect())
					.put(ErrorFeedback.LOGIN_RESULT_INFO_PASSWORD_STATUS.getValue(), addNewCommentResult.getCommentErrorInfo())
					.toString();
			response.setStatus(addNewCommentResult.getStatusCode());
			router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			response.setStatus(500);
			logger.log(Level.ERROR, "Exception occured while email updating: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

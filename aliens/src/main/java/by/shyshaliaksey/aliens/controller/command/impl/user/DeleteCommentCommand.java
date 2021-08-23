package by.shyshaliaksey.aliens.controller.command.impl.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import by.shyshaliaksey.aliens.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for deleting user comment
 * for specific alien through model layer. User can delete his own comment and
 * administrator can delete all comments.
 * 
 * @author Aliaksey Shysh
 * 
 * @see UserService#deleteComment(String, User)
 * 
 */
public class DeleteCommentCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			User currentUser = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
			String commentId = request.getParameter(RequestParameter.COMMENT_ID.getValue());
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			UserService userService = serviceProvider.getUserService();
			boolean deleteCommentResult = userService.deleteComment(commentId, currentUser);
			router = new Router(null, Boolean.toString(deleteCommentResult), Type.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occurred while deleting comment: {}", e.getMessage());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

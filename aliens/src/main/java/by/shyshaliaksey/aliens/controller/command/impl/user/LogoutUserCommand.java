package by.shyshaliaksey.aliens.controller.command.impl.user;

import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Implementer of {@link Command} interface, designed for user log out from
 * system.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class LogoutUserCommand implements Command {

	@AllowedRoles({ Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Router router;
		session.removeAttribute(RequestAttribute.CURRENT_USER.getValue());
		session.setAttribute(RequestAttribute.CURRENT_USER.getValue(), new User(Role.GUEST));
		router = new Router(null, Boolean.TRUE.toString(), Type.AJAX_RESPONSE);
		return router;
	}

}

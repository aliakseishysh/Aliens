package by.shyshaliaksey.aliens.controller.command.impl.open;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening register page.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenRegisterPageCommand implements Command {

	@AllowedRoles({ Role.GUEST })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		return new Router(StaticPath.PAGE_REGISTER_JSP.getValue(), null, Type.FORWARD);

	}

}

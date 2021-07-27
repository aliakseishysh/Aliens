package by.shyshaliaksey.webproject.controller.command.impl.open;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.model.entity.User.Role;
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
		Router router = new Router(StaticPath.PAGE_REGISTER_JSP.getValue(), null, Type.FORWARD);
		return router;

	}

}

package by.shyshaliaksey.webproject.controller.command.impl.open.error;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening error page
 * 404.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class Open404ErrorPageCommand implements Command {

	@AllowedRoles({ Role.GUEST, Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router(StaticPath.ERROR_PAGE_404_JSP.getValue(), null, Type.FORWARD);
		return router;
	}

}

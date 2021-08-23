package by.shyshaliaksey.aliens.controller.command.impl.open;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening administrator
 * functional page.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenAdminFunctionalPageCommand implements Command {

	@AllowedRoles({ User.Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		return new Router(StaticPath.PAGE_ADMIN_FUNCTIONAL_JSP.getValue(), null, Type.FORWARD);
	}

}

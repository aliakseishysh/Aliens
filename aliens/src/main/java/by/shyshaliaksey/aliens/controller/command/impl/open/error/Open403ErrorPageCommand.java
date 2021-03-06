package by.shyshaliaksey.aliens.controller.command.impl.open.error;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening error page
 * 403.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class Open403ErrorPageCommand implements Command {

	@AllowedRoles({ Role.GUEST, Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(StaticPath.ERROR_PAGE_403_JSP.getValue(), null, Type.FORWARD);
	}

}

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
 * Implementer of {@link Command} interface, designed for opening suggest alien
 * page.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenSuggestAlienPageCommand implements Command {

	@AllowedRoles({ Role.USER })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router(StaticPath.PAGE_SUGGEST_ALIEN_JSP.getValue(), null, Type.FORWARD);
		return router;
	}

}

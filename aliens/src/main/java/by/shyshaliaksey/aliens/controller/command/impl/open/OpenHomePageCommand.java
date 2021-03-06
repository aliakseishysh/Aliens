package by.shyshaliaksey.aliens.controller.command.impl.open;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.Alien;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.service.AlienService;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening home page.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenHomePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.GUEST, Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		AlienService alienService = serviceProvider.getAlienService();
		Router router;
		try {
			List<Alien> aliens = alienService
					.findNormalAliens((int) request.getAttribute(RequestAttribute.CURRENT_PAGE.getValue()));
			request.setAttribute(RequestAttribute.ALIEN_LIST.getValue(), aliens);
			router = new Router(StaticPath.PAGE_HOME_JSP.getValue(), null, Type.FORWARD);
		} catch (ServiceException e) {
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
			logger.log(Level.ERROR, "Exception occurred while opening {}: {}", StaticPath.PAGE_HOME_JSP, e.getMessage());
		}
		return router;
	}

}

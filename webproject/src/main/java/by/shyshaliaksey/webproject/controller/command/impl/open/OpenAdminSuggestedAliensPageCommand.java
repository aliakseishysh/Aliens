package by.shyshaliaksey.webproject.controller.command.impl.open;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening administrator
 * suggested aliens page.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenAdminSuggestedAliensPageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		AlienService alienService = serviceProvider.getAlienService();
		Router router;
		try {
			List<Alien> aliens = alienService
					.findUnapprovedAliens((int) request.getAttribute(RequestAttribute.CURRENT_PAGE.getValue()));
			request.setAttribute(RequestAttribute.ALIEN_LIST.getValue(), aliens);
			router = new Router(StaticPath.PAGE_ADMIN_SUGGESTED_ALIENS_JSP.getValue(), null, Type.FORWARD);
		} catch (ServiceException e) {
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
			logger.log(Level.ERROR, "Exception occured while opening {}: {}",
					StaticPath.PAGE_ADMIN_SUGGESTED_ALIENS_JSP, e.getMessage());
		}
		return router;
	}

}

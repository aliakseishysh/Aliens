package by.shyshaliaksey.aliens.controller.command.impl.rating;

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
import by.shyshaliaksey.aliens.model.service.AlienService;
import by.shyshaliaksey.aliens.model.service.RatingService;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for finding user rate for
 * specific alien through model layer.
 * 
 * @author Aliaksey Shysh
 * 
 * @see RatingService
 * 
 */
public class FindUserRateCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			User user = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
			int userId = user.getId();
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			RatingService ratingService = serviceProvider.getRatingService();
			AlienService alienService = serviceProvider.getAlienService();
			Integer alienId = alienService.findAlienId(alienName);
			Integer userRate = ratingService.findUserRate(alienId, userId);
			router = new Router(null, userRate.toString(), Type.AJAX_RESPONSE);
		} catch (ServiceException | NumberFormatException e) {
			logger.log(Level.ERROR, "Error occured while processing FindUserRateCommand: {}", e.getMessage(), e);
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

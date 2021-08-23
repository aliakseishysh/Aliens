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
 * Implementer of {@link Command} interface, designed for updating user rate for
 * specific alien through model layer.
 * 
 * @author Aliaksey Shysh
 * 
 * @see RatingService
 * 
 */
public class UpdateRatingCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			int rateValue = Integer.parseInt(request.getParameter(RequestParameter.RATING_VALUE.getValue()));
			String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
			User user = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
			RatingService ratingService = ServiceProvider.getInstance().getRatingService();
			AlienService alienService = ServiceProvider.getInstance().getAlienService();
			int alienId = alienService.findAlienId(alienName);
			if (ratingService.checkRateExistence(alienId, user.getId())) {
				ratingService.updateRate(alienId, user.getId(), rateValue);
			} else {
				ratingService.addRate(alienId, user.getId(), rateValue);
			}
			Double averageRate = ratingService.calculateAverageRate(alienId);
			router = new Router(null, averageRate.toString(), Type.AJAX_RESPONSE);
		} catch (ServiceException | NumberFormatException e) {
			logger.log(Level.ERROR, "Exception occurred while rating updating: {}", e.getMessage());
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
		}
		return router;
	}

}

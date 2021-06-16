package by.shyshaliaksey.webproject.controller.command.impl.rating;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.RatingService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateRatingCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		int rateValue = Integer.parseInt(request.getParameter(RequestParameter.RATING_VALUE.getValue()));
		String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
		User user = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		RatingService ratingService = serviceProvider.getRatingService();
		AlienService alienService = serviceProvider.getAlienService();
		Router router;
		int alienId;
		try {
			alienId = alienService.findAlienId(alienName);
			if (ratingService.checkRateExistence(alienId, user.getId())) {
				ratingService.updateRate(alienId, user.getId(), rateValue);
			} else {
				ratingService.addRate(alienId, user.getId(), rateValue);
			}
			Double averageRate = ratingService.calculateAverageRate(alienId);
			router = new Router(null, averageRate.toString(), RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while rating updating: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
	}

}

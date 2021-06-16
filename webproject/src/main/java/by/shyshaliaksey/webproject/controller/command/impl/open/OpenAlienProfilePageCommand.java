package by.shyshaliaksey.webproject.controller.command.impl.open;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.RatingDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.RatingService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenAlienProfilePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
		Router router;
		try {
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AlienService alienService = serviceProvider.getAlienService();
			RatingService ratingService = serviceProvider.getRatingService();
			Optional<Alien> alienOptional = alienService.findAlienById(alienId);
			if (alienOptional.isPresent()) {
				Alien alien = alienOptional.get();
				request.setAttribute(RequestAttribute.ALIEN.getValue(), alien);
				double averageRating = ratingService.calculateAverageRate(alienId);
				request.setAttribute(RequestAttribute.AVERAGE_RATING.getValue(), averageRating);
				router = new Router(PagePath.ALIEN_PROFILE_JSP.getValue(), null, RouterType.FORWARD);
			} else {
				router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
				logger.log(Level.INFO, "No alien with id: {}", alienId);
			}
			
		} catch (ServiceException e) {
			router = new Router(PagePath.ERROR_PAGE_JSP.getValue(), null, RouterType.REDIRECT);
			logger.log(Level.ERROR, "Exception occured while alien searching with id {}: {}", alienId, e.getMessage());
		}
		return router;
	}

}
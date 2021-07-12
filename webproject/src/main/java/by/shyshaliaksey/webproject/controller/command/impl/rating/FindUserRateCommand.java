package by.shyshaliaksey.webproject.controller.command.impl.rating;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.RatingService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindUserRateCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		String alienName = request.getParameter(RequestParameter.ALIEN_NAME.getValue());
		User user = (User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
		int userId = user.getId();
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		RatingService ratingService = serviceProvider.getRatingService();
		AlienService alienService = serviceProvider.getAlienService();
		try {
			Integer alienId = alienService.findAlienId(alienName);
			Integer userRate = ratingService.findUserRate(alienId, userId);
			router = new Router(null, userRate.toString(), RouterType.AJAX_RESPONSE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error occured while processing FindUserRateCommand: {}", e.getMessage(), e);
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}

}

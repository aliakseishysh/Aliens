package by.shyshaliaksey.aliens.controller.command.impl.open;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.dao.ColumnName;
import by.shyshaliaksey.aliens.model.entity.Alien;
import by.shyshaliaksey.aliens.model.entity.Comment;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.service.AlienService;
import by.shyshaliaksey.aliens.model.service.RatingService;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Implementer of {@link Command} interface, designed for opening alien profile
 * page.
 * 
 * @author Aliaksey Shysh
 * 
 */
public class OpenAlienProfilePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({ Role.GUEST, Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		try {
			int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
			ServiceProvider serviceProvider = ServiceProvider.getInstance();
			AlienService alienService = serviceProvider.getAlienService();
			RatingService ratingService = serviceProvider.getRatingService();
			Optional<Alien> alienOptional = alienService.findAlienByIdAndStatus(alienId, Alien.Status.NORMAL);
			if (alienOptional.isPresent()) {
				Alien alien = alienOptional.get();
				request.setAttribute(RequestAttribute.ALIEN.getValue(), alien);
				double averageRating = ratingService.calculateAverageRate(alienId);
				request.setAttribute(RequestAttribute.AVERAGE_RATING.getValue(), averageRating);
				List<Comment> comments = alienService.findAllCommentsInPage(alienId,
						(int) request.getAttribute(RequestAttribute.CURRENT_PAGE.getValue()));
				request.setAttribute(RequestAttribute.ALIEN_COMMENTS.getValue(), comments);
				// carousel images
				List<String> imagesUrls = alienService.findImages(alienId);
				JSONArray jsonArray = new JSONArray();
				for (String url : imagesUrls) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put(ColumnName.ALIEN_IMAGE_IMAGE_URL, url);
					jsonArray.put(jsonObject);
				}
				request.setAttribute(RequestAttribute.ALIEN_IMAGES.getValue(), jsonArray);

				router = new Router(StaticPath.PAGE_ALIEN_PROFILE_JSP.getValue(), null, Type.FORWARD);
			} else {
				router = new Router(StaticPath.ERROR_PAGE_404_JSP.getValue(), null, Type.FORWARD);
				logger.log(Level.INFO, "No alien with id: {}", alienId);
			}

		} catch (ServiceException | NumberFormatException e) {
			router = new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD);
			logger.log(Level.ERROR, "Exception occurred while opening {}: {}", StaticPath.PAGE_ALIEN_PROFILE_JSP,
					e.getMessage());
		}
		return router;
	}

}

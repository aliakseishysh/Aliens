package by.shyshaliaksey.webproject.controller.command.impl.open;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.ColumnName;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.RatingDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.AlienPage;
import by.shyshaliaksey.webproject.model.entity.Comment;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.RatingService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenAlienProfilePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@AllowedRoles({Role.GUEST, Role.USER, Role.ADMIN})
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
				// paginating here
				int page = 1;
				Object pageObject = request.getParameter(RequestParameter.PAGE.getValue());
				if (pageObject != null) {
					page = Integer.parseInt(pageObject.toString());
				}
				final int commentsPerPage = AlienPage.COMMENTS_PER_PAGE;
				double commentsCount = alienService.findAlienCommentsCount(alienId);
				int pagesCount = (int) Math.ceil(commentsCount / commentsPerPage);
				request.setAttribute(RequestAttribute.PAGES_COUNT.getValue(), pagesCount);
				request.setAttribute(RequestAttribute.CURRENT_COMMENT_PAGE.getValue(), page);
				List<Comment> comments = alienService.findAllCommentsInPage(alienId, page);
				request.setAttribute(RequestAttribute.ALIEN_COMMENTS.getValue(), comments);
				// carousel images
				List<String> imagesUrls = alienService.findImages(alienId);
				JSONArray jsonArray = new JSONArray();
				for(String url: imagesUrls) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put(ColumnName.ALIEN_IMAGE_IMAGE_URL, url);
					jsonArray.put(jsonObject);
				}
				request.setAttribute(RequestAttribute.ALIEN_IMAGES.getValue(), jsonArray);
				
				
				
				router = new Router(PagePath.PAGE_ALIEN_PROFILE_JSP.getValue(), null, RouterType.FORWARD);
			} else {
				router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
				logger.log(Level.INFO, "No alien with id: {}", alienId);
			}
			
		} catch (ServiceException e) {
			router = new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD);
			logger.log(Level.ERROR, "Exception occured while alien searching with id {}: {}", alienId, e.getMessage());
		}
		return router;
	}

}

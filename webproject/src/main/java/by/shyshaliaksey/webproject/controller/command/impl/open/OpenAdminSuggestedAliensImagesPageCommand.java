package by.shyshaliaksey.webproject.controller.command.impl.open;

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
import by.shyshaliaksey.webproject.model.entity.AdminPage;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.AlienPage;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenAdminSuggestedAliensImagesPageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

	@AllowedRoles({Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		AlienService alienService = serviceProvider.getAlienService();
		Router router;
		try {
			int page = 1;
			Object pageObject = request.getParameter(RequestParameter.PAGE.getValue());
			if (pageObject != null) {
				page = Integer.parseInt(pageObject.toString());
			}
			final int commentsPerPage = AdminPage.IMAGES_PER_PAGE;
			double aliensCount = alienService.findUnapprovedAliensImagesCount();
			int pagesCount = (int) Math.ceil(aliensCount / commentsPerPage);
			request.setAttribute(RequestAttribute.PAGES_COUNT.getValue(), pagesCount);
			request.setAttribute(RequestAttribute.CURRENT_PAGE.getValue(), page);
			List<Alien> aliens = alienService.findUnapprovedAliensImages(page);
			request.setAttribute(RequestAttribute.ALIEN_LIST.getValue(), aliens);
			router = new Router(PagePath.PAGE_ADMIN_SUGGESTED_ALIENS_INAGES_JSP.getValue(), null, RouterType.FORWARD);
		} catch (ServiceException e) {
			router = new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD);
			logger.log(Level.ERROR, "Exception occured while opening {}: {}", PagePath.PAGE_HOME_JSP, e.getMessage());
		}
		return router;
	}

}

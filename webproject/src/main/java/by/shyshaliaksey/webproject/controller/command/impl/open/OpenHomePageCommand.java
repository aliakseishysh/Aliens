package by.shyshaliaksey.webproject.controller.command.impl.open;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.AlienPage;
import by.shyshaliaksey.webproject.model.entity.Comment;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenHomePageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();

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
			final int commentsPerPage = AlienPage.COMMENTS_PER_PAGE;
			double aliensCount = alienService.findAlienCount();
			int pagesCount = (int) Math.ceil(aliensCount / commentsPerPage);
			request.setAttribute(RequestAttribute.PAGES_COUNT.getValue(), pagesCount);
			request.setAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue(), page);
			List<Alien> aliens = alienService.findAllAliens(page);
			request.setAttribute(RequestAttribute.ALIEN_LIST.getValue(), aliens);
			router = new Router(PagePath.PAGE_HOME_JSP.getValue(), null, RouterType.FORWARD);
		} catch (ServiceException e) {
			router = new Router("/" + SessionAttribute.CONTROLLER.getValue() + "?" + RequestParameter.COMMAND.getValue()
					+ "=" + PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
			logger.log(Level.ERROR, "Exception occured while redirecting to {}: {}", PagePath.PAGE_HOME_JSP, e.getMessage());
		}
		return router;
	}

}

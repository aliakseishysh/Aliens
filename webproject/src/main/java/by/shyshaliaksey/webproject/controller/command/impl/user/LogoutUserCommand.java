package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.setAttribute(RequestAttribute.CURRENT_USER.getValue(), null);
		}
		List<Alien> aliens;
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		AlienService alienService = serviceProvider.getAlienService();
		Router router;
		try {
			String pageString = (String) request.getSession().getAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue());
			int pageNumber = 1;
			if (pageString != null) {
				pageNumber = Integer.parseInt(pageString);
				request.getSession().setAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue(), pageNumber);
			} else {
				request.getSession().setAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue(), pageNumber);
			}
			
			aliens = alienService.findAllAliens(pageNumber);
			request.setAttribute(RequestAttribute.ALIEN_LIST.getValue(), aliens);
			router = new Router(PagePath.PAGE_HOME_JSP.getValue(), null, RouterType.FORWARD);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured while logout: {}", e.getMessage());
			router = new Router(PagePath.ERROR_PAGE_404_JSP.getValue(), null, RouterType.REDIRECT);
		}
		return router;
	}

}

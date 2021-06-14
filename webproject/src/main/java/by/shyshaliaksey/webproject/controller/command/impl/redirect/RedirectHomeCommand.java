package by.shyshaliaksey.webproject.controller.command.impl.redirect;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectHomeCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();	
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		AlienService alienService = serviceProvider.getAlienService();
		Router router;
		try {
			List<Alien> aliens = alienService.findAllAliens();
			request.setAttribute("aliensList", aliens);
			router = new Router(PagePath.HOME_JSP, null, RouterType.FORWARD);
		} catch (ServiceException e) {
			router = new Router(PagePath.ERROR_PAGE_JSP, null, RouterType.REDIRECT);
			logger.log(Level.ERROR, "Exception occured while redirecting to {}: {}",PagePath.HOME_JSP, e.getMessage());
		}
		return router;
	}

}

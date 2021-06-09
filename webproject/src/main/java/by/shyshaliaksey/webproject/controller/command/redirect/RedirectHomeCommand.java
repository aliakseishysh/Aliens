package by.shyshaliaksey.webproject.controller.command.redirect;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.FilePath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectHomeCommand extends Command {

	private static final Logger logger = LogManager.getRootLogger();	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AlienDao alienDao = ProviderDao.getAlienDao();
		List<Alien> aliens;
		try {
			aliens = alienDao.findAll();
			request.setAttribute("aliensList", aliens);
			super.redirect(request, response, FilePath.HOME_JSP);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while redirecting to {}: {}",FilePath.HOME_JSP, e.getMessage());
			// TODO redirect to error page
		}
		
	}

}

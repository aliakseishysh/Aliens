package by.shyshaliaksey.webproject.controller.command.redirect;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.FilePath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectUserProfileCommand extends Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserDao userDao = ProviderDao.getUserDao();
		String login = (String) request.getSession(false).getAttribute("login_name");
		try {
			Optional<AbstractUser> currentUser = userDao.findByLogin(login);
			if (currentUser.isPresent()) {
				request.getSession().setAttribute("currentUser", currentUser.get());
				super.redirect(request, response, FilePath.USER_PROFILE_JSP);
			} else {
				logger.log(Level.INFO, "No user with such login: {}", login);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while redirecting to {}: {}",FilePath.USER_PROFILE_JSP, e.getMessage());
			// TODO redirect to error page
		}
	}
	
}

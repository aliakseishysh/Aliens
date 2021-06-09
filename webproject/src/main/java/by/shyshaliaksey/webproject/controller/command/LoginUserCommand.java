package by.shyshaliaksey.webproject.controller.command;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginUserCommand extends Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDao userDao = ProviderDao.getUserDao();
		AlienDao alienDao = ProviderDao.getAlienDao();
		try {
			boolean loginResult = userDao.loginUser(email, password);
			if (loginResult) {
				Optional<AbstractUser> userOptional = userDao.findByEmail(email);
				if (userOptional.isPresent()) {
					AbstractUser user = userOptional.get();
					request.getSession(true).setAttribute("login_name", user.getLogin());
					request.getSession().setAttribute("currentUser", user);
					List<Alien> aliens = alienDao.findAll();
					request.getSession().setAttribute("aliensList", aliens);
					super.redirect(request, response, FilePath.HOME_JSP);
				}
			} else {
				// TODO set error login attribute with this credentials
				super.redirect(request, response, FilePath.LOGIN_JSP);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while user loging: {}", e.getMessage());
			// TODO redirect to error page
		}
		
	}

}

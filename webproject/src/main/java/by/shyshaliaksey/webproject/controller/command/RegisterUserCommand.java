package by.shyshaliaksey.webproject.controller.command;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUserCommand extends Command {

	private static final Logger logger = LogManager.getRootLogger();
	private static final String DEFAULT_IMAGE = "/images/standard.png";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("password_confirm");
		UserDao userDao = ProviderDao.getUserDao();
		try {
			boolean registerResult = userDao.registerUser(email, login, password, DEFAULT_IMAGE, Role.USER);
			if (registerResult) {
				super.redirect(request, response, FilePath.LOGIN_JSP);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while register: {}", e.getMessage());
			// TODO redirect to error page
		}
		
	}

}

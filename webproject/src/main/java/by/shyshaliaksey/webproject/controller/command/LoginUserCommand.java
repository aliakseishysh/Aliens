package by.shyshaliaksey.webproject.controller.command;

import by.shyshaliaksey.webproject.model.dao.AbstractDatabaseController;
import by.shyshaliaksey.webproject.model.dao.MysqlController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginUserCommand extends Command {

	@Override
	public void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		AbstractDatabaseController dbController = new MysqlController();
		boolean loggingResult = dbController.loginUser(email, password);
		if (loggingResult) {
			String login = dbController.getLoginByEmail(email);
			request.getSession(true).setAttribute("login_name", login);
		}
	}

}

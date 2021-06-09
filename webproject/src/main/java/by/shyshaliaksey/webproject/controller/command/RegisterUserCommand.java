package by.shyshaliaksey.webproject.controller.command;


import by.shyshaliaksey.webproject.model.dao.AbstractDatabaseController;
import by.shyshaliaksey.webproject.model.dao.MysqlController;
import by.shyshaliaksey.webproject.model.entity.Role;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUserCommand extends Command {

	private static final String DEFAULT_IMAGE = "/images/standard.png";
	
	@Override
	public void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("password_confirm");
		AbstractDatabaseController dbController = new MysqlController();
		dbController.registerUser(email, login, password, DEFAULT_IMAGE, Role.USER);
	}

}

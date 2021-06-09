package by.shyshaliaksey.webproject.controller.command;


import by.shyshaliaksey.webproject.model.dao.MysqlController;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SetCurrentUser extends Command {

	@Override
	public void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		MysqlController mysqlController = new MysqlController();
		String login = (String) request.getSession(false).getAttribute("login_name");
		AbstractUser currentUser = mysqlController.getUserInfoByLogin(login);
		request.getSession().setAttribute("currentUser", currentUser);
	}

}

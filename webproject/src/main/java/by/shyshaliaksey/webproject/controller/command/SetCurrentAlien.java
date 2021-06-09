package by.shyshaliaksey.webproject.controller.command;


import by.shyshaliaksey.webproject.model.dao.MysqlController;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SetCurrentAlien extends Command {

	@Override
	public void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		MysqlController mysqlController = new MysqlController();
		int alienId = Integer.parseInt(request.getParameter("alien_id"));
		Alien alien = mysqlController.getAlienInfoById(alienId);
		request.setAttribute("alien", alien);
	}

}

package by.shyshaliaksey.webproject.controller.command;

import java.util.List;

import by.shyshaliaksey.webproject.model.dao.MysqlController;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetAliensListCommand extends Command {

	@Override
	public void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		MysqlController mysqlController = new MysqlController();
		List<Alien> aliens = mysqlController.getAllAliens();
		request.setAttribute("aliensList", aliens);
	}

}

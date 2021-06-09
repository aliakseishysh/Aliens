package by.shyshaliaksey.webproject.controller.command;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Command {

	public abstract void execute(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);
	
}

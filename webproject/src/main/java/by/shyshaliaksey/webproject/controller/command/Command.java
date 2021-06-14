package by.shyshaliaksey.webproject.controller.command;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	
	public abstract Router execute(HttpServletRequest request, HttpServletResponse response);
	
	public default void forward(HttpServletRequest request, HttpServletResponse response, String pathToForward) {
		ServletContext servletContext = request.getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pathToForward);
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

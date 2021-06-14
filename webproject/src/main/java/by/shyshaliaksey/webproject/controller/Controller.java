package by.shyshaliaksey.webproject.controller;

import static by.shyshaliaksey.webproject.controller.command.RequestParameter.COMMAND;
import static by.shyshaliaksey.webproject.controller.command.PagePath.ERROR_PAGE_JSP;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandFactory;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;


/**
 * Servlet implementation class Controller
 */
//@WebServlet(name="Controller", urlPatterns={"/controller"})
public class Controller extends HttpServlet implements Servlet {
       
	private static final Logger logger = LogManager.getRootLogger();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		ConnectionPool.getInstance().destroyConnectionPool();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String commandName = request.getParameter(COMMAND);
		Command command = CommandFactory.defineCommand(commandName);
		Router router = command.execute(request, response);
		switch (router.getRouterType()) {
		case FORWARD:
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
			requestDispatcher.forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(router.getPagePath());
			break;
		case AJAX_RESPONSE:
			response.getWriter().write(router.getResponseParameter());
			break;
		default:
			logger.log(Level.ERROR, "Invalid RouterType value: {}", commandName);
			response.sendRedirect(ERROR_PAGE_JSP);
		}
	}

}

package by.shyshaliaksey.webproject.controller;

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

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandFactory;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;


/**
 * Servlet implementation class Controller
 */
//@WebServlet(name="Controller", urlPatterns={"/controller"})
public class Controller extends HttpServlet implements Servlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    @Override
	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		ConnectionPool.getInstance().destroyConnectionPool();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Command command = CommandFactory.defineCommand(request);
		command.execute(request, response);
	}

}

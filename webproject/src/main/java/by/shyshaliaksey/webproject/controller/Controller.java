package by.shyshaliaksey.webproject.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static by.shyshaliaksey.webproject.controller.PagePath.ERROR_PAGE_404_JSP;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandFactory;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.model.entity.Role;


/**
 * Servlet implementation class Controller
 */
@MultipartConfig
@WebServlet(name="Controller", urlPatterns={"/controller"})
public class Controller extends HttpServlet implements Servlet {
       
	private static final Logger logger = LogManager.getRootLogger();
	
    public Controller() {
        super();
    }
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
	
	//@AllowedRoles
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		try {
			Command command = CommandFactory.defineCommand(commandName);
			Router router = command.proceed(request, response);
			
			switch (router.getRouterType()) {
			case FORWARD:
				RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
				dispatcher.forward(request, response);
				break;
			case REDIRECT:
				response.sendRedirect(request.getContextPath() + router.getPagePath());
				break;
			case AJAX_RESPONSE:
				if (router.getResponseParameter() == null) {
					request.getRequestDispatcher(router.getPagePath())
					   	   .forward(request, response);
				} else {
					response.getWriter().write(router.getResponseParameter());					
				}
				break;
			default:
				logger.log(Level.ERROR, "Invalid RouterType value: {}", commandName);
				response.sendRedirect(request.getContextPath() + ERROR_PAGE_404_JSP.getValue());
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Server Error: Cause: {} Message: {}", e.getCause(), e.getMessage());
			// request.getSession().setAttribute(SessionAttribute.ERROR_INFO.getValue(), e);
			response.sendRedirect(request.getContextPath() + ERROR_PAGE_404_JSP.getValue());
		}
		
	}

}

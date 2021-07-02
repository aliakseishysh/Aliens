package by.shyshaliaksey.webproject.controller;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static by.shyshaliaksey.webproject.controller.PagePath.ERROR_PAGE_404_JSP;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandFactory;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.SessionService;
import by.shyshaliaksey.webproject.model.service.UserService;


/**
 * Servlet implementation class Controller
 */

@MultipartConfig
@WebServlet(name="Controller", urlPatterns={"/jsp/controller", "/controller"}, loadOnStartup=0)
public class Controller extends HttpServlet implements Servlet {
       
	private static final Logger logger = LogManager.getRootLogger();
	
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// super.init();
        ConnectionPool.getInstance();
        ServiceProvider.getInstance();
        DaoProvider.getInstance();
    }

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
		
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		UserService userService = ServiceProvider.getInstance().getUserService();
		if (commandName == null) {
			commandName = CommandValue.OPEN_SERVER_ERROR_PAGE.getValue();
		}
		else if (userService.isUserBanned(request.getSession()) && !commandName.equals(CommandValue.LOGOUT_USER.getValue())) {
			commandName = CommandValue.OPEN_BANNED_PAGE.getValue();
		}
		try {
			Command command = CommandFactory.defineCommand(commandName);
			Router router = command.execute(request, response);
			
			switch (router.getRouterType()) {
			case FORWARD:
					request.getRequestDispatcher(router.getPagePath())
						   .forward(request, response);
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

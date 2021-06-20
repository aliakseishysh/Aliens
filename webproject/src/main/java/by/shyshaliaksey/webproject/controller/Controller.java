package by.shyshaliaksey.webproject.controller;

import static by.shyshaliaksey.webproject.controller.command.PagePath.ERROR_PAGE_JSP;
import static by.shyshaliaksey.webproject.controller.command.PagePath.INDEX_JSP;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.CommandFactory;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.controller.command.EnumValue;
import by.shyshaliaksey.webproject.controller.command.FilePath;
import by.shyshaliaksey.webproject.controller.command.FolderPath;
import by.shyshaliaksey.webproject.controller.command.InitParameter;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.SessionAttribute;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.UserStatus;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.SessionService;


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
		setSessionVariables(request.getSession());
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		Command command;
		try {
			command = CommandFactory.defineCommand(commandName);
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
				response.sendRedirect(request.getContextPath() + ERROR_PAGE_JSP.getValue());
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Server Error: Cause: {} Message: {}", e.getCause(), e.getMessage());
			request.getSession().setAttribute(SessionAttribute.ERROR_INFO.getValue(), e);
			response.sendRedirect(request.getContextPath() + ERROR_PAGE_JSP.getValue());
		}
		
	}
	
	
	private void setSessionVariables(HttpSession session) {
//		String n = CommandValue.class.getName();
//		session.setAttribute(n, CommandValue.values());
//		session.setAttribute(FilePath.class.getName(), FilePath.values());
//		session.setAttribute(PagePath.class.getName(), PagePath.values());
//		session.setAttribute(RequestAttribute.class.getName(), RequestAttribute.values());
//		session.setAttribute(RequestParameter.class.getName(), RequestParameter.values());
//		session.setAttribute(SessionAttribute.class.getName(), SessionAttribute.values());
//		session.setAttribute(Role.class.getName(), Role.values());
		
//		String webSiteName = session.getServletContext().getInitParameter(InitParameter.WEB_SITE_NAME.getValue());
//		session.setAttribute(InitParameter.WEB_SITE_NAME.name(), webSiteName);
		
		setEnumSessionVariables(session, CommandValue.values());
		setEnumSessionVariables(session, FilePath.values());
		setEnumSessionVariables(session, PagePath.values());
//		setEnumSessionVariables(session, RequestAttribute.values());
		setEnumSessionVariables(session, RequestParameter.values());
		setEnumSessionVariables(session, SessionAttribute.values());
		setEnumSessionVariables(session, Role.values());
		setEnumSessionVariables(session, UserStatus.values());
	}
	// ${sessionScope[CommandValue][]}
	
	private <T extends Enum<?>> void setEnumSessionVariables(HttpSession session, T[] enumValues) {
		for(T enumValue: enumValues) {
			String enumName = enumValue.name();
			EnumValue enumValueInterface = (EnumValue) enumValue;
			session.setAttribute(enumName, enumValueInterface.getValue());
		}
	}


}

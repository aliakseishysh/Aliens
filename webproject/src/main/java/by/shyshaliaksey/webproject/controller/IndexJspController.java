package by.shyshaliaksey.webproject.controller;

import java.io.IOException;

import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.controller.command.EnumValue;
import by.shyshaliaksey.webproject.controller.command.FilePath;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.SessionAttribute;
import by.shyshaliaksey.webproject.model.entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="IndexJspController", urlPatterns={"/indexJspController"})
public class IndexJspController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		setSessionVariables(request.getSession());
		request.getRequestDispatcher("/controller?command=home")
			   .forward(request, response);
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
		setEnumSessionVariables(session, CommandValue.values());
		setEnumSessionVariables(session, FilePath.values());
		setEnumSessionVariables(session, PagePath.values());
		setEnumSessionVariables(session, RequestAttribute.values());
		setEnumSessionVariables(session, RequestParameter.values());
		setEnumSessionVariables(session, SessionAttribute.values());
		setEnumSessionVariables(session, Role.values());
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

package by.shyshaliaksey.webproject.controller.listener;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.model.entity.Role;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionCreated(se);
		setSessionVariables(se.getSession());
		// se.getSession().setAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue(), 1);
		// se.getSession().setAttribute(RequestAttribute.CURRENT_COMMENT_PAGE.getValue(), 1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionDestroyed(se);
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

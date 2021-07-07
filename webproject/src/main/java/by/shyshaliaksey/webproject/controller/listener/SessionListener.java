package by.shyshaliaksey.webproject.controller.listener;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.FormPattern;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		setSessionVariables(se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
	private void setSessionVariables(HttpSession session) {
		setEnumSessionVariables(session, ErrorFeedback.values());
		setEnumSessionVariables(session, CommandValue.values());
		setEnumSessionVariables(session, FilePath.values());
		setEnumSessionVariables(session, PagePath.values());
//		setEnumSessionVariables(session, RequestAttribute.values());
		setEnumSessionVariables(session, RequestParameter.values());
		setEnumSessionVariables(session, SessionAttribute.values());
		setEnumSessionVariables(session, Role.values());
		setEnumSessionVariables(session, FormPattern.values());
		
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

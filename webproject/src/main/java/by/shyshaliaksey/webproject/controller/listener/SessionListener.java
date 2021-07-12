package by.shyshaliaksey.webproject.controller.listener;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.ApplicationAttribute;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.entity.FormPattern;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
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
		session.setAttribute(RequestAttribute.CURRENT_USER.getValue(), new User(Role.GUEST));
		session.setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), LocaleAttribute.LOCALIZATION_EN.getValue());
		session.setAttribute(SessionAttribute.TEXT.name(), LocaleAttribute.LOCALIZATION_EN.getResourceBundle());
		session.setAttribute(SessionAttribute.CURRENT_LOCALE.name(), LocaleAttribute.LOCALIZATION_EN.getLocale());
		
//		setEnumSessionVariables(session, RequestAttribute.values());

		
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

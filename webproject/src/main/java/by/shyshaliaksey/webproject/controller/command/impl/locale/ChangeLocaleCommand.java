package by.shyshaliaksey.webproject.controller.command.impl.locale;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.localization.LocaleKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

	@AllowedRoles({Role.GUEST, Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String requestedLocale = request.getParameter(RequestParameter.LOCALE.getValue());
		LocaleAttribute locale = LocaleAttribute.fromString(requestedLocale);
		HttpSession session = request.getSession();
		request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), locale.getValue());
		request.getSession().setAttribute(SessionAttribute.TEXT.name(), locale.getResourceBundle());
		
		session.setAttribute(LocaleKey.STANDARD_EMAIL_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_EMAIL_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_LOGIN_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_LOGIN_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_PASSWORD_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_PASSWORD_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_PASSWORD_CONFIRMATION_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_PASSWORD_CONFIRMATION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_IMAGE_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_IMAGE_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_DAYS_TO_BAN_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_DAYS_TO_BAN_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_NAME_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_NAME_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_COMMENT_FEEDBACK.name(), locale.getLocalizedMessage(LocaleKey.STANDARD_COMMENT_FEEDBACK.getValue()));
		
		Router router = new Router(null, Boolean.TRUE.toString(), RouterType.AJAX_RESPONSE);
		return router;
	}

}

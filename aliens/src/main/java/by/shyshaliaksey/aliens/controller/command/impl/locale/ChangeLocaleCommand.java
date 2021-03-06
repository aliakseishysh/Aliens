package by.shyshaliaksey.aliens.controller.command.impl.locale;

import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.SessionAttribute;
import by.shyshaliaksey.aliens.controller.command.AllowedRoles;
import by.shyshaliaksey.aliens.controller.command.Command;
import by.shyshaliaksey.aliens.controller.command.Router;
import by.shyshaliaksey.aliens.controller.command.Router.Type;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.aliens.model.util.localization.LocaleKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Implementer of {@link Command} interface, designed for changing current locale.
 * 
 * @author Aliaksey Shysh
 *
 * @see LocaleAttribute
 * 
 */
public class ChangeLocaleCommand implements Command {

	@AllowedRoles({ Role.GUEST, Role.USER, Role.ADMIN })
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String requestedLocale = request.getParameter(RequestParameter.LOCALE.getValue());
		LocaleAttribute locale = LocaleAttribute.fromString(requestedLocale);
		HttpSession session = request.getSession();
		request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), locale.getValue());
		request.getSession().setAttribute(SessionAttribute.TEXT.name(), locale.getResourceBundle());
		request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALE.name(), locale);
		session.setAttribute(SessionAttribute.CURRENT_LOCALE_ABBREVIATION.name(), locale.getLocale().toString());
		// for client side validation feedback
		session.setAttribute(LocaleKey.STANDARD_EMAIL_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_EMAIL_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_LOGIN_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_LOGIN_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_PASSWORD_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_PASSWORD_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_PASSWORD_CONFIRMATION_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_PASSWORD_CONFIRMATION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_IMAGE_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_IMAGE_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_DAYS_TO_BAN_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_DAYS_TO_BAN_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_NAME_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_NAME_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_COMMENT_FEEDBACK.name(),
				locale.getLocalizedMessage(LocaleKey.STANDARD_COMMENT_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.name(),
				locale.getLocalizedMessage(LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue()));

		return new Router(null, Boolean.TRUE.toString(), Type.AJAX_RESPONSE);
	}

}

package by.shyshaliaksey.aliens.controller.listener;

import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.SessionAttribute;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.aliens.model.util.localization.LocaleKey;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Class designed for setting variables into session.
 * 
 * @author Aliaksey Shysh
 *
 */
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

	/**
	 * @param session current {@code HttpSession}
	 */
	private void setSessionVariables(HttpSession session) {
		session.setAttribute(RequestAttribute.CURRENT_USER.getValue(), new User(Role.GUEST));
		session.setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(),
				LocaleAttribute.LOCALIZATION_EN.getValue());
		session.setAttribute(SessionAttribute.TEXT.name(), LocaleAttribute.LOCALIZATION_EN.getResourceBundle());
		session.setAttribute(SessionAttribute.CURRENT_LOCALE.name(), LocaleAttribute.LOCALIZATION_EN);
		session.setAttribute(SessionAttribute.CURRENT_LOCALE_ABBREVIATION.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocale().toString());

		session.setAttribute(LocaleKey.STANDARD_EMAIL_FEEDBACK.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocalizedMessage(LocaleKey.STANDARD_EMAIL_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_LOGIN_FEEDBACK.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocalizedMessage(LocaleKey.STANDARD_LOGIN_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_PASSWORD_FEEDBACK.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocalizedMessage(LocaleKey.STANDARD_PASSWORD_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_PASSWORD_CONFIRMATION_FEEDBACK.name(), LocaleAttribute.LOCALIZATION_EN
				.getLocalizedMessage(LocaleKey.STANDARD_PASSWORD_CONFIRMATION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_IMAGE_FEEDBACK.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocalizedMessage(LocaleKey.STANDARD_IMAGE_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_DAYS_TO_BAN_FEEDBACK.name(), LocaleAttribute.LOCALIZATION_EN
				.getLocalizedMessage(LocaleKey.STANDARD_DAYS_TO_BAN_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_NAME_FEEDBACK.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_NAME_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK.name(), LocaleAttribute.LOCALIZATION_EN
				.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK.name(), LocaleAttribute.LOCALIZATION_EN
				.getLocalizedMessage(LocaleKey.STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.STANDARD_COMMENT_FEEDBACK.name(),
				LocaleAttribute.LOCALIZATION_EN.getLocalizedMessage(LocaleKey.STANDARD_COMMENT_FEEDBACK.getValue()));
		session.setAttribute(LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.name(),
				LocaleAttribute.LOCALIZATION_EN
						.getLocalizedMessage(LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue()));

	}

}

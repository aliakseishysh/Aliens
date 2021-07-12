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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocaleCommand implements Command {

	@AllowedRoles({Role.GUEST, Role.USER, Role.ADMIN})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String requestedLocale = request.getParameter(RequestParameter.LOCALE.getValue());
		LocaleAttribute locale = LocaleAttribute.fromString(requestedLocale);
		request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), locale.getValue());
		request.getSession().setAttribute(SessionAttribute.TEXT.name(), locale.getResourceBundle());
		Router router = new Router(null, Boolean.TRUE.toString(), RouterType.AJAX_RESPONSE);
		return router;
	}

}

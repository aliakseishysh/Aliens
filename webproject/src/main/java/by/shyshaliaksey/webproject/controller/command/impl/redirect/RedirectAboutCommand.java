package by.shyshaliaksey.webproject.controller.command.impl.redirect;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.PagePath;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectAboutCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router(PagePath.ABOUT_JSP, null, RouterType.FORWARD);
		return router;
	}

}

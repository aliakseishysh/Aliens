package by.shyshaliaksey.webproject.controller.command.impl.open;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenForgotPasswordPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router(PagePath.PAGE_FORGOT_PASSWORD_JSP.getValue(), null, RouterType.FORWARD);
		return router;
	}

}
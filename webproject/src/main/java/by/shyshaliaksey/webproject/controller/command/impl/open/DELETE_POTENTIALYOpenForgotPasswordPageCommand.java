package by.shyshaliaksey.webproject.controller.command.impl.open;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DELETE_POTENTIALYOpenForgotPasswordPageCommand implements Command {

	// TODO if have time
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		return router;
	}

}

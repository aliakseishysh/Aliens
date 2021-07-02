package by.shyshaliaksey.webproject.controller.command.impl.load;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoadUserImage  implements Command  {


	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router(PagePath.TEMPLATE_USER_IMAGE_JSP.getValue(), null, RouterType.AJAX_RESPONSE);
		return router;
	}
	
}

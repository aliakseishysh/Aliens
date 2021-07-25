package by.shyshaliaksey.webproject.controller.command.impl.open;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.AllowedRoles;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UtilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenLoginPageCommand implements Command {

	@AllowedRoles({Role.GUEST})
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getParameter(RequestParameter.TOKEN.getValue());
		Router router;
		if (token != null) {
			UtilService utilService = ServiceProvider.getInstance().getUtilService();
			try {
				utilService.activateAccount(token);
			} catch (ServiceException e) {
				// TODO sdf
				// router = new Router(PagePath.PAGE_LOGIN_JSP.getValue(), null, RouterType.FORWARD);
			}
		}
		router = new Router(PagePath.PAGE_LOGIN_JSP.getValue(), null, RouterType.FORWARD);
		return router;
	}

}

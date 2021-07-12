package by.shyshaliaksey.webproject.controller.command;

import java.lang.reflect.Method;
import java.util.Arrays;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	
	public abstract Router execute(HttpServletRequest request, HttpServletResponse response);

	public default Router proceed(HttpServletRequest request, HttpServletResponse response) {
		Class<? extends Command> clazz = this.getClass();
		Router router;
		try {
			final String methodName = "execute";
			Class[] methodArgumentsClasses = new Class[2];
			methodArgumentsClasses[0] = HttpServletRequest.class;
			methodArgumentsClasses[1] = HttpServletResponse.class;
			Method method = clazz.getMethod(methodName, methodArgumentsClasses);
			AllowedRoles allowedRolesAnnotation = method.getAnnotation(AllowedRoles.class);
			Role[] allowedRoles;
			if (allowedRolesAnnotation == null) {
				allowedRoles = new Role[1];
				allowedRoles[0] = Role.GUEST;
			} else {
				allowedRoles = allowedRolesAnnotation.value();
			}
			Role currentUserRole = ((User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue())).getRole();
			if (Arrays.asList(allowedRoles).contains(currentUserRole)) {
				router = execute(request, response);
			} else {
				response.setStatus(403);
				router = new Router(PagePath.ERROR_PAGE_403_JSP.getValue(), null, RouterType.FORWARD);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
		}
		return router;
	}
	
}

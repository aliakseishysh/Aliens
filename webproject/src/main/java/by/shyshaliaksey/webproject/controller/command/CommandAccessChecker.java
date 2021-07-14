package by.shyshaliaksey.webproject.controller.command;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandAccessChecker {

	public enum MapKey {
		RESULT,
		ROUTER;
	}
	
	public static Map<MapKey, Object> isUserHasPermission(Command command, HttpServletRequest request, HttpServletResponse response) {
		Class<? extends Command> clazz = command.getClass();
		Map<MapKey, Object> result = new HashMap<>();
		try {
			// Command.execute(HttpServletRequest request, HttpServletResponse response)
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
				result.put(MapKey.RESULT, Boolean.TRUE);
				result.put(MapKey.ROUTER, null);
			} else {
				response.setStatus(403);
				result.put(MapKey.RESULT, Boolean.FALSE);
				result.put(MapKey.ROUTER, new Router(PagePath.ERROR_PAGE_403_JSP.getValue(), null, RouterType.FORWARD));
			}
		} catch (NoSuchMethodException | SecurityException e) {
			result.put(MapKey.RESULT, Boolean.FALSE);
			result.put(MapKey.ROUTER, new Router(PagePath.ERROR_PAGE_500_JSP.getValue(), null, RouterType.FORWARD));
		}
		return result;
	}
	
}

package by.shyshaliaksey.webproject.controller.command;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.Router.Type;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandAccessChecker {

	public enum MapKey {
		RESULT, ROUTER;
	}

	/**
	 * 
	 * @param command  Current Command instance, obtained from
	 *                 {@link by.shyshaliaksey.webproject.controller.command.CommandDefiner }
	 * @param request  Extends the jakarta.servlet.ServletRequest interface to
	 *                 provide request information for HTTP servlets. The servlet
	 *                 container creates an HttpServletRequest object and passes it
	 *                 as an argument to the servlet's service methods (doGet,
	 *                 doPost, etc).
	 * @param response Extends the ServletResponse interface to provide
	 *                 HTTP-specific functionality in sending a response. For
	 *                 example, it has methods to access HTTP headers and cookies.
	 *                 The servlet container creates an HttpServletResponse object
	 *                 and passes it as an argument to theservlet's service methods
	 *                 (doGet, doPost, etc).
	 * @return {@link MapKey#RESULT} Boolean object and {@link MapKey#ROUTER} to
	 *         error page if result is false
	 */
	public static Map<MapKey, Object> isUserHasPermission(Command command, HttpServletRequest request,
			HttpServletResponse response) {
		Class<? extends Command> clazz = command.getClass();
		Map<MapKey, Object> result = new EnumMap<>(MapKey.class);
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
			Role currentUserRole = ((User) request.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue()))
					.getRole();
			if (Arrays.asList(allowedRoles).contains(currentUserRole)) {
				result.put(MapKey.RESULT, Boolean.TRUE);
				result.put(MapKey.ROUTER, null);
			} else {
				response.setStatus(403);
				result.put(MapKey.RESULT, Boolean.FALSE);
				result.put(MapKey.ROUTER,
						new Router(StaticPath.ERROR_PAGE_403_JSP.getValue(), null, Type.FORWARD));
			}
		} catch (NoSuchMethodException | SecurityException e) {
			response.setStatus(500);
			result.put(MapKey.RESULT, Boolean.FALSE);
			result.put(MapKey.ROUTER, new Router(StaticPath.ERROR_PAGE_500_JSP.getValue(), null, Type.FORWARD));
		}
		return result;
	}

}

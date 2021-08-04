package by.shyshaliaksey.aliens.controller.filter;

import java.io.IOException;
import java.util.Optional;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.CommandDefiner;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.User;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import by.shyshaliaksey.aliens.model.service.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Implementer of {@code Filter} designed for forward to ban page if user is
 * banned.
 * 
 * @author Aliaksey Shysh
 *
 */
@WebFilter(filterName = "BanFilter", urlPatterns = "/*")
public class BanFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			boolean result = isUserBanned(request);
			if (result) {
				forwardBannedPage(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} catch (ServiceException e) {
			forward500ErrorPage(request, response);
		}
	}

	private boolean isUserBanned(ServletRequest request) throws ServiceException {
		boolean result = false;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		UserService userService = ServiceProvider.getInstance().getUserService();
		User user = (User) httpRequest.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
		if (user != null) {
			Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());
			if (optionalUser.isPresent()) {
				User userFromDatabase = optionalUser.get();
				user.setStatus(userFromDatabase.getStatus());
				user.setBannedToDate(userFromDatabase.getBannedToDate());
				if (user.getStatus() == User.Status.BANNED && commandName != null
						&& !commandName.equals(CommandDefiner.LOGOUT_USER.getValue())
						&& !commandName.equals(CommandDefiner.CHANGE_LOCALE.getValue())) {
					result = true;
				}
			}
		}
		return result;
	}

	private void forwardBannedPage(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher(StaticPath.PAGE_BANNED_JSP.getValue()).forward(request, response);
	}

	private void forward500ErrorPage(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher(StaticPath.ERROR_PAGE_500_JSP.getValue()).forward(request, response);
	}

}

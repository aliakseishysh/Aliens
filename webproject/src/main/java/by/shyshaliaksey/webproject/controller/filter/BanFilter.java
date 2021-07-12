package by.shyshaliaksey.webproject.controller.filter;

import java.io.IOException;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.model.entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(filterName = "BanFilter", urlPatterns = "/*")
public class BanFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (isUserBanned(request, response)) { 
			forwardBannedPage(request, response); 
		} else {
			chain.doFilter(request, response);			
		}
	}

	private boolean isUserBanned(ServletRequest request, ServletResponse response) {
		boolean result = false;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
		User user = (User) httpRequest.getSession().getAttribute(RequestAttribute.CURRENT_USER.getValue());
		if (user != null && user.getUserStatus() == User.UserStatus.BANNED
				&& commandName != null && !commandName.equals(CommandValue.LOGOUT_USER.getValue())
				&& !commandName.equals(CommandValue.CHANGE_LOCALE.getValue())) {
			result = true;
		}
		return result;
	}
	
	private void forwardBannedPage(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher(PagePath.PAGE_BANNED_JSP.getValue()).forward(request, response);
	}

}

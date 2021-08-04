package by.shyshaliaksey.aliens.controller.filter;

import java.io.IOException;

import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.PaginationConfiguration;
import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.CommandDefiner;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.service.AlienService;
import by.shyshaliaksey.aliens.model.service.ServiceProvider;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Implementer of {@code Filter} designed for navigation over all pages that
 * supports navigation.
 * 
 * @author Aliaksey Shysh
 *
 */
@WebFilter(filterName = "PaginationFilter", urlPatterns = { "/index.jsp", "/controller" })
public class PaginationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String requestedPageString = httpServletRequest.getParameter(RequestParameter.PAGE.getValue());
			String commandString = httpServletRequest.getParameter(RequestParameter.COMMAND.getValue());
			CommandDefiner commandDefiner = CommandDefiner.fromString(commandString);
			AlienService alienService = ServiceProvider.getInstance().getAlienService();
			int requestedPage = 1;
			double aliensCount = 0;
			int pagesCount = 0;
			if (requestedPageString != null) {
				requestedPage = Integer.parseInt(requestedPageString);
			}
			switch (commandDefiner) {
			case OPEN_HOME_PAGE:
				aliensCount = alienService.findAlienCount();
				pagesCount = (int) Math.ceil(aliensCount / PaginationConfiguration.HOME_ALIENS_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			case OPEN_ALIEN_PROFILE_PAGE:
				int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
				double commentsCount = alienService.findAlienCommentsCount(alienId);
				pagesCount = (int) Math.ceil(commentsCount / PaginationConfiguration.ALIEN_PROFILE_COMMENTS_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			case OPEN_ADMIN_SUGGESTED_ALIENS_PAGE:
				aliensCount = alienService.findUnapprovedAlienCount();
				pagesCount = (int) Math.ceil(aliensCount / PaginationConfiguration.ADMIN_SUGGESTED_ALIENS_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			case OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE:
				double imagesCount = alienService.findUnapprovedAliensImagesCount();
				pagesCount = (int) Math.ceil(imagesCount / PaginationConfiguration.ADMIN_SUGGESTED_ALIENS_IMAGES_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			default:
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);

			}
		} catch (NumberFormatException | ServiceException e) {
			request.getRequestDispatcher(StaticPath.ERROR_PAGE_400_JSP.getValue()).forward(request, response);
		}

	}

	/**
	 * 
	 * @param pagesCount    count of pages in current page type
	 * @param requestedPage page obtained from request
	 * 
	 */
	private void setAttributesOrForward(ServletRequest request, ServletResponse response, FilterChain chain,
			int pagesCount, int requestedPage) throws ServletException, IOException {
		if (requestedPage > 0 && requestedPage <= pagesCount || pagesCount == 0 && requestedPage == 1) {
			request.setAttribute(RequestAttribute.PAGES_COUNT.getValue(), pagesCount);
			request.setAttribute(RequestAttribute.CURRENT_PAGE.getValue(), requestedPage);
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher(StaticPath.ERROR_PAGE_400_JSP.getValue()).forward(request, response);
		}
	}

}

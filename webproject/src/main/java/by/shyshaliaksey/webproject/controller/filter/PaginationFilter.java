package by.shyshaliaksey.webproject.controller.filter;

import java.io.IOException;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.AdminPage;
import by.shyshaliaksey.webproject.model.entity.AlienPage;
import by.shyshaliaksey.webproject.model.entity.HomePage;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(filterName = "PaginationFilter", urlPatterns = { "/index.jsp", "/controller" })
public class PaginationFilter implements Filter {

	@SuppressWarnings("incomplete-switch")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestedPageString = httpServletRequest.getParameter(RequestParameter.PAGE.getValue());
		String commandString = httpServletRequest.getParameter(RequestParameter.COMMAND.getValue());
		CommandValue commandValue = CommandValue.fromString(commandString);
		try {
			AlienService alienService = ServiceProvider.getInstance().getAlienService();
			int requestedPage = 1;
			double aliensCount = 0;
			int pagesCount = 0;
			if (requestedPageString != null) {
				requestedPage = Integer.parseInt(requestedPageString);
			}
			switch (commandValue) {
			case OPEN_HOME_PAGE:
				aliensCount = alienService.findAlienCount();
				pagesCount = (int) Math.ceil(aliensCount / HomePage.ALIENS_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			case OPEN_ALIEN_PROFILE_PAGE:
				int alienId = Integer.parseInt(request.getParameter(RequestParameter.ALIEN_ID.getValue()));
				double commentsCount = alienService.findAlienCommentsCount(alienId);
				pagesCount = (int) Math.ceil(commentsCount / AlienPage.COMMENTS_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			case OPEN_ADMIN_SUGGESTED_ALIENS_PAGE:
				aliensCount = alienService.findUnapprovedAlienCount();
				pagesCount = (int) Math.ceil(aliensCount / AdminPage.ALIENS_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			case OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE:
				double imagesCount = alienService.findUnapprovedAliensImagesCount();
				pagesCount = (int) Math.ceil(imagesCount / AdminPage.IMAGES_PER_PAGE);
				setAttributesOrForward(request, response, chain, pagesCount, requestedPage);
				break;
			default:
				setAttributesOrForward(request, response, chain, requestedPage, requestedPage);

			}
		} catch (NumberFormatException | ServiceException e) {
			request.getRequestDispatcher(PagePath.ERROR_PAGE_400_JSP.getValue()).forward(request, response);
		}
		
	}

	private void setAttributesOrForward(ServletRequest request, ServletResponse response, FilterChain chain, int pagesCount, int requestedPage) throws ServletException, IOException {
		if (requestedPage > 0 && requestedPage <= pagesCount) {
			request.setAttribute(RequestAttribute.PAGES_COUNT.getValue(), pagesCount);
			request.setAttribute(RequestAttribute.CURRENT_PAGE.getValue(), requestedPage);
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher(PagePath.ERROR_PAGE_400_JSP.getValue()).forward(request, response);
		}
	}
	
	
}

package by.shyshaliaksey.aliens.controller.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Arrays;
import java.util.Calendar;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;


/**
 * Implementer of {@code Filter} designed for cache settings.
 * 
 * @author Aliaksey Shysh
 *
 */
@WebFilter(filterName = "CacheFilter", urlPatterns = { "/*" })
public class CacheFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, new AddExpiresHeaderResponse((HttpServletResponse) response));
	}
	
	private static class AddExpiresHeaderResponse extends HttpServletResponseWrapper {

	    private static final String[] CACHEABLE_CONTENT_TYPES = new String[] {
	        "text/css", "text/javascript", "image/png", "image/jpeg", "image/jpg" };

	    static {
	        Arrays.sort(CACHEABLE_CONTENT_TYPES);
	    }

	    private AddExpiresHeaderResponse(HttpServletResponse response) {
	        super(response);
	    }

	    @Override
	    public void setContentType(String contentType) {
	        if (contentType != null && Arrays.binarySearch(CACHEABLE_CONTENT_TYPES, contentType) > -1) {
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(new Date());
	            calendar.add(Calendar.MINUTE, 30);
	            super.setDateHeader("Expires", calendar.getTimeInMillis());
	        } else {
	            super.setHeader("Expires", "-1");
	            super.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        }
	        super.setContentType(contentType);
	    }
	}


}

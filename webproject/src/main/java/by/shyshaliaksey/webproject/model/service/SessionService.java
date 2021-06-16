package by.shyshaliaksey.webproject.model.service;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionService {

	boolean checkSessionInitialized(HttpServletRequest request);
	
}

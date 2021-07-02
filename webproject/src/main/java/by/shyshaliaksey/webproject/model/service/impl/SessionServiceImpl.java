package by.shyshaliaksey.webproject.model.service.impl;

import by.shyshaliaksey.webproject.controller.SessionAttribute;
import by.shyshaliaksey.webproject.model.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;

public class SessionServiceImpl implements SessionService {

	@Override
	public boolean checkSessionInitialized(HttpServletRequest request) {
		String value = (String) request.getSession().getAttribute(SessionAttribute.CONTROLLER.name());
		return  value != null;
	}

}

package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.HomePage;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.service.AlienService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PreviousPageCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router;
		int currentPage = Integer.parseInt(request.getSession().getAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue()).toString());
		if (currentPage <= 1) {
			router = new Router(null, Boolean.FALSE.toString(), RouterType.AJAX_RESPONSE);
		} else {
			request.getSession().setAttribute(RequestAttribute.CURRENT_HOME_PAGE.getValue(), currentPage - 1);
			router = new Router(null, Boolean.TRUE.toString(), RouterType.AJAX_RESPONSE);
		}
		return router;
	}

}

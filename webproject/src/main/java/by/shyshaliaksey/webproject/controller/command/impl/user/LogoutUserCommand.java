package by.shyshaliaksey.webproject.controller.command.impl.user;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.RequestAttribute;
import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.Router;
import by.shyshaliaksey.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutUserCommand implements Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Router router;
		session.removeAttribute(RequestAttribute.CURRENT_USER.getValue());
		session.setAttribute(RequestAttribute.CURRENT_USER.getValue(), null);
		router = new Router(null, Boolean.TRUE.toString(), RouterType.AJAX_RESPONSE);
		return router;
	}

}

package by.shyshaliaksey.webproject.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	
	/**
	 * 
	 * if method name changed, change <b>final String methodName</b> variable from
	 * {@link by.shyshaliaksey.webproject.controller.command.CommandAccessChecker#
	 * isUserHasPermission(Command, HttpServletRequest, HttpServletResponse)}
	 * to new method name
	 * @return Router to proceed ajax response/forward/redirect
	 */
	// if method name changed, change {@link }
	public abstract Router execute(HttpServletRequest request, HttpServletResponse response);
	
}

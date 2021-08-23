package by.shyshaliaksey.aliens.controller.listener;

import by.shyshaliaksey.aliens.controller.EnumValue;
import by.shyshaliaksey.aliens.controller.StaticPath;
import by.shyshaliaksey.aliens.controller.RequestParameter;

import by.shyshaliaksey.aliens.controller.command.CommandDefiner;
import by.shyshaliaksey.aliens.controller.command.Feedback;
import by.shyshaliaksey.aliens.model.connection.ConnectionPool;
import by.shyshaliaksey.aliens.model.entity.User.Role;
import by.shyshaliaksey.aliens.model.service.FormPattern;
import by.shyshaliaksey.aliens.model.util.DeploymentPropertiesReader;
import by.shyshaliaksey.aliens.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.aliens.model.util.localization.LocaleKey;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Class designed for setting variables into servlet context.
 * 
 * @author Aliaksey Shysh
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	/**
	 * Initializes all objects that needed in system
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		ConnectionPool.getInstance();
		setServletContextVariables(sce.getServletContext());
	}

	/**
	 * Destroying all objects on context destroy
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		ConnectionPool.getInstance().destroyConnectionPool();
	}
	
	/**
	 * Setting enum variables to servlet context
	 * @param servletContext current {@code ServletContext} of application
	 */
	private void setServletContextVariables(ServletContext servletContext) {
		setEnumApplicationVariables(servletContext, Feedback.Key.values());
		setEnumApplicationVariables(servletContext, CommandDefiner.values());
		setEnumApplicationVariables(servletContext, StaticPath.values());
		setEnumApplicationVariables(servletContext, RequestParameter.values());
		setEnumApplicationVariables(servletContext, Role.values());
		setEnumApplicationVariables(servletContext, FormPattern.values());
		setEnumApplicationVariables(servletContext, LocaleAttribute.values());
		setEnumApplicationVariables(servletContext, LocaleKey.values());
		setEnumApplicationVariables(servletContext, DeploymentPropertiesReader.Deployment.values());
	}

	/**
	 * 
	 * @param <T> enum to set into {@code servletContext}
	 * @param servletContext current {@code ServletContext} of application
	 * @param enumValues {@code enum.values()}
	 */
	private <T extends Enum<?>> void setEnumApplicationVariables(ServletContext  servletContext, T[] enumValues) {
		for(T enumValue: enumValues) {
			String enumName = enumValue.name();
			EnumValue enumValueInterface = (EnumValue) enumValue;
			servletContext.setAttribute(enumName, enumValueInterface.getValue());
		}
	}
	

}

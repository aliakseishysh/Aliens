package by.shyshaliaksey.webproject.controller.listener;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.StaticPath;
import by.shyshaliaksey.webproject.controller.RequestParameter;

import by.shyshaliaksey.webproject.controller.command.CommandDefiner;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.email.EmailPropertiesReader;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.service.FormPattern;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.util.DeploymentPropertiesReader;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.util.localization.LocaleKey;
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
		DaoProvider.getInstance();
		EmailPropertiesReader.getPropeties();
        ServiceProvider.getInstance();
        setServletContextVariables(sce.getServletContext());
        LocaleAttribute.LOCALIZATION_EN.getResourceBundle();
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

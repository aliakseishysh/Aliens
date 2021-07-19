package by.shyshaliaksey.webproject.controller.listener;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.PagePath;
import by.shyshaliaksey.webproject.controller.RequestParameter;

import java.util.ResourceBundle;

import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.model.connection.ConnectionPool;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.email.EmailPropertiesReader;
import by.shyshaliaksey.webproject.model.entity.FormPattern;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.localization.LocaleKey;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		ConnectionPool.getInstance();
		DaoProvider.getInstance();
		EmailPropertiesReader.getPropeties();
        ServiceProvider.getInstance();
        setSessionVariables(sce.getServletContext());
        LocaleAttribute.LOCALIZATION_EN.getResourceBundle();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		ConnectionPool.getInstance().destroyConnectionPool();
	}
	
	private void setSessionVariables(ServletContext servletContext) {
		setEnumApplicationVariables(servletContext, Feedback.Key.values());
		setEnumApplicationVariables(servletContext, CommandValue.values());
		setEnumApplicationVariables(servletContext, FilePath.values());
		setEnumApplicationVariables(servletContext, PagePath.values());
		setEnumApplicationVariables(servletContext, RequestParameter.values());
		setEnumApplicationVariables(servletContext, Role.values());
		setEnumApplicationVariables(servletContext, FormPattern.values());
		setEnumApplicationVariables(servletContext, LocaleAttribute.values());
		setEnumApplicationVariables(servletContext, LocaleKey.values());
	}

	private <T extends Enum<?>> void setEnumApplicationVariables(ServletContext  servletContext, T[] enumValues) {
		for(T enumValue: enumValues) {
			String enumName = enumValue.name();
			EnumValue enumValueInterface = (EnumValue) enumValue;
			servletContext.setAttribute(enumName, enumValueInterface.getValue());
		}
	}
	

}

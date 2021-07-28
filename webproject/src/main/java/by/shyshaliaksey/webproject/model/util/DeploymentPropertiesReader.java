package by.shyshaliaksey.webproject.model.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.EnumValue;

/**
 * Class {@code DeploymentPropertiesReader} designed for reading
 * deployment.properties file
 * 
 * @author Aliaksey Shysh
 *
 */
public class DeploymentPropertiesReader {

	private static Logger logger = LogManager.getRootLogger();
	private static final ResourceBundle resourceBundle;

	static {
		try {
			resourceBundle = ResourceBundle.getBundle("\\settings\\deployment");
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, "Can not read properties for deployment: {}", e.getMessage());
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}

	/**
	 * Enum {@code Deployment} designed for storing data from deployment.properties
	 * file
	 * 
	 * @author Aliaksey Shysh
	 *
	 */
	public enum Deployment implements EnumValue {
		WEB_APP_ROOT(resourceBundle.getString("WEB_APP_ROOT")),
		CURRENT_DEPLOYMENT(resourceBundle.getString("CURRENT_DEPLOYMENT")),
		PROJECT_NAME(resourceBundle.getString("PROJECT_NAME"));

		private String value;

		private Deployment(String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}
	}

}

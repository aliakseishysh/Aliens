package by.shyshaliaksey.aliens.model.email;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class designed for reading email properties
 * 
 * @author Aliaksey Shysh
 *
 */
public class EmailPropertiesReader {

	private static Logger logger = LogManager.getRootLogger();
	private static ResourceBundle resourceBundle;
	private static Properties properties;

	private EmailPropertiesReader() {
	}

	static {
		try {
			resourceBundle = ResourceBundle.getBundle("\\email\\email");
			Map<String, String> messages = new HashMap<>();
			Collections.list(resourceBundle.getKeys()).forEach(key -> messages.put(key, resourceBundle.getString(key)));
			properties = new Properties();
			properties.putAll(messages);
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, "MissingResourceException: {} {}", e.getMessage(), e.getStackTrace());
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}

	public static Properties getPropeties() {
		return properties;
	}

}

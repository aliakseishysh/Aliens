package by.shyshaliaksey.webproject.model.connection;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code DatabasePropertiesReader} designed for reading database properties
 * 
 * @author Aliaksey Shysh
 *
 */
class DatabasePropertiesReader {

	private static Logger logger = LogManager.getRootLogger();
	static final String DATABASE_URL;
	static final String DATABASE_TIMEZONE;
	static final String DATABASE_USERNAME;
	static final String DATABASE_PASSWORD;
	static final String DATABASE_DRIVER;
	static final int CONNECTION_POOL_DEFAULT_SIZE;
	
	static {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("\\settings\\database");
			DATABASE_URL = resourceBundle.getString("DATABASE_URL");
			DATABASE_TIMEZONE = resourceBundle.getString("DATABASE_TIMEZONE");
			DATABASE_USERNAME = resourceBundle.getString("DATABASE_USERNAME");
			DATABASE_PASSWORD = resourceBundle.getString("DATABASE_PASSWORD");
			DATABASE_DRIVER = resourceBundle.getString("DATABASE_DRIVER");
			CONNECTION_POOL_DEFAULT_SIZE = Integer.parseInt(resourceBundle.getString("CONNECTION_POOL_DEFAULT_SIZE"));
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, "MissingResourceException: {}", e.getMessage(), e);
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}
	
	private DatabasePropertiesReader() {
	}
	
}

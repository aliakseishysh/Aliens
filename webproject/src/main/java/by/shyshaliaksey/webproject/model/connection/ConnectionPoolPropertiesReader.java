package by.shyshaliaksey.webproject.model.connection;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPoolPropertiesReader {

	private static Logger logger = LogManager.getRootLogger();
	public static final int CONNECTION_POOL_DEFAULT_SIZE;
	
	static {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("\\settings\\connection_pool");
			CONNECTION_POOL_DEFAULT_SIZE = Integer.parseInt(resourceBundle.getString("CONNECTION_POOL_DEFAULT_SIZE"));
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, "MissingResourceException: {}", e.getMessage());
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}
	
	private ConnectionPoolPropertiesReader() {
	}
	
}

package by.shyshaliaksey.webproject.model.connection;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabasePropertiesReader {

	private static Logger logger = LogManager.getRootLogger();
	public static final String DATABASE_URL;
	public static final String DATABASE_TIMEZONE;
	public static final String DATABASE_USERNAME;
	public static final String DATABASE_PASSWORD;
	public static final String DATABASE_DRIVER;
	
	static {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("\\settings\\database");
			DATABASE_URL = resourceBundle.getString("DATABASE_URL");
			DATABASE_TIMEZONE = resourceBundle.getString("DATABASE_TIMEZONE");
			DATABASE_USERNAME = resourceBundle.getString("DATABASE_USERNAME");
			DATABASE_PASSWORD = resourceBundle.getString("DATABASE_PASSWORD");
			DATABASE_DRIVER = resourceBundle.getString("DATABASE_DRIVER");
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, "MissingResourceException: {}", e.getMessage());
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}
	
	private DatabasePropertiesReader() {
	}
	
}

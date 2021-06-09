package by.shyshaliaksey.webproject.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {

	private static Logger logger = LogManager.getRootLogger();
	
	static {
		String driverName = DatabasePropertiesReader.DATABASE_DRIVER;
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			logger.log(Level.FATAL, "Can not register database driver {}: {}", driverName, e.getMessage());
			throw new RuntimeException("Can not register database driver " + driverName + ": ", e);
		}
	}
	
	private ConnectionFactory() {
	}
	
	static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(
				DatabasePropertiesReader.DATABASE_URL + DatabasePropertiesReader.DATABASE_TIMEZONE, 
				DatabasePropertiesReader.DATABASE_USERNAME, 
				DatabasePropertiesReader.DATABASE_PASSWORD);
	}
	
	
}

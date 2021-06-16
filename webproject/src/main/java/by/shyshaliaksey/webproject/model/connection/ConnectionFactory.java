package by.shyshaliaksey.webproject.model.connection;

import static by.shyshaliaksey.webproject.model.connection.DatabasePropertiesReader.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConnectionFactory {

	private static Logger logger = LogManager.getRootLogger();

	static {
		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.log(Level.FATAL, "Can not register database driver {}: {}", DATABASE_DRIVER, e.getMessage());
			throw new IllegalArgumentException("Can not register database driver " + DATABASE_DRIVER + ": ", e);
		}
	}

	private ConnectionFactory() {
	}

	static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE_URL + DATABASE_TIMEZONE, DATABASE_USERNAME, DATABASE_PASSWORD);
	}

}

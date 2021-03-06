package by.shyshaliaksey.aliens.model.connection;

import static by.shyshaliaksey.aliens.model.connection.DatabasePropertiesReader.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConnectionFactory {

	private static final Logger logger = LogManager.getRootLogger();

	/*
	 * Static block for database driver registration
	 */
	static {
		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.log(Level.FATAL, "Can not register database driver {}: {} {}", DATABASE_DRIVER, e.getMessage(),
					e.getStackTrace());
			throw new ExceptionInInitializerError("Can not register database driver: " + DATABASE_DRIVER);
		}
	}

	private ConnectionFactory() {
	}

	/**
	 * 
	 * @return new {@link Connection} with provided data
	 * @throws SQLException if can not create connection
	 */
	static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE_URL + DATABASE_TIMEZONE, DATABASE_USERNAME, DATABASE_PASSWORD);
	}

}

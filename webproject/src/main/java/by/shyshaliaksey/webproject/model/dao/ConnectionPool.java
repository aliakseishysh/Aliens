package by.shyshaliaksey.webproject.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {

	private static ConnectionPool instance;
	private static final Logger logger = LogManager.getRootLogger();
	private static final AtomicBoolean isConnectionPoolCreated = new AtomicBoolean(false);
	private BlockingDeque<Connection> freeConnections;
	private BlockingDeque<Connection> occupiedConnections;
	
	private static final String DATABASE_URL;
	private static final String DATABASE_TIMEZONE;
	private static final String DATABASE_USERNAME;
	private static final String DATABASE_PASSWORD;
	
	static {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("\\settings\\database");
		DATABASE_URL = resourceBundle.getString("DATABASE_URL");
		DATABASE_TIMEZONE = resourceBundle.getString("DATABASE_TIMEZONE");
		DATABASE_USERNAME = resourceBundle.getString("DATABASE_USERNAME");
		DATABASE_PASSWORD = resourceBundle.getString("DATABASE_PASSWORD");
	}
	
	
	private ConnectionPool() {
		occupiedConnections = new LinkedBlockingDeque<>();
		freeConnections = new LinkedBlockingDeque<>();
		Connection connection1;
		Connection connection2;
		try {
			connection1 = DriverManager.getConnection(DATABASE_URL + DATABASE_TIMEZONE, DATABASE_USERNAME, DATABASE_PASSWORD);
			connection2 = DriverManager.getConnection(DATABASE_URL + DATABASE_TIMEZONE, DATABASE_USERNAME, DATABASE_PASSWORD);
			freeConnections.push(connection1);
			freeConnections.push(connection2);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ConnectionPool creation error: {}", e.getMessage());
		}
	}
	
	public static ConnectionPool getInstance() {
		while (instance == null) {
			if (isConnectionPoolCreated.compareAndSet(false, true)) {
				instance = new ConnectionPool();
			}
		}
		return instance;
	}
	
	public Connection getFreeConnection() {
		Connection connection = freeConnections.poll();
		occupiedConnections.push(connection);
		return connection;
	}
	
	public void releaseConnection(Connection connection) {
		occupiedConnections.remove(connection);
		freeConnections.push(connection);
	}
	
	public void releaseConnectionPool() {
		while (!freeConnections.isEmpty()) {
			Connection connection = freeConnections.poll();
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (!occupiedConnections.isEmpty()) {
			Connection connection = occupiedConnections.poll();
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}

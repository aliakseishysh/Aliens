package by.shyshaliaksey.webproject.model.connection;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {

	private static final Logger logger = LogManager.getRootLogger();
	private static ConnectionPool instance;
	private static final AtomicBoolean isConnectionPoolCreated = new AtomicBoolean(false);
	private BlockingDeque<ConnectionProxy> freeConnections;
	private BlockingDeque<ConnectionProxy> occupiedConnections;
	
	private ConnectionPool() {
		occupiedConnections = new LinkedBlockingDeque<>();
		freeConnections = new LinkedBlockingDeque<>();
		for (int i = 0; i < ConnectionPoolPropertiesReader.CONNECTION_POOL_DEFAULT_SIZE; i++) {
			try {
				Connection connection = ConnectionFactory.createConnection();
				ConnectionProxy connectionProxy = new ConnectionProxy(connection);
				freeConnections.put(connectionProxy);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Connection creation error: {}", e.getMessage());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		if (freeConnections.isEmpty()) {
			logger.log(Level.FATAL, "Can not create ConnectionPool: empty");
			throw new RuntimeException("Can not create ConnectionPool:");
		} else if (freeConnections.size() == ConnectionPoolPropertiesReader.CONNECTION_POOL_DEFAULT_SIZE) {
			logger.log(Level.INFO, "ConnectionPool successfully created");
		} else if (freeConnections.size() < ConnectionPoolPropertiesReader.CONNECTION_POOL_DEFAULT_SIZE) {
			logger.log(Level.WARN, "ConnectionPool successfully created, default size: {}, current size: {}",
					ConnectionPoolPropertiesReader.CONNECTION_POOL_DEFAULT_SIZE,
					freeConnections.size());
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
	
	// TODO what to do with interrupt? call getFreeConnection again from catch? use optional? or throw new InterruptedException?
	public Connection getFreeConnection() {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			occupiedConnections.put((ConnectionProxy) connection);
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return connection;
	}
	
	public void releaseConnection(Connection connection) {
		if (!(connection instanceof ConnectionProxy)) {
			logger.log(Level.ERROR, "Unboxed connection is detected: {}", connection);
			// TODO need to throw exception or just close
		}
		occupiedConnections.removeFirstOccurrence(connection);
		try {
			freeConnections.put((ConnectionProxy) connection);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void destroyConnectionPool() {
		while (!freeConnections.isEmpty()) {
			try {
				freeConnections.take().reallyClose();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Connection is not deleted: {}", e.getMessage());
			} catch (InterruptedException e) {
				logger.log(Level.WARN, "Connection is not deleted: {}", e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
		while (!occupiedConnections.isEmpty()) {
			try {
				ConnectionProxy connection = occupiedConnections.take();
				connection.rollback();
				connection.reallyClose();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Connection is not deleted: {}", e.getMessage());
			} catch (InterruptedException e) {
				logger.log(Level.WARN, "Connection is not deleted: {}", e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
		deregisterDrivers();
	}
	
	private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error occured while deregistering driver {}: {}", driver, e.getMessage());
            }
        }
    }
	
	
}

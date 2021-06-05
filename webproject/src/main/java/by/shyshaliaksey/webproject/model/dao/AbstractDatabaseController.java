package by.shyshaliaksey.webproject.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public abstract class AbstractDatabaseController<T> {

	private Connection connection;
	private ConnectionPool connectionPool;
	
	protected AbstractDatabaseController() {
		connectionPool = ConnectionPool.getInstance();
		connection = connectionPool.getFreeConnection();
	}
	
	public abstract List<T> getAll();
	
	
	public void releaseConnection() {
		connectionPool.releaseConnection(connection);
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO handle exception
		}
		return preparedStatement;
	}
	
	public void closePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO handle exception
			}
		}
	}
	
	
}

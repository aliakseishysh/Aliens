package by.shyshaliaksey.webproject.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Role;



public abstract class AbstractDatabaseController {

	private Connection connection;
	private ConnectionPool connectionPool;
	
	protected AbstractDatabaseController() {
		connectionPool = ConnectionPool.getInstance();
		connection = connectionPool.getFreeConnection();
	}
	
	public abstract List<Alien> getAllAliens();
	public abstract int registerUser(String email, String login, String password, String imagePath, Role role);
	public abstract boolean loginUser(String email, String password);
	public abstract String getLoginByEmail(String email);
	public abstract AbstractUser getUserInfoByLogin(String login);
	public abstract Alien getAlienInfoById(int id);
	
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

package by.shyshaliaksey.aliens.model.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConnectionPoolTest {
	
	@BeforeClass
	public void init() {
		ConnectionPool.getInstance();
	}
	
	@Test
	public void releaseNullTest() {
		boolean actual = ConnectionPool.getInstance().releaseConnection(null);
		Assert.assertFalse(actual);
	}
	
	@Test
	public void releaseExternalSqlConnectionTest() {
		try (Connection connection = ConnectionFactory.createConnection()){
			boolean actual = ConnectionPool.getInstance().releaseConnection(connection);
			Assert.assertFalse(actual);
		} catch (SQLException e) {
			Assert.fail("Can not create connection", e.getCause());
		}
	}
	
	@Test
	public void releaseExternalConnectionProxyTest() throws SQLException {
		ConnectionProxy connection = null;
		try {
			connection = new ConnectionProxy(ConnectionFactory.createConnection());
			boolean actual = ConnectionPool.getInstance().releaseConnection(connection);
			Assert.assertFalse(actual);
		} catch (SQLException e) {
			Assert.fail("Can not create connection", e.getCause());
		} finally {
			if (connection != null) {
				connection.reallyClose();				
			}
		}
	}
	
	
	@AfterClass
	public void destroy() {
		ConnectionPool.getInstance().destroyConnectionPool();
	}
	
}

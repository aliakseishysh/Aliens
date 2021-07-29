package by.shyshaliaksey.webproject.model.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConnectionFactoryTest {

	@Test
	public void createConnectionTest() {
		try (Connection connection = ConnectionFactory.createConnection()) {
			Assert.assertNotNull(connection);
		} catch (SQLException e) {
			Assert.fail("Can not create connection", e.getCause());
		}
	}

}

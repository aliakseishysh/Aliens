package by.shyshaliaksey.webproject.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.shyshaliaksey.webproject.model.entity.Alien;

public class MysqlController extends AbstractDatabaseController<Alien> {

	/**
	 * 
	 */
	private static final String SELECT_ALL_ALIANS = "SELECT * FROM aliens_web_project.aliens";
	
	@Override
	public List<Alien> getAll() {
		List<Alien> databaseData = new ArrayList<>();
		PreparedStatement preparedStatement = super.getPreparedStatement(SELECT_ALL_ALIANS);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Alien alien = new Alien();
				alien.setId(resultSet.getInt(1));
				alien.setName(resultSet.getString(2));
				databaseData.add(alien);
			}
		} catch (SQLException e) {
			// TODO Handle exception
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		return databaseData;
	}

}

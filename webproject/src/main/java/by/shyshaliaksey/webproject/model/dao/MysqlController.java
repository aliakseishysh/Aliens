package by.shyshaliaksey.webproject.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.UserFactory;

public class MysqlController extends AbstractDatabaseController {

	/**
	 * 
	 */
	private static final String SELECT_ALL_ALIENS = "SELECT * FROM aliens_web_project.Aliens";
	private static final String REGISTER_USER = "INSERT INTO aliens_web_project.users (email, login_name, password_hash, image_url, role_id) VALUES (?, ?, ?, ?, ?)";
	private static final String LOGIN_USER = "SELECT count(*) as usersCount FROM aliens_web_project.users WHERE email=? AND password_hash=?";
	private static final String GET_LOGIN_BY_EMAIL = "SELECT login_name FROM aliens_web_project.users WHERE email=?";
	private static final String GET_USER_INFO_BY_LOGIN = "SELECT users.email, users.image_url, roles._name FROM users INNER JOIN roles ON users.role_id = roles.role_id WHERE users.login_name=?";
	private static final String GET_ALIEN_INFO_BY_ID = "SELECT * FROM aliens WHERE alien_id=?";
	
	@Override
	public List<Alien> getAllAliens() {
		List<Alien> databaseData = new ArrayList<>();
		PreparedStatement preparedStatement = super.getPreparedStatement(SELECT_ALL_ALIENS);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Alien alien = new Alien(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5)
						);
				databaseData.add(alien);
			}
		} catch (SQLException e) {
			// TODO Handle exception
			e.printStackTrace();
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		return databaseData;
	}

	@Override
	public int registerUser(String email, String login, String password, String imagePath, Role role) {
		PreparedStatement preparedStatement = super.getPreparedStatement(REGISTER_USER);
		int rowsAdded = 0;
		try {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, login);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, imagePath);
			preparedStatement.setInt(5, role.ordinal() + 1);
			rowsAdded = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		return rowsAdded;
	}

	@Override
	public boolean loginUser(String email, String password) {
		PreparedStatement preparedStatement = super.getPreparedStatement(LOGIN_USER);
		int usersCount = 0;
		try {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				usersCount = resultSet.getInt("usersCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		boolean loggingResult = usersCount == 1;
		return loggingResult;
	}
	
	

	@Override
	public String getLoginByEmail(String email) {
		PreparedStatement preparedStatement = super.getPreparedStatement(GET_LOGIN_BY_EMAIL);
		String login = "";
		try {
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				login = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		return login;
	}

	@Override
	public AbstractUser getUserInfoByLogin(String login) {
		PreparedStatement preparedStatement = super.getPreparedStatement(GET_USER_INFO_BY_LOGIN);
		AbstractUser abstractUser = null;
		try {
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String email = resultSet.getString(1);
				String imageUrl = resultSet.getString(2);
				Role role = Role.valueOf(resultSet.getString(3).toUpperCase());
				abstractUser = UserFactory.getInstance(email, login, imageUrl, role);
			}
		} catch (SQLException e) {
			// TODO Handle exception
			e.printStackTrace();
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		return abstractUser;
	}

	@Override
	public Alien getAlienInfoById(int alienId) {
		PreparedStatement preparedStatement = super.getPreparedStatement(GET_ALIEN_INFO_BY_ID);
		Alien alien = null;
		try {
			preparedStatement.setInt(1, alienId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(2);
				String smallDescription = resultSet.getString(3);
				String bigDescription = resultSet.getString(4);
				String imageUrl = resultSet.getString(5);
				alien = new Alien(
						alienId, 
						name, 
						smallDescription, 
						bigDescription, 
						imageUrl
						);
			}
		} catch (SQLException e) {
			// TODO Handle exception
			e.printStackTrace();
		} finally {
			super.closePreparedStatement(preparedStatement);
			super.releaseConnection();
		}
		return alien;
	}

}

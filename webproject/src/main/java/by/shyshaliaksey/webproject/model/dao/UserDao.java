package by.shyshaliaksey.webproject.model.dao;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.Role;

public interface UserDao {

	List<User> findAll() throws DaoException;
	Optional<User> findById(int userId) throws DaoException;
	Optional<User> findByLogin(String userLogin) throws DaoException;
	Optional<User> findByEmail(String userEmail) throws DaoException;
	boolean registerUser(String email, String login, String passwordHash, String imagePath, Role role) throws DaoException;
	boolean loginUser(String email, String passwordHash) throws DaoException;
	boolean updateUserEmail(String email, int userId) throws DaoException;
	boolean updateUserLogin(String login, int userId) throws DaoException;
	boolean updateUserPassword(String hashedPassword, int userId) throws DaoException;
	boolean updateProfileImage(String newFileName, int userId) throws DaoException;
	boolean banUser(String userLogin, String banDate) throws DaoException;
	boolean unbanUser(String userLogin, String currentDate) throws DaoException;
	
}

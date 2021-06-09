package by.shyshaliaksey.webproject.model.dao;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Role;

public interface UserDao {

	List<AbstractUser> findAll() throws DaoException;
	Optional<AbstractUser> findById(int userId) throws DaoException;
	Optional<AbstractUser> findByLogin(String userLogin) throws DaoException;
	Optional<AbstractUser> findByEmail(String userEmail) throws DaoException;
	boolean registerUser(String email, String login, String passwordHash, String imagePath, Role role) throws DaoException;
	boolean loginUser(String email, String passwordHash) throws DaoException;
	
}

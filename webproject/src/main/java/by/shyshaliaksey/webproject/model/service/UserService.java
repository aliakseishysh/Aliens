package by.shyshaliaksey.webproject.model.service;


import java.util.Optional;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import jakarta.servlet.http.Part;

public interface UserService {

	boolean userLogIn(String email, String password) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	boolean registerUser(String email, String login, String passwordHash, String imagePath, Role role) throws ServiceException;
	Optional<User> findByLogin(String login) throws ServiceException;
	boolean changeEmail(String email, String newEmail, int userId) throws ServiceException;
	boolean changeLogin(String login, String newLogin, int userId) throws ServiceException;
	boolean changePassword(String password, String passwordConfirm, int userId) throws ServiceException;
	boolean updateImage(String serverDeploymentPath, String rootFolder, Part part, int userId) throws ServiceException;
}

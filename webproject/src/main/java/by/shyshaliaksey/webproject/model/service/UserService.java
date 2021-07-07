package by.shyshaliaksey.webproject.model.service;


import java.util.Optional;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.EmailUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.ImageUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.LoginUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.PasswordUpdateResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.RegisterResultInfo;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public interface UserService {

	LoginResultInfo userLogIn(String email, String password) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	RegisterResultInfo registerUser(String email, String login, String password, String passwordRepeat, String imagePath, Role role) throws ServiceException;
	Optional<User> findByLogin(String login) throws ServiceException;
	EmailUpdateResultInfo changeEmail(String email, String newEmail, int userId) throws ServiceException;
	LoginUpdateResultInfo changeLogin(String login, String newLogin, int userId) throws ServiceException;
	PasswordUpdateResultInfo changePassword(String password, String passwordConfirm, int userId) throws ServiceException;
	ImageUpdateResultInfo updateImage(String serverDeploymentPath, String rootFolder, Part part, int userId) throws ServiceException;
	boolean isUserBanned(HttpSession session);
	boolean addNewComment(int userId, int alienId, String newComment) throws ServiceException;
	boolean deleteComment(int commentId) throws ServiceException;
	
}

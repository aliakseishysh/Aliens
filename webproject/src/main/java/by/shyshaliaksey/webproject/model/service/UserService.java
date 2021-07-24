package by.shyshaliaksey.webproject.model.service;


import java.util.Map;
import java.util.Optional;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.controller.command.Feedback.Key;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public interface UserService {

	Map<Feedback.Key, Object> userLogIn(String email, String password) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	Map<Feedback.Key, Object> registerUser(String email, String login, String password, String passwordRepeat, String imagePath, Role role) throws ServiceException;
	Optional<User> findByLogin(String login) throws ServiceException;
	Map<Feedback.Key, Object> changeEmail(String email, String newEmail, int userId) throws ServiceException;
	Map<Feedback.Key, Object> changeLogin(String login, String newLogin, int userId) throws ServiceException;
	Map<Feedback.Key, Object> changePassword(String password, String passwordConfirm, int userId) throws ServiceException;
	boolean isUserBanned(HttpSession session);
	Map<Feedback.Key, Object> addNewComment(int userId, int alienId, String newComment) throws ServiceException;
	boolean deleteComment(int commentId) throws ServiceException;
	Map<Key, Object> suggestNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException;
	Map<Key, Object> suggestNewAlienImage(String alienName, Part alienImage, String rootFolder,
			String serverDeploymentPath) throws ServiceException;
	Map<Key, Object> updateImage(String serverDeploymentPath, String rootFolder, Part userImage, int userId, String webSiteName)
			throws ServiceException;
	
}

package by.shyshaliaksey.webproject.model.dao;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.User.Role;
import by.shyshaliaksey.webproject.model.dao.DatabaseFeedback.Key;
import by.shyshaliaksey.webproject.model.entity.Token;
import by.shyshaliaksey.webproject.model.entity.Token.Status;

public interface UserDao {

	List<User> findAll() throws DaoException;
	Optional<User> findById(int userId) throws DaoException;
	Optional<User> findByLogin(String userLogin) throws DaoException;
	Optional<User> findByEmail(String userEmail) throws DaoException;
	Map<Key, Optional<String>> findUserLoginData(String userEmail) throws DaoException;
	Map<Key, Optional<String>> findUserLoginData(int userId) throws DaoException;
	boolean registerUser(String email, String login, String hashedPasswordHash, String saltHash, String defaultImage, Role defaultRole) throws DaoException;
	boolean loginUser(String email, String passwordHash) throws DaoException;
	boolean updateUserEmail(String email, int userId) throws DaoException;
	boolean updateUserLogin(String login, int userId) throws DaoException;
	boolean updateUserPassword(String hashedPassword, int userId) throws DaoException;
	boolean updateProfileImage(String newFileName, int userId) throws DaoException;
	boolean banUser(String userLogin, String banDate) throws DaoException;
	boolean unbanUser(String userLogin, String currentDate) throws DaoException;
	boolean promoteUser(String userLogin) throws DaoException;
	boolean demoteAdmin(String adminLogin) throws DaoException;
	boolean addNewComment(int userId, int alienId, String newComment) throws DaoException;
	boolean deleteComment(int commentId) throws DaoException;
	boolean addNewToken(String email, String token, String expirationDate) throws DaoException;
	boolean changeUserStatus(String email) throws DaoException;
	boolean deleteComment(int commentId, int userId) throws DaoException;
	Optional<Token> findToken(String tokenRequestedContent, Status status) throws DaoException;
	/**
	 * Method for adding new token for user email updating
	 * @param email
	 * @param token
	 * @param expirationDate
	 * @param newEmail
	 * @return
	 * @throws DaoException
	 */
	boolean addNewToken(String email, String token, String expirationDate, String newEmail) throws DaoException;
	boolean updateUserEmail(String email, String newEmail) throws DaoException;
	boolean setTokenStatusExpired(String tokenRequestedContent) throws DaoException;
	
}

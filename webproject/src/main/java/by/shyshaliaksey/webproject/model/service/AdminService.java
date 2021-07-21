package by.shyshaliaksey.webproject.model.service;

import java.util.Map;

import by.shyshaliaksey.webproject.controller.command.Feedback;
import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface AdminService {

	Map<Feedback.Key, Object> banUser(String userLogin, String daysToBan) throws ServiceException;

	Map<Feedback.Key, Object> unbanUser(String userLogin) throws ServiceException;

	Map<Feedback.Key, Object> promoteUser(String userLogin, String currentUserLogin) throws ServiceException;

	Map<Feedback.Key, Object> demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException;

	Map<Feedback.Key, Object> addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException;

	Map<Feedback.Key, Object> updateAlienInfo(int alienId, String alienName, String alienSmallDescription,
			String alienFullDescription) throws ServiceException;

	Map<Feedback.Key, Object> updateAlienImage(int alienId, Part alienImage, String rootFolder,
			String serverDeploymentPath) throws ServiceException;

	boolean approveAlien(String alienId) throws ServiceException;

	boolean declineAlien(String alienId) throws ServiceException;

	boolean approveAlienImage(String alienId) throws ServiceException;

	boolean declineAlienImage(String alienImageUrl) throws ServiceException;
}

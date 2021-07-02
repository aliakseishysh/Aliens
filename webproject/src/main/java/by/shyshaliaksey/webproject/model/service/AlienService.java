package by.shyshaliaksey.webproject.model.service;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Comment;
import jakarta.servlet.http.Part;

public interface AlienService {

	int findAlienId(String alienName) throws ServiceException;
	List<Alien> findAllAliens(int pageNumber) throws ServiceException;
	Optional<Alien> findAlienById(int alienId) throws ServiceException;
	boolean addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription, Part alienImage,
			String rootFolder, String serverDeploymentPath) throws ServiceException;
	boolean updateAlien(int alienId, String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException;
	List<Comment> findAllComments(int alienId) throws ServiceException;
	int findAlienCount() throws ServiceException;
	int findAlienCommentsCount(int alienId) throws ServiceException;
	List<Comment> findAllCommentsInPage(int alienId, int page) throws ServiceException;
	
}

package by.shyshaliaksey.webproject.model.service;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Alien.Status;
import by.shyshaliaksey.webproject.model.entity.Comment;
import jakarta.servlet.http.Part;

public interface AlienService {

	int findAlienId(String alienName) throws ServiceException;
	List<Alien> findNormalAliens(int pageNumber) throws ServiceException;
	Optional<Alien> findAlienById(int alienId) throws ServiceException;
	List<Comment> findAllComments(int alienId) throws ServiceException;
	int findAlienCount() throws ServiceException;
	int findAlienCommentsCount(int alienId) throws ServiceException;
	List<Comment> findAllCommentsInPage(int alienId, int page) throws ServiceException;
	List<String> findImages(int alienId) throws ServiceException;
	List<Alien> findUnapprovedAliens(int pageNumber) throws ServiceException;
	int findUnapprovedAlienCount() throws ServiceException;
	int findUnapprovedAliensImagesCount() throws ServiceException;
	List<Alien> findUnapprovedAliensImages(int page) throws ServiceException;
	Optional<Alien> findAlienByIdAndStatus(int alienId, Status status) throws ServiceException;
	
}

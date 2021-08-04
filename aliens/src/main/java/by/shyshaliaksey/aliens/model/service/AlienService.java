package by.shyshaliaksey.aliens.model.service;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.entity.Alien;
import by.shyshaliaksey.aliens.model.entity.Comment;

public interface AlienService {

	/**
	 * Finds alien by name.
	 * 
	 * @param alienName {@code String} alien name obtained from request
	 * @return {@code int} alien id, otherwise throws ServiceException
	 * @throws ServiceException
	 */
	int findAlienId(String alienName) throws ServiceException;

	/**
	 * Finds all aliens with {@link Alien.Status#NORMAL} status in specified page.
	 * 
	 * @param pageNumber {@code int} page to search aliens at
	 * @return {@code List<Alien>} with aliens at page
	 * @throws ServiceException
	 */
	List<Alien> findNormalAliens(int pageNumber) throws ServiceException;

	/**
	 * Finds aliens count.
	 * 
	 * @return {@code int} alien number
	 * @throws ServiceException
	 */
	int findAlienCount() throws ServiceException;

	/**
	 * Finds comment count for alien with specified id.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @return {@code int} alien comments number
	 * @throws ServiceException
	 */
	int findAlienCommentsCount(int alienId) throws ServiceException;

	/**
	 * Finds comments number for specified alien and page.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @param page    {@code int} requested page obtained from request
	 * @return {@code List<Comment>} comments in requested page
	 * @throws ServiceException
	 */
	List<Comment> findAllCommentsInPage(int alienId, int page) throws ServiceException;

	/**
	 * Finds images of specified alien by id.
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @return {@code List<String>} alien images urls
	 * @throws ServiceException
	 */
	List<String> findImages(int alienId) throws ServiceException;

	/**
	 * Finds aliens with {@link Alien.Status#UNDER_CONSIDERATION} status.
	 * 
	 * @param pageNumber {@code int} requested page obtained from request
	 * @return {@code List<Alien>} unapproved aliens in requested page
	 * @throws ServiceException
	 */
	List<Alien> findUnapprovedAliens(int pageNumber) throws ServiceException;

	/**
	 * Finds number of aliens with {@link Alien.Status#UNDER_CONSIDERATION} status.
	 * 
	 * @return {@code int} number of aliens
	 * @throws ServiceException
	 */
	int findUnapprovedAlienCount() throws ServiceException;

	/**
	 * Finds number of aliens images with {@link Alien.Status#UNDER_CONSIDERATION}
	 * status.
	 * 
	 * @return {@code int} number of aliens images
	 * @throws ServiceException
	 */
	int findUnapprovedAliensImagesCount() throws ServiceException;

	/**
	 * Finds aliens images urls with {@link Alien.Status#UNDER_CONSIDERATION} status
	 * at specified page.
	 * 
	 * @param page {@code int} requested page obtained from request
	 * @return {@code List<Alien>} aliens with ids, names, urls.
	 * @throws ServiceException
	 */
	List<Alien> findUnapprovedAliensImages(int page) throws ServiceException;

	/**
	 * Finds alien by specified id and {@link Alien.Status}
	 * 
	 * @param alienId {@code int} alien id obtained from request
	 * @param status  {@code Alien.Status} alien status
	 * @return {@code Optional<Alien>} alien from database
	 * @throws ServiceException
	 */
	Optional<Alien> findAlienByIdAndStatus(int alienId, Alien.Status status) throws ServiceException;

}

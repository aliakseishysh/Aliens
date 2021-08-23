package by.shyshaliaksey.aliens.model.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.aliens.controller.PaginationConfiguration;
import by.shyshaliaksey.aliens.exception.DaoException;
import by.shyshaliaksey.aliens.exception.ServiceException;
import by.shyshaliaksey.aliens.model.dao.AlienDao;
import by.shyshaliaksey.aliens.model.dao.DaoProvider;
import by.shyshaliaksey.aliens.model.entity.Alien;
import by.shyshaliaksey.aliens.model.entity.Comment;
import by.shyshaliaksey.aliens.model.service.AlienService;

/**
 * Implementer of {@link AlienService} designed for communication between
 * controller and model layer for actions related to alien.
 * 
 * @author Aliaksey Shysh
 *
 */
public class AlienServiceImpl implements AlienService {

	private static final AlienDao alienDao = DaoProvider.getInstance().getAlienDao();

	@Override
	public int findAlienId(String alienName) throws ServiceException {
		try {
			Optional<Alien> alienOptional = alienDao.findByName(alienName);
			if (alienOptional.isPresent()) {
				Alien alien = alienOptional.get();
				return alien.getId();
			} else {
				throw new ServiceException("Can not find alien by name: " + alienName);
			}
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while looking for alien id by name: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Alien> findNormalAliens(int pageNumber) throws ServiceException {
		try {
			int aliensPerPageLimit = PaginationConfiguration.HOME_ALIENS_PER_PAGE;
			int fromRecord = aliensPerPageLimit * (pageNumber - 1);
			return alienDao.findAll(fromRecord, aliensPerPageLimit);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while looking for aliens: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Alien> findUnapprovedAliens(int pageNumber) throws ServiceException {
		try {
			int aliensPerPageLimit = PaginationConfiguration.ADMIN_SUGGESTED_ALIENS_PER_PAGE;
			int fromRecord = aliensPerPageLimit * (pageNumber - 1);
			return alienDao.findAllUnapprovedAliens(fromRecord, aliensPerPageLimit);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while looking for aliens: " + e.getMessage(), e);
		}
	}

	@Override
	public Optional<Alien> findAlienByIdAndStatus(int alienId, Alien.Status status) throws ServiceException {
		try {
			return alienDao.findByIdAndStatus(alienId, status);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while finding alien by id and status: " + "Alien ID: " + alienId
					+ ", " + "Alien Status: " + status.name() + ", " + Arrays.toString(e.getStackTrace()), e);
		}
	}

	@Override
	public int findAlienCount() throws ServiceException {
		try {
			return alienDao.findAlienCount();
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while finding alien count: " + e.getMessage() + Arrays.toString(e.getStackTrace()),
					e);
		}
	}

	@Override
	public int findUnapprovedAlienCount() throws ServiceException {
		try {
			return alienDao.findUnapprovedAlienCount();
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while finding alien count: " + e.getMessage() + Arrays.toString(e.getStackTrace()),
					e);
		}
	}

	@Override
	public int findAlienCommentsCount(int alienId) throws ServiceException {
		try {
			return alienDao.findAlienCommentsCount(alienId);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while finding comments: " + e.getMessage() + Arrays.toString(e.getStackTrace()),
					e);
		}
	}

	@Override
	public List<Comment> findAllCommentsInPage(int alienId, int page) throws ServiceException {
		try {
			int aliensPerPageLimit = PaginationConfiguration.ALIEN_PROFILE_COMMENTS_PER_PAGE;
			int fromRecord = aliensPerPageLimit * (page - 1);
			return alienDao.findComments(alienId, fromRecord, aliensPerPageLimit);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while finding comments: " + e.getMessage() + Arrays.toString(e.getStackTrace()),
					e);
		}
	}

	@Override
	public List<String> findImages(int alienId) throws ServiceException {
		try {
			return alienDao.findImages(alienId);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while finding images: " + e.getMessage() + Arrays.toString(e.getStackTrace()), e);
		}
	}

	@Override
	public int findUnapprovedAliensImagesCount() throws ServiceException {
		try {
			return alienDao.findUnapprovedAliensImagesCount();
		} catch (DaoException e) {
			throw new ServiceException(
					"Error occurred while finding alien images count: " + e.getMessage() + Arrays.toString(e.getStackTrace()), e);
		}
	}

	@Override
	public List<Alien> findUnapprovedAliensImages(int page) throws ServiceException {
		try {
			int imagesPerPageLimit = PaginationConfiguration.ADMIN_SUGGESTED_ALIENS_IMAGES_PER_PAGE;
			int fromRecord = imagesPerPageLimit * (page - 1);
			return alienDao.findUnapprovedAliensImages(fromRecord, imagesPerPageLimit);
		} catch (DaoException e) {
			throw new ServiceException("Error occurred while looking for aliens: " + e.getMessage(), e);
		}
	}

}

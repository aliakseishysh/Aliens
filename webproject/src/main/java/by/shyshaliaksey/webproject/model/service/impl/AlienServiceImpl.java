package by.shyshaliaksey.webproject.model.service.impl;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.service.AlienService;

public class AlienServiceImpl implements AlienService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final AlienDao alienDao = daoProvider.getAlienDao();

	@Override
	public int findAlienId(String alienName) throws ServiceException {
		try {
			Optional<Alien> alienOptional = alienDao.findByName(alienName);
			if (alienOptional.isPresent()) {
				Alien alien = alienOptional.get();
				int alienId = alien.getId();
				return alienId;
			} else {
				throw new ServiceException("Can not find alien by name: " + alienName);
			}
		} catch (DaoException e) {
			throw new ServiceException("Error occured while looking for alien id by name: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Alien> findAllAliens() throws ServiceException {
		try {
			List<Alien> aliens = alienDao.findAll();
			return aliens;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while looking for aliens: " + e.getMessage(), e);
		}
	}

	@Override
	public Optional<Alien> findAlienById(int alienId) throws ServiceException {
		 try {
			Optional<Alien> alien = alienDao.findById(alienId);
			return alien;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while finding alien by id: " + alienId + " :" + e.getMessage(), e);
		}
	}

}

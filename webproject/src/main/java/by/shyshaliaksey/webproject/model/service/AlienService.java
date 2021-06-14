package by.shyshaliaksey.webproject.model.service;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.Alien;

public interface AlienService {

	int findAlienId(String alienName) throws ServiceException;
	List<Alien> findAllAliens() throws ServiceException;
	Optional<Alien> findAlienById(int alienId) throws ServiceException;
	
}

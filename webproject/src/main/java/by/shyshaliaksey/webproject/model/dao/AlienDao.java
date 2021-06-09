package by.shyshaliaksey.webproject.model.dao;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.entity.Alien;

public interface AlienDao {

	List<Alien> findAll() throws DaoException;
	Optional<Alien> findById(int userId) throws DaoException;
	Optional<Alien> findByName(String alienName) throws DaoException;
	boolean addAlien(Alien alien) throws DaoException;
	boolean removeAlien(int alienId) throws DaoException;
	
}

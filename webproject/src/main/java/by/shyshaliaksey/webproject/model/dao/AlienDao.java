package by.shyshaliaksey.webproject.model.dao;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Comment;

public interface AlienDao {

	List<Alien> findAll(int fromRecord, int aliensPerPageLimit) throws DaoException;
	Optional<Alien> findById(int userId) throws DaoException;
	Optional<Alien> findByName(String alienName) throws DaoException;
	boolean addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription, String imageUrl) throws DaoException;
	// boolean removeAlien(int alienId) throws DaoException;
	boolean updateAlien(int alienId, String alienName, String alienSmallDescription, String alienFullDescription,
			String imageUrl) throws DaoException;
	List<Comment> findAllComments(int alienId) throws DaoException;
	int findAlienCount() throws DaoException;
	int findAlienCommentsCount(int alienId) throws DaoException;
	List<Comment> findComments(int alienId, int fromRecord, int aliensPerPageLimit) throws DaoException;
	
}

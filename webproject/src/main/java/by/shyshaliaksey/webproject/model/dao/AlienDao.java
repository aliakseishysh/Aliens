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
	int addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription, String imageUrl) throws DaoException;
	// boolean removeAlien(int alienId) throws DaoException;
	boolean updateAlien(int alienId, String alienName, String alienSmallDescription, String alienFullDescription,
			String imageUrl) throws DaoException;
	List<Comment> findAllComments(int alienId) throws DaoException;
	int findAlienCount() throws DaoException;
	int findAlienCommentsCount(int alienId) throws DaoException;
	List<Comment> findComments(int alienId, int fromRecord, int aliensPerPageLimit) throws DaoException;
	List<String> findImages(int alienId) throws DaoException;
	int suggestNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
			String imageUrl) throws DaoException;
	boolean suggestNewAlienImage(int alienImage, String imageUrl) throws DaoException;
	boolean addNewImageToGalery(int alienImage, String imageUrl) throws DaoException;
	List<Alien> findAllUnapproved(int fromRecord, int aliensPerPageLimit) throws DaoException;
	int findUnapprovedAlienCount() throws DaoException;
	int findUnapprovedAliensImagesCount() throws DaoException;
	List<Alien> findUnapprovedAliensImages(int fromRecord, int imagesPerPageLimit) throws DaoException;
	boolean approveAlien(int alienId) throws DaoException;
	boolean declineAlien(int alienId) throws DaoException;
	boolean declineProfileImage(int alienId) throws DaoException;
	boolean approveProfileImage(int alienId) throws DaoException;
	boolean approveSuggestedImage(String alienImageUrl) throws DaoException;
	boolean declineSuggestedImage(String alienImageUrl) throws DaoException;
	int findMaxAlienId() throws DaoException;
	
}

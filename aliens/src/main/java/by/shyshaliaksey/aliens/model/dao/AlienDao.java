package by.shyshaliaksey.aliens.model.dao;

import java.util.List;
import java.util.Optional;

import by.shyshaliaksey.aliens.exception.DaoException;
import by.shyshaliaksey.aliens.model.entity.Alien;
import by.shyshaliaksey.aliens.model.entity.Comment;

public interface AlienDao {

	/**
	 * Finds all aliens with {@link Alien.Status#NORMAL} status from specified
	 * record in database with limit
	 * 
	 * @param fromRecord         start id record in database
	 * @param aliensPerPageLimit aliens limit per page
	 * @return {@code List<Alien>} with {@link Alien.Status#NORMAL} status from
	 *         database
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	List<Alien> findAll(int fromRecord, int aliensPerPageLimit) throws DaoException;

	/**
	 * Finds alien with specified id
	 * 
	 * @param alienId id of requested alien
	 * @return requested {@code Optional<Alien>} from database
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	Optional<Alien> findById(int alienId) throws DaoException;

	/**
	 * Finds alien with specified id and {@link Alien.Status}
	 * 
	 * @param alienId id of requested alien
	 * @param status  status of requested alien
	 * @return requested {@code Optional<Alien>} from database
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	Optional<Alien> findByIdAndStatus(int alienId, Alien.Status status) throws DaoException;

	/**
	 * Finds alien with specified alien name
	 * 
	 * @param alienName name of requested alien
	 * @return requested {@code Optional<Alien>} from database
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	Optional<Alien> findByName(String alienName) throws DaoException;
	
	/**
	 * Adds new alien with specified parameters and {@link Alien.Status#NORMAL}
	 * status into aliens table and alien image into gallery
	 * 
	 * @param alienName             name of new alien
	 * @param alienSmallDescription small description of new alien
	 * @param alienFullDescription  full description of new alien
	 * @param imageUrl              profile image of new alien
	 * @return generated id of new alien
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	boolean addNewAlienAndImageToGallery(String alienName, String alienSmallDescription,
			String alienFullDescription, String imageUrl) throws DaoException;

	/**
	 * Adds new alien with specified parameters and
	 * {@link Alien.Status#UNDER_CONSIDERATION} status into database
	 * 
	 * @param alienName             name of suggested alien
	 * @param alienSmallDescription small description of suggested alien
	 * @param alienFullDescription  full description of suggested alien
	 * @param imageUrl              profile image of suggested alien
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	void suggestNewAlien(String alienName, String alienSmallDescription, String alienFullDescription, String imageUrl)
			throws DaoException;

	/**
	 * Adds new alien image url for specified alien with
	 * {@link Alien.Status#UNDER_CONSIDERATION} status
	 * 
	 * @param alienId  id of alien
	 * @param imageUrl url of new image
	 * @return true if count of added rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	boolean suggestNewAlienImage(int alienId, String imageUrl) throws DaoException;

	/**
	 * Adds new alien image url into image gallery for specified alien with
	 * {@link Alien.Status#NORMAL} status
	 * 
	 * @param alienId  id of alien
	 * @param imageUrl url of new image
	 * @return true if count of added rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	boolean addNewImageToGallery(int alienId, String imageUrl) throws DaoException;

	/**
	 * Updates alien info for specified alien
	 * 
	 * @param alienId               id of alien
	 * @param alienName             name of alien
	 * @param alienSmallDescription small description of alien
	 * @param alienFullDescription  full description of alien
	 * @return true if count of added rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	boolean updateAlienInfo(int alienId, String alienName, String alienSmallDescription, String alienFullDescription)
			throws DaoException;

	/**
	 * Updates alien image and adds it to gallery for specified alien
	 * 
	 * @param alienId  id of alien
	 * @param imageUrl url of new image
	 * @return true if count of added rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	boolean updateAlienImageAndAddToGallery(int alienId, String imageUrl) throws DaoException;
	
	/**
	 * Finds alien count with {@link Alien.Status#NORMAL} status
	 * 
	 * @return {@code int} alien count with {@link Alien.Status#NORMAL} status
	 * @throws DaoException if dao exception occurred
	 */
	int findAlienCount() throws DaoException;

	/**
	 * Finds comment count {@link Alien.Status#NORMAL} for specified alien
	 * 
	 * @param alienId id of alien
	 * @return {@code int} alien comment count with {@link Comment.Status#NORMAL}
	 *         status
	 * @throws DaoException if dao exception occurred
	 * @see Comment
	 * @see Alien
	 */
	int findAlienCommentsCount(int alienId) throws DaoException;

	/**
	 * Finds all comments for specified alien from specified record in database with
	 * limit
	 * 
	 * @param alienId              id of alien
	 * @param fromRecord           start id record in database
	 * @param commentsPerPageLimit comments limit per page
	 * @return {@code List<Comment>} comments for specified alien
	 * @throws DaoException if dao exception occurred
	 * @see Comment
	 * @see Alien
	 */
	List<Comment> findComments(int alienId, int fromRecord, int commentsPerPageLimit) throws DaoException;

	/**
	 * Finds all images with {@link Alien.Status#NORMAL} for specified alien
	 * 
	 * @param alienId id of alien
	 * @return {@code List<String>} images urls for specified alien
	 * @throws DaoException if dao exception occurred
	 * @see Comment
	 * @see Alien
	 */
	List<String> findImages(int alienId) throws DaoException;

	/**
	 * Finds aliens with {@link Alien.Status#UNDER_CONSIDERATION} status from
	 * specified record in database with limit
	 * 
	 * @param fromRecord         start id record in database
	 * @param aliensPerPageLimit aliens limit per page
	 * @return {@code List<Alien>} aliens with
	 *         {@link Alien.Status#UNDER_CONSIDERATION} status
	 * @throws DaoException if dao exception occurred
	 * @see Alien
	 */
	List<Alien> findAllUnapprovedAliens(int fromRecord, int aliensPerPageLimit) throws DaoException;

	/**
	 * Finds number of aliens with {@link Alien.Status#UNDER_CONSIDERATION} status
	 * 
	 * @return {@code int} alien count with {@link Alien.Status#UNDER_CONSIDERATION}
	 *         status
	 * @throws DaoException if dao exception occurred
	 */
	int findUnapprovedAlienCount() throws DaoException;

	/**
	 * Finds number of aliens images count with
	 * {@link Alien.Status#UNDER_CONSIDERATION} status
	 * 
	 * @return {@code int} number of aliens images with
	 *         {@link Alien.Status#UNDER_CONSIDERATION} status
	 * @throws DaoException if dao exception occurred
	 */
	int findUnapprovedAliensImagesCount() throws DaoException;

	/**
	 * Finds aliens images with {@link Alien.Status#UNDER_CONSIDERATION} status
	 * 
	 * @param fromRecord         start id record in database
	 * @param imagesPerPageLimit images limit per page
	 * @return {@code List<Alien>} aliens {@link Alien#Alien(int, String, String)}
	 *         with {@link Alien.Status#UNDER_CONSIDERATION} status
	 * @throws DaoException if dao exception occurred
	 */
	List<Alien> findUnapprovedAliensImages(int fromRecord, int imagesPerPageLimit) throws DaoException;

	/**
	 * Approves alien with specified id (changes status to
	 * {@link Alien.Status#NORMAL})
	 * 
	 * @param alienId id of alien
	 * @return true if count of updated rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 */
	boolean approveAlien(int alienId) throws DaoException;

	/**
	 * Declines alien with specified id (changes status to
	 * {@link Alien.Status#DECLINED})
	 * 
	 * @param alienId id of alien
	 * @return true if count of updated rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 */
	boolean declineAlien(int alienId) throws DaoException;

	/**
	 * Approves alien suggested image with specified url (changes status to
	 * {@link Alien.Status#NORMAL})
	 * 
	 * @param alienImageUrl url of new image
	 * @return true if count of updated rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 */
	boolean approveSuggestedImage(String alienImageUrl) throws DaoException;

	/**
	 * Declines alien suggested image with specified url (changes status to
	 * {@link Alien.Status#DECLINED})
	 * 
	 * @param alienImageUrl url of new image
	 * @return true if count of updated rows equals 1, false otherwise
	 * @throws DaoException if dao exception occurred
	 */
	boolean declineSuggestedImage(String alienImageUrl) throws DaoException;

}
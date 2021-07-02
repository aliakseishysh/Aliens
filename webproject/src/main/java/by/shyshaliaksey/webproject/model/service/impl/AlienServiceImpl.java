package by.shyshaliaksey.webproject.model.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;

import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.AlienPage;
import by.shyshaliaksey.webproject.model.entity.Comment;
import by.shyshaliaksey.webproject.model.entity.HomePage;
import by.shyshaliaksey.webproject.model.service.AlienService;
import jakarta.servlet.http.Part;

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
	public List<Alien> findAllAliens(int pageNumber) throws ServiceException {
		try {
			int aliensPerPageLimit = HomePage.ALIENS_PER_PAGE;
			int fromRecord = aliensPerPageLimit*(pageNumber - 1);
			List<Alien> aliens = alienDao.findAll(fromRecord, aliensPerPageLimit);
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
			throw new ServiceException("Error occured while finding alien by id: " + alienId + " :" + e.getMessage(),
					e);
		}
	}

	@Override
	public boolean addNewAlien(String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException {
		boolean result = false;
		try {
			Optional<Alien> alienInDatabase = alienDao.findByName(alienName);
			if (!alienInDatabase.isPresent()) {
				// TODO equals null or something else?
				if (alienImage == null) {
					result = alienDao.addNewAlien(alienName, alienSmallDescription, alienFullDescription,
							FilePath.IMAGE_DEFAULT.getValue());
				} else {
					Optional<String> urlResult = uploadImage(alienName, alienImage, rootFolder, serverDeploymentPath);
					if (urlResult.isPresent()) {
						result = alienDao.addNewAlien(alienName, alienSmallDescription, alienFullDescription,
								urlResult.get());
					}
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("Error occured when adding new alien " + alienName + " :" + e.getMessage(), e);
		}
		return result;
	}
	
	@Override
	public boolean updateAlien(int alienId, String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException {
		boolean result = false;
		try {
			Optional<Alien> alienInDatabase = alienDao.findById(alienId);
			if (alienInDatabase.isPresent()) {
				// TODO equals null or something else?
				if (alienImage == null) {
					result = alienDao.updateAlien(alienId, alienName, alienSmallDescription, alienFullDescription,
							FilePath.IMAGE_DEFAULT.getValue());
				} else {
					Optional<String> urlResult = uploadImage(alienName, alienImage, rootFolder, serverDeploymentPath);
					if (urlResult.isPresent()) {
						result = alienDao.updateAlien(alienId, alienName, alienSmallDescription, alienFullDescription,
								urlResult.get());
					}
				}
			}
		} catch (DaoException e) {
			throw new ServiceException("Error occured when updating alien " + alienName + " :" + e.getMessage(), e);
		}
		return result;
	}
	
	@Override
	public List<Comment> findAllComments(int alienId) throws ServiceException {
		try {
			List<Comment> comments = alienDao.findAllComments(alienId);
			return comments;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while finding comments: " + e.getMessage() + e.getStackTrace(), e);
		}
	}
	
	@Override
	public int findAlienCount() throws ServiceException {
		try {
			int alienCount = alienDao.findAlienCount();
			return alienCount;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while finding alien count: " + e.getMessage() + e.getStackTrace(), e);
		}
	}
	
	@Override
	public int findAlienCommentsCount(int alienId) throws ServiceException {
		try {
			int alienCount = alienDao.findAlienCommentsCount(alienId);
			return alienCount;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while finding comments: " + e.getMessage() + e.getStackTrace(), e);
		}
	}
	
	@Override
	public List<Comment> findAllCommentsInPage(int alienId, int page) throws ServiceException {
		try {
			int aliensPerPageLimit = AlienPage.COMMENTS_PER_PAGE;
			int fromRecord = aliensPerPageLimit*(page - 1);
			List<Comment> comments = alienDao.findComments(alienId, fromRecord, aliensPerPageLimit);
			return comments;
		} catch (DaoException e) {
			throw new ServiceException("Error occured while finding comments: " + e.getMessage() + e.getStackTrace(), e);
		}
	}

	// return new image path
	private Optional<String> uploadImage(String alienName, Part part, String rootFolder, String serverDeploymentPath)
			throws ServiceException {
		Optional<String> result = Optional.empty();
		try (InputStream inputStream1 = part.getInputStream(); InputStream inputStream2 = part.getInputStream();) {
			String submittedFileName = part.getSubmittedFileName();
			// TODO error will occured if file_name with wrong type, need validation
			String fileExtension = FilenameUtils.getExtension(submittedFileName);
			String newFileName = "alien_image_" + alienName + "." + fileExtension;
			String realpath = rootFolder + FolderPath.ALIEN_IMAGE_FOLDER.getValue() + newFileName;
			Path imageRealPath = Paths.get(realpath);
			Path imageServerDeploymentPath = Paths.get(serverDeploymentPath + newFileName);
			long bytes1 = createFile(inputStream1, imageRealPath);
			long bytes2 = createFile(inputStream2, imageServerDeploymentPath);
			if (bytes1 > 0 && bytes2 > 0) {
				String url = FolderPath.ALIEN_IMAGE_FOLDER.getValue() + newFileName;
				result = Optional.of(url);
			}
		} catch (IOException e) {
			throw new ServiceException(
					"Error occured when uploading image to server for: " + alienName + " :" + e.getMessage(), e);
		}
		return result;
	}

	// TODO create util package or UtilService and place this method there (from
	// UserService too)
	private long createFile(InputStream inputStream, Path imagePath) throws ServiceException {
		try {
			Files.deleteIfExists(imagePath);
			imagePath = Files.createFile(imagePath);
			long bytes = Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
			return bytes;
		} catch (IOException e) {
			throw new ServiceException("Error occured when creating file by path: " + imagePath + " :" + e.getMessage(),
					e);
		}
	}



	

}

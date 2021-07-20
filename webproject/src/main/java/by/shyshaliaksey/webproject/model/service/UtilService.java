package by.shyshaliaksey.webproject.model.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface UtilService {

//	Optional<String> uploadAlienImage(int alienId, String rootFolder, String serverDeploymentPath, Part part) throws ServiceException;
	
//	Optional<String> uploadUserImage(int userId, String fileExtension, String rootFolder, String serverDeploymentPath, Part part) throws ServiceException;

	long createFile(InputStream inputStream, Path imagePath) throws ServiceException;

	byte[] hashPassword(String password, byte[] salt) throws ServiceException;

	byte[] createSalt() throws ServiceException;

	String createToken(String email) throws ServiceException;

	boolean sendEmail(String emailTo, String message) throws ServiceException;

	boolean activateAccount(String email, String token) throws ServiceException;

	boolean uploadImage(String folderToUpload, String imageFolder, String fileName, Part part) throws ServiceException;

	String prepareUserProfileImageName(String submittedFileName);

	String prepareAlienImageName(String submittedFileName);

}

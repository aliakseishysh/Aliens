package by.shyshaliaksey.webproject.model.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface UtilService {

	Optional<String> uploadAlienImage(String alienName, String imagePrefix, String rootFolder, String serverDeploymentPath, Part part) throws ServiceException;
	
	Optional<String> uploadUserImage(int userId, String imagePrefix, String fileExtension, String rootFolder, String serverDeploymentPath, Part part) throws ServiceException;

	long createFile(InputStream inputStream, Path imagePath) throws ServiceException;

	byte[] hashPassword(String password, byte[] salt) throws ServiceException;

	byte[] createSalt() throws ServiceException;

}

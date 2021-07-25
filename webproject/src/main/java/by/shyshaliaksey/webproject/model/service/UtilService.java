package by.shyshaliaksey.webproject.model.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface UtilService {

	long createFile(InputStream inputStream, Path imagePath) throws ServiceException;

	byte[] hashPassword(String password, byte[] salt) throws ServiceException;

	byte[] createSalt() throws ServiceException;

	String createToken(String email) throws ServiceException;

	boolean uploadImage(String folderToUpload, String imageFolder, String fileName, Part part) throws ServiceException;

	String prepareUserProfileImageName(String submittedFileName);

	String prepareAlienImageName(String submittedFileName);

	boolean activateAccount(String tokenRequested) throws ServiceException;

}

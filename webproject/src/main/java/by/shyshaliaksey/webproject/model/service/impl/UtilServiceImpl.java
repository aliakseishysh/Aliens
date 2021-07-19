package by.shyshaliaksey.webproject.model.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

// TODO change to jakarta
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.io.FilenameUtils;

import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.email.EmailPropertiesReader;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.TimeService;
import by.shyshaliaksey.webproject.model.service.UtilService;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Part;
import jakarta.xml.bind.DatatypeConverter;

public class UtilServiceImpl implements UtilService {

	private static final UserDao userDao = DaoProvider.getInstance().getUserDao();
	private static final String ALIEN_IMAGE_PREFIX = "alien_image_";
	private static final String USER_IMAGE_PREFIX = "user_profile_image_";
//	// TODO refactor
	@Override
	public Optional<String> uploadAlienImage(String rootFolder, String serverDeploymentPath, Part part) throws ServiceException {
		Optional<String> result = Optional.empty();
		try (InputStream inputStream1 = part.getInputStream(); InputStream inputStream2 = part.getInputStream();) {
			String submittedFileName = part.getSubmittedFileName();
			// TODO error will occured if file_name with wrong type, need validation
			String fileExtension = FilenameUtils.getExtension(submittedFileName);

			String newFileName = ALIEN_IMAGE_PREFIX + createRandomName() + "." + fileExtension;
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
					"Error occured when uploading image to server" + ": " + e.getMessage(), e);
		}
		return result;
	}

	//user_profile_image_
	@Override
	public Optional<String> uploadUserImage(int userId, String fileExtension, String rootFolder, String serverDeploymentPath, Part part) throws ServiceException {
		try (InputStream inputStream1 = part.getInputStream(); InputStream inputStream2 = part.getInputStream();) {
			Optional<String> result = Optional.empty();
			String newFileName = USER_IMAGE_PREFIX + createRandomName() + "." + fileExtension;
			String realpath = rootFolder + FolderPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
			Path imageRealPath = Paths.get(realpath);
			Path imageServerDeploymentPath = Paths.get(serverDeploymentPath + newFileName);
			long bytes = createFile(inputStream1, imageRealPath);
			if (bytes > 0) {
				String url = FolderPath.PROFILE_IMAGE_FOLDER.getValue() + newFileName;
				boolean imageUpdateResult = userDao.updateProfileImage(url, userId);
				if (imageUpdateResult) {
					result = Optional.of(url);
				}
				bytes = createFile(inputStream2, imageServerDeploymentPath);
			}
			return result;
		} catch (ServiceException | IOException | DaoException e) {
			throw new ServiceException("Can not upload image", e);
		}
	}

	@Override
	public long createFile(InputStream inputStream, Path imagePath) throws ServiceException {
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

	@Override
	public byte[] hashPassword(String password, byte[] salt) throws ServiceException {
		// do not change this final variables
		final int iterations = 65536;
		final int size = 32 * 8;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, size);
		final String algorithmName = "PBKDF2WithHmacSHA1";
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return hash;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error occured while instantiating SecretKeyFactory with algorithm "
					+ algorithmName + " :" + e.getMessage(), e);
		}
	}

	@Override
	public byte[] createSalt() throws ServiceException {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;
	}
	
	@Override
	public String createToken(String email) throws ServiceException {
		final int iterations = 10;
		final int size = 32 * 8;
		KeySpec spec = new PBEKeySpec(email.toCharArray(), createSalt(), iterations, size);
		final String algorithmName = "PBKDF2WithHmacSHA1";
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return DatatypeConverter.printHexBinary(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error occured while instantiating SecretKeyFactory with algorithm "
					+ algorithmName + " :" + e.getMessage(), e);
		}
	}
	
	@Override
	public boolean sendEmail(String emailTo, String address) throws ServiceException {
		Properties properties = EmailPropertiesReader.getPropeties();
		final String userKey = "mail.smtp.user";
		final String passwordKey = "mail.smtp.password";
		final String user = properties.getProperty(userKey);
		final String password = properties.getProperty(passwordKey);
		properties.remove(userKey);
		properties.remove(passwordKey);
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			final String subject = "Email Confirmation";
			message.setSubject(subject);
			final String content = "Click to confirm email: <a href='" + address + "'>link</a>";
			final String contentType = "text/html";
			message.setContent(content, contentType);
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			throw new ServiceException("Can not send message : " + e.getMessage(), e);
		}
	}
	
	@Override
	public boolean activateAccount(String email, String token) throws ServiceException {
		try {
			boolean result = false;
			TimeService timeService = ServiceProvider.getInstance().getTimeService();
			Optional<String> tokenExpires = userDao.findTokenExpiresDate(email, token);
			if(tokenExpires.isPresent()) {
				boolean isExpired = timeService.isExpired(tokenExpires.get());
				if (!isExpired) {
					boolean activateUserAccountResult = userDao.changeUserStatus(email);
					result = activateUserAccountResult;
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Can not activate user account", e);
		}
	}
	
	private String createRandomName() {
		Random random = new Random(Long.MAX_VALUE);
		Long long1 = random.nextLong();
		Long long2 = random.nextLong();
		String result = long1.toString() + Long.toHexString(long2);
		return result;
	}

}

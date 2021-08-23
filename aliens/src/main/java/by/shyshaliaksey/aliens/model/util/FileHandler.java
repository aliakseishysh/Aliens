package by.shyshaliaksey.aliens.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.Part;

/**
 * Class {@code FileHandler} designed for operations with files and files names
 * 
 * @author Aliaksey Shysh
 *
 */
public class FileHandler {

	private static final Logger logger = LogManager.getRootLogger();
	private static final Random random = new SecureRandom();
	private static final String ALIEN_IMAGE_PREFIX = "alien_image";
	private static final String UNDERSCORE = "_";
	private static final String POINT = ".";

	private FileHandler() {
	}

	/**
	 * Uploads image to {@code folderToUpload}
	 * 
	 * @param folderToUpload {@code String} folder to upload image
	 * @param imageFolder    {@code String} url of image folder
	 * @param fileName       {@code String} name of uploaded file
	 * @param part           {@code Part} image to upload
	 * @return {@code true} if uploading ws successful, {@code false} otherwise
	 */
	public static boolean uploadImage(String folderToUpload, String imageFolder, String fileName, Part part) {
		boolean result;
		try (InputStream inputStream = part.getInputStream()) {
			String rootFolderString = folderToUpload + imageFolder + fileName;
			Path rootStaticPath = Paths.get(rootFolderString);
			result = createFile(inputStream, rootStaticPath) > 0;
		} catch (IOException e) {
			result = false;
		}
		return result;
	}

	/**
	 * Prepares new alien image name
	 * 
	 * @param submittedFileName {@code String} submitted file name
	 * @return {@code String} new file name
	 */
	public static String prepareAlienImageName(String submittedFileName) {
		String fileExtension = FilenameUtils.getExtension(submittedFileName);
		StringBuilder resultFileName = new StringBuilder();
		resultFileName.append(ALIEN_IMAGE_PREFIX);
		resultFileName.append(UNDERSCORE);
		resultFileName.append(FileHandler.createRandomName());
		resultFileName.append(POINT);
		resultFileName.append(fileExtension);
		return resultFileName.toString();
	}

	/**
	 * Creates file from {@code InputStream} object by {@code Path} object
	 * 
	 * @param inputStream {@code InputStream} file input stream
	 * @param imagePath   {@code Path} image path upload to
	 * @return {@code String} the number of bytes written
	 */
	private static long createFile(InputStream inputStream, Path imagePath) {
		long result = 0;
		try {
			boolean isExist = Files.exists(imagePath);
			if (!isExist) {
				Files.createFile(imagePath);
				result = Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			logger.log(Level.ERROR, "Error occurred when creating file by path: {} {} {}", imagePath, e.getMessage(),
					e.getStackTrace());
		}
		return result;
	}

	/**
	 * Generates random hex string
	 * 
	 * @return random hex string
	 */
	private static String createRandomName() {
		long long1 = random.nextLong();
		long long2 = random.nextLong();
		long long3 = random.nextLong();
		long long4 = random.nextLong();
		StringBuilder builder = new StringBuilder();
		builder.append(Long.toHexString(long1));
		builder.append(Long.toHexString(long2));
		builder.append(Long.toHexString(long3));
		builder.append(Long.toHexString(long4));
		return builder.toString();
	}

}

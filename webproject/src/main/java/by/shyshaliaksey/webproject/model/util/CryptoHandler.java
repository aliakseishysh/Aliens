package by.shyshaliaksey.webproject.model.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.xml.bind.DatatypeConverter;

/**
 * Class {@code PasswordHandler} designed for all cryptographic operations
 * 
 * @author Aliaksey Shysh
 *
 */
public class CryptoHandler {

	private static final Logger logger = LogManager.getRootLogger();

	private CryptoHandler() {
	}

	/**
	 * Hashes password with provided salt
	 * 
	 * @param password {@code String} password to hash
	 * @param salt     {@code String} salt to hash password
	 * @return {@code byte[]} hashed password
	 */
	public static byte[] hashPassword(String password, byte[] salt) {
		final String algorithmName = "PBKDF2WithHmacSHA1";
		try {
			// do not change this final variables
			final int iterations = 65536;
			final int size = 32 * 8;
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, size);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return hash;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// can't happen in context of program
			logger.log(Level.FATAL, "Error occured while instantiating SecretKeyFactory with algorithm {} {} {}",
					algorithmName, e.getMessage(), e.getStackTrace());
			throw new RuntimeException(
					"Error occured while instantiating SecretKeyFactory with algorithm " + algorithmName, e);
		}
	}

	/**
	 * Generates salt
	 * 
	 * @return {@code byte[]} generated salt
	 */
	public static byte[] createSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;
	}

	/**
	 * Creates token from email with generated salt
	 * 
	 * @return {@code String} generated token
	 */
	public static String createToken() {
		final int iterations = 10;
		final int size = 32 * 8;
		final String algorithmName = "PBKDF2WithHmacSHA1";
		try {
			char[] messageToHash = DatatypeConverter.printHexBinary(createSalt()).toCharArray();
			KeySpec spec = new PBEKeySpec(messageToHash, createSalt(), iterations, size);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return DatatypeConverter.printHexBinary(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// can't happen in context of program
			logger.log(Level.FATAL, "Error occured while instantiating SecretKeyFactory with algorithm {} {} {}",
					algorithmName, e.getMessage(), e.getStackTrace());
			throw new RuntimeException(
					"Error occured while instantiating SecretKeyFactory with algorithm " + algorithmName, e);
		}
	}

}

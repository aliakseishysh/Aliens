package by.shyshaliaksey.aliens.model.util.localization;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code LocalizationReader} designed for read localization files
 * 
 * @author Aliaksey Shysh
 *
 */
public class LocalizationReader {

	private static final Logger logger = LogManager.getRootLogger();
	private final ResourceBundle resourceBundle;

	public LocalizationReader(Locale locale) {
		try {
			resourceBundle = ResourceBundle.getBundle("\\localization\\localization", locale);
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, "Can not read localization properties file: {} {} {}", locale.toLanguageTag(),
					e.getMessage(), e.getStackTrace());
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

}

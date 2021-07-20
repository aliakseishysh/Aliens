package by.shyshaliaksey.webproject.model.util.localization;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocalizationReader {

	private static Logger logger = LogManager.getRootLogger();
	private ResourceBundle resourceBundle;
	
	public LocalizationReader(Locale locale) {
		try {
			resourceBundle = ResourceBundle.getBundle("\\localization\\localization", locale);			
		} catch (MissingResourceException e) {
			throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
		}
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
}

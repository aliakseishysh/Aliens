package by.shyshaliaksey.aliens.controller;

/**
 * Enum with different session attributes
 */
public enum SessionAttribute {

	BAN_INFO, 
	CURRENT_LOCALIZATION_NAME, 
	CURRENT_LOCALE, 
	/**
	 * Required for custom_file.css
	 */
	CURRENT_LOCALE_ABBREVIATION,
	/**
	 * Provides access to ResourceBundle in jsp
	 */
	TEXT

}

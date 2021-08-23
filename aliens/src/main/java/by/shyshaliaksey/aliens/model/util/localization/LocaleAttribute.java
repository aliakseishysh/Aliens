package by.shyshaliaksey.aliens.model.util.localization;

import java.util.Locale;
import java.util.ResourceBundle;

import by.shyshaliaksey.aliens.controller.EnumValue;

/**
 * Enum {@code LocaleAttribute} designed for storing {@code ResourceBundle}
 * objects with localizations
 * 
 * @author Aliaksey Shysh
 *
 */
public enum LocaleAttribute implements EnumValue {

	LOCALIZATION_RU("Русский", new LocalizationReader(new Locale("ru", "RU")).getResourceBundle()),
	LOCALIZATION_EN("English", new LocalizationReader(new Locale("en", "GB")).getResourceBundle());

	private final String value;
	private final ResourceBundle resourceBundle;

	LocaleAttribute(String value, ResourceBundle resourceBundle) {
		this.value = value;
		this.resourceBundle = resourceBundle;
	}

	@Override
	public String getValue() {
		return value;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public Locale getLocale() {
		return resourceBundle.getLocale();
	}

	public String getLocalizedMessage(String key) {
		return resourceBundle.getString(key);
	}

	public static LocaleAttribute fromString(String requestedLocale) {
		LocaleAttribute result = LOCALIZATION_EN;
		for (LocaleAttribute locale : LocaleAttribute.values()) {
			if (locale.getValue().equals(requestedLocale)) {
				result = locale;
			}
		}
		return result;
	}

}

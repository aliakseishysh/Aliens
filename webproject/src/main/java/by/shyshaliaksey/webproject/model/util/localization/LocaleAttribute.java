package by.shyshaliaksey.webproject.model.util.localization;

import java.util.Locale;
import java.util.ResourceBundle;

import by.shyshaliaksey.webproject.controller.EnumValue;
import by.shyshaliaksey.webproject.controller.command.CommandValue;

public enum LocaleAttribute implements EnumValue {

	LOCALIZATION_RU("Русский", new LocalizationReader(new Locale("ru", "RU")).getResourceBundle()),
	LOCALIZATION_EN("English", new LocalizationReader(new Locale("en", "GB")).getResourceBundle());
	
	private String value;
	private Locale locale;
	private ResourceBundle resourceBundle;

	
	private LocaleAttribute(String value, ResourceBundle resourceBundle) {
		this.value = value;
		this.locale = resourceBundle.getLocale();
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
		return locale;
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

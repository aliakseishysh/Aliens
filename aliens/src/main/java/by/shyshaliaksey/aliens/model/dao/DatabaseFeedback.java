package by.shyshaliaksey.aliens.model.dao;

import by.shyshaliaksey.aliens.controller.EnumValue;

/**
 * Class with database feedback keys
 */
public class DatabaseFeedback {

	/**
	 * Enum that defines database feedback keys
	 */
	public enum Key implements EnumValue {
		EMPTY_MESSAGE(""), PASSWORD("password"), SALT("salt");

		private final String value;

		Key(String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}
	}

}

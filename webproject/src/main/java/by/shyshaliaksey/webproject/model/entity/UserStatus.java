package by.shyshaliaksey.webproject.model.entity;

import by.shyshaliaksey.webproject.controller.EnumValue;

public enum UserStatus implements EnumValue {
	
	NORMAL("NORMAL"),
	BANNED("BANNED");

	private String value;
	
	private UserStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}

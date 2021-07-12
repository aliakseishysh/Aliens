package by.shyshaliaksey.webproject.model.entity;

import by.shyshaliaksey.webproject.controller.EnumValue;

public enum Role implements EnumValue {
	
	ADMIN("ADMIN"),
	USER("USER"),
	GUEST("GUEST");

	private String value;
	
	private Role(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}

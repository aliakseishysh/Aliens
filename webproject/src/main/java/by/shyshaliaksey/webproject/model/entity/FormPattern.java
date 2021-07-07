package by.shyshaliaksey.webproject.model.entity;

import by.shyshaliaksey.webproject.controller.EnumValue;

public enum FormPattern implements EnumValue  {

	VALID_EMAIL("(?=^.{3,254}$)^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"),
	VALID_LOGIN("(?=^.{6,30}$)^[a-zA-Z0-9_]+$"),
	VALID_PASSWORD("(?=^.{8,30}$)^[a-zA-Z0-9]+$"),
	VALID_IMAGE_EXTENSION("^(jpg|jpeg|png)$"),
	VALID_DIGIT("^[1-9][0-9]*$"),
	VALID_IMAGE_SIZE("1000000"); // TODO really? this needed to set maximum image size and send it to client side to js script
	
	private String value;
	
	private FormPattern(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	
}

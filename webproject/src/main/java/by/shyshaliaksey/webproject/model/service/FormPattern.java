package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.controller.EnumValue;

/**
 * Enum {@code FormPattern} designed for storing regular expressions with
 * purpose of forms validation.
 * 
 * @author Aliaksey Shysh
 *
 */
public enum FormPattern implements EnumValue {

	VALID_EMAIL("(?=^.{3,254}$)^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"),
	VALID_LOGIN("(?=^.{6,30}$)^[a-zA-Z0-9_]+$"), 
	VALID_PASSWORD("(?=^.{8,30}$)^[a-zA-Z0-9_!#$%&'*+/=?`(){|}~^.@-]+$"),
	VALID_IMAGE_EXTENSION("^(jpg|jpeg|png)$"), 
	VALID_DAYS_IN_BAN("(?=^.{1,5}$)^[1-9][0-9]*$"),
	VALID_ALIEN_NAME("(?=^.{3,30}$)^[a-zA-Z0-9_ ]+$"),
	/*
	 * p don't work in js
	 */
	VALID_ALIEN_SMALL_DESCRIPTION("(?=^.{6,100}$)^[\\s0-9a-zA-Zа-яА-Я!?#%*()\\-_\\|\\/,\\.…:;\']+$"),
	VALID_ALIEN_FULL_DESCRIPTION("(?=^.{10,3000}$)^[\\s0-9a-zA-Zа-яА-Я!?#%*()\\-_\\|/,\\.…:;\']+$"),
	VALID_COMMENT("(?=^.{3,300}$)^[\\s0-9a-zA-Zа-яА-Я!?#%*()\\-_\\|/,\\.…:;\']+$"),
	MAX_VALID_IMAGE_SIZE("1000000");

	private String value;

	private FormPattern(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}

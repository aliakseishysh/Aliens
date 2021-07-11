package by.shyshaliaksey.webproject.controller;

public enum ApplicationAttribute implements EnumValue {

	CONTROLLER("controller"),
	PROJECT_NAME("webproject");
	
	private String value;
	
	private ApplicationAttribute(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

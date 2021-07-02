package by.shyshaliaksey.webproject.controller;

public enum SessionAttribute implements EnumValue {

	CONTROLLER("controller"),
	PROJECT_NAME("webproject");
	
	private String value;
	
	private SessionAttribute(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

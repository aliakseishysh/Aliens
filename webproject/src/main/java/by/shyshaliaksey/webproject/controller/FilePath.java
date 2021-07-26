package by.shyshaliaksey.webproject.controller;

public enum FilePath implements EnumValue  {

	IMAGE_DEFAULT("/images/standard.png");
	
	private String value;
	
	private FilePath(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

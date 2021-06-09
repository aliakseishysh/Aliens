package by.shyshaliaksey.webproject.model.entity;

public enum Role {
	
	ADMIN("admin"),
	USER("user");

	private String value;
	
	private Role(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}

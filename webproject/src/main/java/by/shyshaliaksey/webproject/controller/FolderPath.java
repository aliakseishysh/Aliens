package by.shyshaliaksey.webproject.controller;

public enum FolderPath {

	IMAGE_FOLDER("/images/"),
	ALIEN_IMAGE_FOLDER("/images/alien/"),
	PROFILE_IMAGE_FOLDER("/images/profile/");
	
	private String value;
	
	private FolderPath(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}

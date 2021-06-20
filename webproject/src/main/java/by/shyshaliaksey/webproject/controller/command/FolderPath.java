package by.shyshaliaksey.webproject.controller.command;

public enum FolderPath {

	IMAGE_FOLDER("/images/"),
	PROFILE_IMAGE_FOLDER("/images/profile/");
	
	private String value;
	
	private FolderPath(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}

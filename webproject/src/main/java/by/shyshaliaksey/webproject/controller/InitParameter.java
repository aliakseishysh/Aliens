package by.shyshaliaksey.webproject.controller;

public enum InitParameter implements EnumValue  {

	// from web.xml
	WEB_APP_ROOT_FOLDER_PARAMETER("web-app-root-folder"),
	WEB_SITE_NAME("web-site-name");
	
	private String value;
	
	private InitParameter(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

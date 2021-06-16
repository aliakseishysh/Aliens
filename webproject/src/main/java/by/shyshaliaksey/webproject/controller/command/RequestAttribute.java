package by.shyshaliaksey.webproject.controller.command;

public enum RequestAttribute {

	ALIEN("alien"),
	AVERAGE_RATING("averageRating"),
	ALIEN_LIST("aliensList"),
	LOGIN_NAME("login_name"),
	CURRENT_USER("currentUser");

	private String value;
	
	private RequestAttribute(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static RequestAttribute fromString(String attributeName) {
        for (RequestAttribute attributeValue : RequestAttribute.values()) {
            if (attributeValue.getValue().equals(attributeName)) {
                return attributeValue;
            }
        }
        return null;
    }
	
}

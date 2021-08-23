package by.shyshaliaksey.aliens.controller;

/**
 * Enum with different request attributes
 */
public enum RequestAttribute implements EnumValue {

	ALIEN("alien"), 
	AVERAGE_RATING("averageRating"), 
	ALIEN_LIST("aliensList"),
	ALIEN_COMMENTS("alienComments"),
	LOGIN_NAME("login_name"), 
	CURRENT_USER_ROLE("currentUserRole"),
	CURRENT_USER("currentUser"),
	CURRENT_PAGE("currentPage"), 
	PAGES_COUNT("pagesCount"), 
	ALIEN_IMAGES("alienImages");

	private final String value;

	RequestAttribute(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}

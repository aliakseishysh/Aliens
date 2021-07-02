package by.shyshaliaksey.webproject.controller;

public enum RequestAttribute implements EnumValue  {

	ALIEN("alien"),
	AVERAGE_RATING("averageRating"),
	ALIEN_LIST("aliensList"),
	ALIEN_COMMENTS("alienComments"),
	LOGIN_NAME("login_name"),
	CURRENT_USER_ROLE("currentUserRole"),
	CURRENT_USER_ID("currentUserId"),
	CURRENT_USER_LOGIN("currentUserLogin"),
	CURRENT_USER_EMAIL("currentUserEmail"),
	CURRENT_USER("currentUser"),
	CURRENT_HOME_PAGE("currentHomePage"),
	CURRENT_COMMENT_PAGE("currentCommentPage"),
	PAGES_COUNT("pagesCount");

	private String value;
	
	private RequestAttribute(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
}

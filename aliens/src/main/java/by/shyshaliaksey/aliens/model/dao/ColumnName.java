package by.shyshaliaksey.aliens.model.dao;

/**
 * Class for storing information about tables columns name
 * 
 * @author Aliaksey Shysh
 *
 */
public class ColumnName {

	// table: `aliens`
	public static final String ALIEN_ID = "alien_id";
	public static final String ALIEN_NAME = "name";
	public static final String ALIEN_DESCRIPTION_SMALL = "description_small";
	public static final String ALIEN_DESCRIPTION_FULL = "description_full";
	public static final String ALIEN_IMAGE_URL = "image_url";

	// if change this field, go to carousel.js and change key
	public static final String ALIEN_IMAGE_IMAGE_URL = "image_url";

	// table: `users`
	public static final String USER_ID = "user_id";
	public static final String USER_EMAIL = "email";
	public static final String USER_LOGIN_NAME = "login_name";
	public static final String USER_PASSWORD_HASH = "password_hash";
	public static final String USER_SALT = "salt";
	public static final String USER_IMAGE_URL = "image_url";
	public static final String USER_ROLE_TYPE = "role_type";
	public static final String USER_STATUS = "status";
	public static final String USER_BANNED_TO_DATE = "banned_to_datetime";

	public static final String RATE_VALUE = "rate_value";
	
	// table: `comments`
	public static final String COMMENT_ID ="comment_id";
	public static final String COMMENT ="comment";
	
	// table: `tokens`
	public static final String TOKEN_EMAIL ="email";
	public static final String TOKEN_EXPIRATION ="expiration_date";
	public static final String TOKEN_NEW_EMAIL ="new_email";

	// operations
	public static final String AVERAGE_RATE = "averageRate";
	public static final String RATES_COUNT = "ratesCount";
	public static final String ALIEN_COUNT = "alienCount";
	public static final String ALIEN_COMMENTS_COUNT = "alienCommentsCount";
	
	private ColumnName() {
	}
}

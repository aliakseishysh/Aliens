package by.shyshaliaksey.webproject.model.dao;

public class ColumnName {
	
	// table names
	public static final String ALIENS = "aliens";
	public static final String USERS = "users";
	public static final String RATINGS = "ratings";
	
	// table: `aliens`
	public static final String ALIEN_ID = "alien_id";
	public static final String ALIEN_NAME = "_name";
	public static final String ALIEN_DESCRIPTION_SMALL = "description_small";
	public static final String ALIEN_DESCRIPTION_FULL = "description_full";
	public static final String ALIEN_IMAGE_URL = "image_url";
	
	// table: `aliens_images`
	public static final String ALIEN_IMAGE_ID = "alien_image_id";
	public static final String ALIEN_IMAGE_ALIEN_ID = "alien_id";
	// TODO if change this, go to carousel.js and change key
	public static final String ALIEN_IMAGE_IMAGE_URL = "image_url";
	public static final String ALIEN_IMAGE_STATUS = "_status";
	
//	// table: `roles`
//	public static final String ROLE_ID = "role_id";
//	public static final String ROLE_NAME = "_name";
//	
	// table: `users`
	public static final String USER_ID = "user_id";
	public static final String USER_EMAIL = "email";
	public static final String USER_LOGIN_NAME = "login_name";
	public static final String USER_PASSWORD_HASH = "password_hash";
	public static final String USER_SALT = "salt";
	public static final String USER_IMAGE_URL = "image_url";
	public static final String USER_ROLE_TYPE = "role_type";
	public static final String USER_STATUS = "_status";
	public static final String USER_BANNED_TO_DATE = "banned_to_datetime";
	
	// table: `ratings`
	public static final String RATE_ID = "rate_id";
	public static final String RATE_ALIEN_ID = "alien_id";
	public static final String RATE_USER_ID = "user_id";
	public static final String RATE_VALUE = "rate_value";
	
	// table: `comments`
	public static final String COMMENT_ID ="comment_id";
	public static final String COMMENT_ALIEN_ID ="alien_id";
	public static final String COMMENT_USER_ID ="user_id";
	public static final String COMMENT ="comment";
	
	// table: `tokens`
	public static final String TOKEN_ID ="token_id";
	public static final String TOKEN_EMAIL ="email";
	public static final String TOKEN ="token";
	public static final String TOKEN_STATUS ="_status";
	public static final String TOKEN_EXPIRATION ="expiration_date";
	public static final String TOKEN_NEW_EMAIL ="new_email";
	
	// operations
	public static final String USERS_COUNT = "usersCount";
	public static final String AVERAGE_RATE = "averageRate";
	public static final String RATES_COUNT = "ratesCount";
	public static final String ALIEN_COUNT = "alienCount";
	public static final String ALIENS_IMAGES_COUNT = "aliensImagesCount";
	public static final String ALIEN_COMMENTS_COUNT = "alienCommentsCount";
	
	private ColumnName() {
	}
}

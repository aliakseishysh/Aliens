package by.shyshaliaksey.webproject.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class {@code DateHandler} designed for handling all date operations
 * 
 * @author Aliaksey Shysh
 *
 */
public class DateHandler {

	private DateHandler() {
	}

	/**
	 * Prepares ban date in DATETIME format for database.
	 * 
	 * @param daysToBan {@code int} number of days to ban user
	 * @return {@code String} ban date int DATETIME format for database
	 */
	public static String prepareBanDate(int daysToBan) {
		String banDate;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(date);
		calendar.add(Calendar.DATE, daysToBan);
		banDate = dateFormat.format(calendar.getTime());
		return banDate;
	}

	/**
	 * Prepares token expiration date in DATETIME format for database
	 * 
	 * @param minutesToExpiration {@code int} number of minutes before token
	 *                            expiration
	 * @return {@code String} token expiration date int DATETIME format for database
	 */
	public static String prepareTokenExpirationDate(int minutesToExpiration) {
		String expirationDate;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutesToExpiration);
		expirationDate = dateFormat.format(calendar.getTime());
		return expirationDate;
	}

	/**
	 * Checks if received date lower then current date
	 * 
	 * @param expirationDateString {@code String} date for check for expiration
	 * @return {@code true} if expired, otherwise {@code false}
	 */
	public static boolean isExpired(String expirationDateString) {
		try {
			Date currentDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date expirationDate = formatter.parse(expirationDateString);
			return currentDate.compareTo(expirationDate) > 0;
		} catch (ParseException e) {
			return false;
		}
	}
}

package by.shyshaliaksey.aliens.model.util;

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
	 * Prepares date in DATETIME format for database.
	 * 
	 * @param timeUnits {@code int} number of time units
	 * @param type      {@code int} one of {@link Calendar} fields describing type
	 *                  of time unit
	 * @return {@code String} date in DATETIME format for database
	 */
	public static String prepareDate(int timeUnits, int type) {
		String banDate;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(date);
		calendar.add(type, timeUnits);
		banDate = dateFormat.format(calendar.getTime());
		return banDate;
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

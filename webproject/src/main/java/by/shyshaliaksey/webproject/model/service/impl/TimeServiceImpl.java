package by.shyshaliaksey.webproject.model.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.service.TimeService;

public class TimeServiceImpl implements TimeService {

	@Override
	public String prepareBanDate(int daysToBan) {
		String banDate;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(date);
		calendar.add(Calendar.DATE, daysToBan);
		banDate = dateFormat.format(calendar.getTime());
		return banDate;
	}
	
	@Override
	public String prepareTokenExpirationDate(int minutesToExpiration) {
		String expirationDate;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutesToExpiration);
		expirationDate = dateFormat.format(calendar.getTime());
		return expirationDate;
	}

	@Override
	public boolean isExpired(String expirationDateString) throws ServiceException {
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date expirationDate = formatter.parse(expirationDateString);
			return currentDate.compareTo(expirationDate) > 0;
		} catch (ParseException e) {
			throw new ServiceException("Can not parse date: " + expirationDateString);
		}
	}

}

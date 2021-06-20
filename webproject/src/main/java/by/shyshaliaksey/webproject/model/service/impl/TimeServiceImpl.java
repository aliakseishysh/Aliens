package by.shyshaliaksey.webproject.model.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

}

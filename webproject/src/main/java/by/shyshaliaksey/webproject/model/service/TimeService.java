package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.exception.ServiceException;

public interface TimeService {

	String prepareBanDate(int daysToBan);
	String prepareTokenExpirationDate(int minutesToExpiration);
	boolean isExpired(String string) throws ServiceException;
	
}

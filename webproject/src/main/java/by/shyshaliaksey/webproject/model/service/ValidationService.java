package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.exception.ServiceException;

public interface ValidationService {

	boolean validateEmail(String email) throws ServiceException;
	boolean validateLogin(String login) throws ServiceException;
	boolean validatePassword(String password) throws ServiceException;
	boolean validateImageExtension(String imageExtension) throws ServiceException;
	boolean validateImageSize(long imageSize) throws ServiceException;
	boolean validateDaysToBan(int daysToBan) throws ServiceException;
	boolean validateAlienName(String alienName) throws ServiceException;
	boolean validateAlienSmallDescription(String alienSmallDescription) throws ServiceException;
	boolean validateAlienFullDescription(String alienFullDescription) throws ServiceException;
	boolean validateComment(String comment) throws ServiceException;
	
}

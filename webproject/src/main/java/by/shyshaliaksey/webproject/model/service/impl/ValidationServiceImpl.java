package by.shyshaliaksey.webproject.model.service.impl;

import java.util.regex.Pattern;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.FormPattern;
import by.shyshaliaksey.webproject.model.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validateEmail(String email) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_EMAIL.getValue(), email);
		return result;
	}

	@Override
	public boolean validateLogin(String login) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_LOGIN.getValue(), login);
		return result;
	}

	@Override
	public boolean validatePassword(String password) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_PASSWORD.getValue(), password);
		return result;
	}

	@Override
	public boolean validateImageExtension(String imageExtension) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_IMAGE_EXTENSION.getValue(), imageExtension);
		return result;
	}

	@Override
	public boolean validateImageSize(long imageSize) throws ServiceException {
		boolean result = imageSize <= Long.parseLong(FormPattern.VALID_IMAGE_SIZE.getValue());
		return result;
	}

	@Override
	public boolean validateDaysToBan(int daysToBan) throws ServiceException {
		boolean result = daysToBan > 0;
		return result;
	}

	@Override
	public boolean validateAlienName(String alienName) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_ALIEN_NAME.getValue(), alienName);
		return result;
	}

	@Override
	public boolean validateAlienSmallDescription(String alienSmallDescription) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_ALIEN_SMALL_DESCRIPTION.getValue(), alienSmallDescription);
		return result;
	}

	@Override
	public boolean validateAlienFullDescription(String alienFullDescription) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_ALIEN_FULL_DESCRIPTION.getValue(), alienFullDescription);
		return result;
	}

	@Override
	public boolean validateComment(String comment) throws ServiceException {
		boolean result = Pattern.matches(FormPattern.VALID_COMMENT.getValue(), comment);
		return result;
	}

}

package by.shyshaliaksey.webproject.model.service;


import by.shyshaliaksey.webproject.exception.ServiceException;

public interface AdminService {

	boolean banUser(String userLogin, int daysToBan) throws ServiceException;

	boolean unbanUser(String userLogin) throws ServiceException;
	
}

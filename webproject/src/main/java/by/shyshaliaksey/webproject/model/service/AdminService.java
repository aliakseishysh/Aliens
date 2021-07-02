package by.shyshaliaksey.webproject.model.service;


import by.shyshaliaksey.webproject.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface AdminService {

	boolean banUser(String userLogin, int daysToBan) throws ServiceException;

	boolean unbanUser(String userLogin) throws ServiceException;

	boolean promoteUser(String userLogin, String currentUserLogin) throws ServiceException;

	boolean demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException;

}

package by.shyshaliaksey.webproject.model.service;


import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.entity.feedback.AddNewUpdateAlienResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.BanUnbanUserResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.PromoteDemoteUserResultInfo;
import jakarta.servlet.http.Part;

public interface AdminService {

	BanUnbanUserResultInfo banUser(String userLogin, String daysToBan) throws ServiceException;
	BanUnbanUserResultInfo unbanUser(String userLogin) throws ServiceException;
	PromoteDemoteUserResultInfo promoteUser(String userLogin, String currentUserLogin) throws ServiceException;
	PromoteDemoteUserResultInfo demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException;
	AddNewUpdateAlienResultInfo addNewAlien(AddNewUpdateAlienResultInfo result, String alienName, String alienSmallDescription, String alienFullDescription, Part alienImage,
			String rootFolder, String serverDeploymentPath) throws ServiceException;
	AddNewUpdateAlienResultInfo updateAlien(int alienId, String alienName, String alienSmallDescription, String alienFullDescription,
			Part alienImage, String rootFolder, String serverDeploymentPath) throws ServiceException;
}

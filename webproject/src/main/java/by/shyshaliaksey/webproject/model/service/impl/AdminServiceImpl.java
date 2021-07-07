package by.shyshaliaksey.webproject.model.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import by.shyshaliaksey.webproject.controller.FilePath;
import by.shyshaliaksey.webproject.controller.FolderPath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.DaoProvider;
import by.shyshaliaksey.webproject.model.dao.UserDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import by.shyshaliaksey.webproject.model.entity.Role;
import by.shyshaliaksey.webproject.model.entity.User;
import by.shyshaliaksey.webproject.model.entity.feedback.BanUnbanUserResultInfo;
import by.shyshaliaksey.webproject.model.entity.feedback.ErrorFeedback;
import by.shyshaliaksey.webproject.model.entity.feedback.PromoteDemoteUserResultInfo;
import by.shyshaliaksey.webproject.model.service.AdminService;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.TimeService;
import by.shyshaliaksey.webproject.model.service.ValidationService;
import jakarta.servlet.http.Part;

public class AdminServiceImpl implements AdminService {

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private static final UserDao userDao = daoProvider.getUserDao();
	private static final AlienDao alienDao = daoProvider.getAlienDao();

	@Override
	public BanUnbanUserResultInfo banUser(String userLogin, String daysToBan) throws ServiceException {
		BanUnbanUserResultInfo result = new BanUnbanUserResultInfo();
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			if(validationService.validateLogin(userLogin)) {
				result.setLoginCorrect(true);
			} else {
				result.setLoginCorrect(false);
				result.setLoginErrorInfo(ErrorFeedback.BAN_UNBAN_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN.getValue());
			}
			int daysToBanInt = -1;
			try {
				daysToBanInt = Integer.parseInt(daysToBan);
			} catch(NumberFormatException e) {
				// TODO nothing to do here
			}
			if(validationService.validateDaysToBan(daysToBanInt)) {
				result.setDaysToBanCorrect(true);
			} else {
				result.setDaysToBanCorrect(false);
				result.setDaysToBanErrorInfo(ErrorFeedback.BAN_UNBAN_USER_RESULT_INFO_FEEDBACK_INVALID_DAYS_TO_BAN.getValue());
			}
			if (result.isLoginCorrect() && result.isDaysToBanCorrect()) {
				Optional<User> user = userDao.findByLogin(userLogin);
				if (user.isPresent()) {
					ServiceProvider serviceProvider = ServiceProvider.getInstance();
					TimeService timeService = serviceProvider.getTimeService();
					String banDate = timeService.prepareBanDate(daysToBanInt);
					boolean banUserResult = userDao.banUser(userLogin, banDate);
					if (banUserResult) {
						result.setDaysToBanCorrect(true);
						result.setLoginCorrect(true);
					} else {
						result.setDaysToBanCorrect(false);
						result.setLoginCorrect(false);
						result.setLoginErrorInfo(ErrorFeedback.BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK.getValue());
						result.setDaysToBanErrorInfo(ErrorFeedback.BAN_UNBAN_USER_STANDARD_DAYS_TO_BAN_FEEDBACK.getValue());
					}
				} else {
					result.setLoginCorrect(false);
					result.setLoginErrorInfo(ErrorFeedback.NO_USER_WITH_LOGIN.getValue());
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when banning user " + userLogin + " :" + e.getMessage(), e);
		}
	}

	@Override
	public BanUnbanUserResultInfo unbanUser(String userLogin) throws ServiceException {
		BanUnbanUserResultInfo result = new BanUnbanUserResultInfo();
		try {
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			if(validationService.validateLogin(userLogin)) {
				result.setLoginCorrect(true);
			} else {
				result.setLoginCorrect(false);
				result.setLoginErrorInfo(ErrorFeedback.BAN_UNBAN_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN.getValue());
			}
			if (result.isLoginCorrect()) {
				Optional<User> user = userDao.findByLogin(userLogin);
				if (user.isPresent()) {
					ServiceProvider serviceProvider = ServiceProvider.getInstance();
					TimeService timeService = serviceProvider.getTimeService();
					String unbanDate = timeService.prepareBanDate(0);
					boolean unbanUserResult = userDao.unbanUser(userLogin, unbanDate);
					if (unbanUserResult) {
						result.setLoginCorrect(true);
					} else {
						result.setLoginCorrect(false);
						result.setLoginErrorInfo(ErrorFeedback.BAN_UNBAN_USER_STANDARD_LOGIN_FEEDBACK.getValue());
					}
				} else {
					result.setLoginCorrect(false);
					result.setLoginErrorInfo(ErrorFeedback.NO_USER_WITH_LOGIN.getValue());
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when unbanning user " + userLogin + " :" + e.getMessage(), e);
		}
	}

	@Override
	public PromoteDemoteUserResultInfo promoteUser(String userLogin, String currentUserLogin) throws ServiceException {
		try {
			PromoteDemoteUserResultInfo result = new PromoteDemoteUserResultInfo();
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			if (validationService.validateLogin(userLogin)) {
				result.setLoginCorrect(true);
			} else {
				result.setLoginCorrect(false);
				result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN.getValue());
			}
			if (result.isLoginCorrect()) {
				if (!userLogin.equals(currentUserLogin)) {
					Optional<User> user = userDao.findByLogin(userLogin);
					if (user.isPresent() && user.get().getRole() == Role.USER) {
						boolean promotingResult = userDao.promoteUser(userLogin);
						if (promotingResult) {
							result.setLoginCorrect(true);
						} else {
							result.setLoginCorrect(false);
							result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_STANDARD_LOGIN_FEEDBACK.getValue());
						}
					} else {
						result.setLoginCorrect(false);
						result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_CAN_NOT_FIND_USER_FOR_PROMOTING.getValue());
					}
				} else {
					result.setLoginCorrect(false);
					result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_PROMOTE_YOURSELF.getValue());
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when promoting user " + userLogin + " :" + e.getMessage(), e);
		}
	}
	
	@Override
	public PromoteDemoteUserResultInfo demoteAdmin(String adminLogin, String currentAdminLogin) throws ServiceException {
		try {
			PromoteDemoteUserResultInfo result = new PromoteDemoteUserResultInfo();
			ValidationService validationService = ServiceProvider.getInstance().getValidationService();
			if (validationService.validateLogin(adminLogin)) {
				result.setLoginCorrect(true);
			} else {
				result.setLoginCorrect(false);
				result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN.getValue());
			}
			if (result.isLoginCorrect()) {
				if (!adminLogin.equals(currentAdminLogin)) {
					Optional<User> user = userDao.findByLogin(adminLogin);
					if (user.isPresent() && user.get().getRole() == Role.ADMIN) {
						boolean demotingResult = userDao.demoteAdmin(adminLogin);
						if (demotingResult) {
							result.setLoginCorrect(true);
						} else {
							result.setLoginCorrect(false);
							result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_STANDARD_LOGIN_FEEDBACK.getValue());
						}
					} else {
						result.setLoginCorrect(false);
						result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_CAN_NOT_FIND_ADMIN_FOR_DEMOTING.getValue());
					}
				} else {
					result.setLoginCorrect(false);
					result.setLoginErrorInfo(ErrorFeedback.PROMOTE_DEMOTE_USER_RESULT_INFO_FEEDBACK_INVALID_LOGIN_DEMOTE_YOURSELF.getValue());
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Error occured when demoting admin " + adminLogin + " :" + e.getMessage(), e);
		}
	}

}

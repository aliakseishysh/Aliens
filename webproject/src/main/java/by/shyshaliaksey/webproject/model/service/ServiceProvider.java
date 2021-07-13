package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.model.service.impl.AdminServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.AlienServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.RatingServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.TimeServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.UserServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.UtilServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.ValidationServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private static final AlienService alienService = new AlienServiceImpl();
	private static final RatingService ratingService = new RatingServiceImpl();
	private static final UserService userService = new UserServiceImpl();
	private static final AdminService adminService = new AdminServiceImpl();
	private static final TimeService timeService = new TimeServiceImpl();
	private static final ValidationService validationService = new ValidationServiceImpl();
	private static final UtilService utilService = new UtilServiceImpl();
	
	private ServiceProvider() {
	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}

	public AlienService getAlienService() {
		return alienService;
	}

	public RatingService getRatingService() {
		return ratingService;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}
	
	public TimeService getTimeService() {
		return timeService;
	}
	
	public ValidationService getValidationService() {
		return validationService;
	}
	
	public UtilService getUtilService() {
		return utilService;
	}
}

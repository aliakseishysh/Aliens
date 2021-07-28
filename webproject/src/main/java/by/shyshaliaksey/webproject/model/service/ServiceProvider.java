package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.model.service.impl.AdminServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.AlienServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.RatingServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.UserServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.ValidationServiceImpl;

/**
 * Provider for different service layer objects
 * 
 * @author Aliaksey Shysh
 *
 */
public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private static final AlienService alienService = new AlienServiceImpl();
	private static final RatingService ratingService = new RatingServiceImpl();
	private static final UserService userService = new UserServiceImpl();
	private static final AdminService adminService = new AdminServiceImpl();
	private static final ValidationService validationService = new ValidationServiceImpl();

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

	public ValidationService getValidationService() {
		return validationService;
	}

}

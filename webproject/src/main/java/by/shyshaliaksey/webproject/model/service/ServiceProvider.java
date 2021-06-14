package by.shyshaliaksey.webproject.model.service;

import by.shyshaliaksey.webproject.model.service.impl.AlienServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.RatingServiceImpl;
import by.shyshaliaksey.webproject.model.service.impl.UserServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private static final AlienService alienService = new AlienServiceImpl();
	private static final RatingService ratingService = new RatingServiceImpl();
	private static final UserService userService = new UserServiceImpl();
	
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
	
}

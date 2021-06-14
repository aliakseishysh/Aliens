package by.shyshaliaksey.webproject.model.dao;

import by.shyshaliaksey.webproject.model.dao.impl.AlienDaoImpl;
import by.shyshaliaksey.webproject.model.dao.impl.RatingDaoImpl;
import by.shyshaliaksey.webproject.model.dao.impl.UserDaoImpl;

public class DaoProvider {

	private static final DaoProvider instance = new DaoProvider();
	private static final UserDao userDao = UserDaoImpl.getInstance();
	private static final AlienDao alienDao = AlienDaoImpl.getInstance();
	private static final RatingDao ratingDao = RatingDaoImpl.getInstance();
	
	private DaoProvider() {
	}
	
	public static DaoProvider getInstance() {
		return instance;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public AlienDao getAlienDao() {
		return alienDao;
	}
	
	public RatingDao getRatingDao() {
		return ratingDao;
	}
}

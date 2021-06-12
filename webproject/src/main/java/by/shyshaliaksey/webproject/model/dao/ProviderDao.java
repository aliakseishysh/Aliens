package by.shyshaliaksey.webproject.model.dao;

import by.shyshaliaksey.webproject.model.dao.impl.AlienDaoImpl;
import by.shyshaliaksey.webproject.model.dao.impl.RatingDaoImpl;
import by.shyshaliaksey.webproject.model.dao.impl.UserDaoImpl;

public class ProviderDao {

	private static final ProviderDao instance = new ProviderDao();
	private static final UserDao userDao = UserDaoImpl.getInstance();
	private static final AlienDao alienDao = AlienDaoImpl.getInstance();
	private static final RatingDao ratingDao = RatingDaoImpl.getInstance();
	
	private ProviderDao() {
	}
	
	public static ProviderDao getInstance() {
		return instance;
	}
	
	public static UserDao getUserDao() {
		return userDao;
	}
	
	public static AlienDao getAlienDao() {
		return alienDao;
	}
	
	public static RatingDao getRatingDao() {
		return ratingDao;
	}
}

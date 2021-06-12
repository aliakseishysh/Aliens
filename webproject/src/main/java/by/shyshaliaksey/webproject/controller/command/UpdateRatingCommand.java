package by.shyshaliaksey.webproject.controller.command;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.dao.RatingDao;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateRatingCommand extends Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rateValue = Integer.parseInt(request.getParameter("ratingValue"));
		String alienName = request.getParameter("alienName");
		AbstractUser user = (AbstractUser) request.getSession().getAttribute("currentUser");
		
		int alienId = getAlienIdByName(alienName);
		
		if (checkRateExistence(alienId, user.getId())) {
			updateRateToAlien(alienId, user.getId(), rateValue);
		} else {
			addRateToAlien(alienId, user.getId(), rateValue);
		}
		Double averageRate = calculateAlienAverageRate(alienId);
		try {
			response.getWriter().write(averageRate.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.ERROR, "Exception occured while sending response to `{}`: {}", FilePath.ALIEN_PROFILE_JSP, e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
		
	}
	
	private int getAlienIdByName(String alienName) {
		AlienDao alienDao = ProviderDao.getAlienDao();
		try {
			Optional<Alien> currentAlien = alienDao.findByName(alienName);
			if (currentAlien.isPresent()) {
				int alienId = currentAlien.get().getId();
				return alienId;
			} else {
				logger.log(Level.ERROR, "Alien is not present in table `aliens`");
				// TODO redirect to error page
				throw new UnsupportedOperationException();
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while alien id search: {}", e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
	}
	
	private void addRateToAlien(int alienId, int userId, int rateValue) {
		RatingDao ratingDao = ProviderDao.getRatingDao();
		try {
			ratingDao.addRate(alienId, userId, rateValue);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while adding alien rate: {}", e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
	}
	
	private void updateRateToAlien(int alienId, int userId, int rateValue) {
		RatingDao ratingDao = ProviderDao.getRatingDao();
		try {
			ratingDao.updateRate(alienId, userId, rateValue);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while adding alien rate: {}", e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
	}
	
	private double calculateAlienAverageRate(int alienId) {
		RatingDao ratingDao = ProviderDao.getRatingDao();
		try {
			double averageRate = ratingDao.calculateAverageRating(alienId);
			return averageRate;
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while calculating average alien rate: {}", e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException(e.getMessage());
		}
	}
	
	private boolean checkRateExistence(int alienId, int userId) {
		RatingDao ratingDao = ProviderDao.getRatingDao();
		try {
			boolean result = ratingDao.checkRateExistence(alienId, userId);
			return result;
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while calculating average alien rate: {}", e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException(e.getMessage());
		}
	}

}

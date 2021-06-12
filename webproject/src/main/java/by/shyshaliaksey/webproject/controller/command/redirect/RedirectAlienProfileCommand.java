package by.shyshaliaksey.webproject.controller.command.redirect;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.command.Command;
import by.shyshaliaksey.webproject.controller.command.FilePath;
import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.dao.RatingDao;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectAlienProfileCommand extends Command {

	private static final Logger logger = LogManager.getRootLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int alienId = Integer.parseInt(request.getParameter("alien_id"));
		try {
			AlienDao alienDao = ProviderDao.getAlienDao();
			RatingDao ratingDao = ProviderDao.getRatingDao();
			Optional<Alien> alienOptional = alienDao.findById(alienId);
			if (alienOptional.isPresent()) {
				Alien alien = alienOptional.get();
				request.setAttribute("alien", alien);
				double averageRating = calculateAlienAverageRate(ratingDao, alien.getId());
				request.setAttribute("averageRating", averageRating);
				super.redirect(request, response, FilePath.ALIEN_PROFILE_JSP);
			} else {
				logger.log(Level.INFO, "No alien with id: {}", alienId);
			}
			
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while alien searching with id {}: {}", alienId, e.getMessage());
			// TODO redirect to error page
		}
		
	}
	
	private double calculateAlienAverageRate(RatingDao ratingDao, int alienId) {
		try {
			double averageRate = ratingDao.calculateAverageRating(alienId);
			return averageRate;
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Exception occured while calculating average alien rate: {}", e.getMessage());
			// TODO redirect to error page
			throw new UnsupportedOperationException(e.getMessage());
		}
	}

}

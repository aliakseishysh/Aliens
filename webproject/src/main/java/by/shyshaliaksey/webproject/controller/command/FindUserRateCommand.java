package by.shyshaliaksey.webproject.controller.command;

import java.io.IOException;
import java.util.Optional;

import by.shyshaliaksey.webproject.exception.DaoException;
import by.shyshaliaksey.webproject.model.dao.AlienDao;
import by.shyshaliaksey.webproject.model.dao.ProviderDao;
import by.shyshaliaksey.webproject.model.dao.RatingDao;
import by.shyshaliaksey.webproject.model.entity.AbstractUser;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindUserRateCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String alienName = request.getParameter("alienName");
		AbstractUser user = (AbstractUser) request.getSession().getAttribute("currentUser");
		int alienId = findAlienId(alienName);
		int userId = user.getId();
		Integer userRate = findUserRate(userId, alienId);
		try {
			response.getWriter().write(userRate.toString());
		} catch (IOException e) {
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
	}
	
	private int findUserRate(int userId, int alienId) {
		RatingDao ratingDao = ProviderDao.getRatingDao();
		try {
			int userRate = ratingDao.findUserRate(alienId, userId);
			return userRate;
		} catch (DaoException e) {
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
	}
	
	private int findAlienId(String alienName) {
		AlienDao alienDao = ProviderDao.getAlienDao();
		try {
			Optional<Alien> alienOptional = alienDao.findByName(alienName);
			if (alienOptional.isPresent()) {
				Alien alien = alienOptional.get();
				int alienId = alien.getId();
				return alienId;
			} else {
				// TODO redirect to error page
				throw new UnsupportedOperationException();
			}
		} catch (DaoException e) {
			// TODO redirect to error page
			throw new UnsupportedOperationException();
		}
	}

}

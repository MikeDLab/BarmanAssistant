package com.labutin.barman.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.RatingRepository;
import com.labutin.barman.specification.rating.FindAverageBarmanRatingSet;
import com.labutin.barman.specification.rating.FindAverageCocktailRatingSet;
import com.labutin.barman.specification.rating.FindBarmanRatingSetByUserId;
import com.labutin.barman.specification.rating.FindCocktailRatingSetByUserId;
import com.labutin.barman.specification.rating.FindCocktailRatingSetByUserIdAndCocktailId;

public class RatingService {
	private static Logger logger = LogManager.getLogger();
	private final RatingRepository utilRepository;

	public RatingService() throws ServiceException {
		try {
			utilRepository = RatingRepository.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveBarmanRatingSetByUserId(int userId) throws ServiceException {
		try {
			return utilRepository.query(new FindBarmanRatingSetByUserId(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveBarmanRatingSetByBarmanId(int userId) throws ServiceException {
		try {
			return utilRepository.query(new FindAverageBarmanRatingSet(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException();
		}
	}

	public Rating receiveCocktailRatingSetByUserIdAndCocktailId(int userId, int cocktailId) throws ServiceException {
		try {
			if (utilRepository.query(new FindCocktailRatingSetByUserIdAndCocktailId(userId, cocktailId)).iterator()
					.hasNext()) {
				return utilRepository.query(new FindCocktailRatingSetByUserIdAndCocktailId(userId, cocktailId))
						.iterator().next();
			} else {
				return null;
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTTT");
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveCocktailRatingSetByUserId(int userId) throws ServiceException {
		try {
			return utilRepository.query(new FindCocktailRatingSetByUserId(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveCocktailRatingSetByCocktailId(int cocktailId) throws ServiceException {
		try {
			return utilRepository.query(new FindAverageCocktailRatingSet(cocktailId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void removeCocktailRating(int cocktailId) throws ServiceException {

		try {
			Cocktail cocktail = new Cocktail();
			cocktail.setCocktailId(cocktailId);
			utilRepository.removeCocktailRating(cocktail);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}

package com.labutin.barman.service;

import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.RatingRepository;
import com.labutin.barman.specification.rating.FindAverageBarmanRatingSet;
import com.labutin.barman.specification.rating.FindAverageCocktailRatingSet;
import com.labutin.barman.specification.rating.FindBarmanRatingSetByUserId;
import com.labutin.barman.specification.rating.FindCocktailRatingSetByUserId;
import com.labutin.barman.specification.rating.FindCocktailRatingSetByUserIdAndCocktailId;

public class RatingService {
	private static RatingService INSTANCE;
	private final RatingRepository ratingRepository;
	private RatingService() throws ServiceException {
		try {
			ratingRepository = RatingRepository.getInstance();
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
	public static RatingService getInstance() throws ServiceException
	{
		return (INSTANCE == null) ? INSTANCE = new RatingService() : INSTANCE;
	}
	public Set<Rating> receiveBarmanRatingSetByBarmanId(int userId) throws ServiceException {
		try {
			return ratingRepository.query(new FindAverageBarmanRatingSet(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveBarmanRatingSetByUserId(int userId) throws ServiceException {
		try {
			return ratingRepository.query(new FindBarmanRatingSetByUserId(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveCocktailRatingSetByCocktailId(int cocktailId) throws ServiceException {
		try {
			return ratingRepository.query(new FindAverageCocktailRatingSet(cocktailId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Rating> receiveCocktailRatingSetByUserId(int userId) throws ServiceException {
		try {
			return ratingRepository.query(new FindCocktailRatingSetByUserId(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Rating receiveCocktailRatingSetByUserIdAndCocktailId(int userId, int cocktailId) throws ServiceException {
		try {
			Set<Rating> setRating = ratingRepository.query(new FindCocktailRatingSetByUserIdAndCocktailId(userId, cocktailId));
			if (setRating.iterator().hasNext()) {
				return setRating.iterator().next();
			} else {
				return null;
			}
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCocktailRating(int cocktailId) throws ServiceException {

		try {
			ratingRepository.removeCocktailRating(cocktailId);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}

package com.labutin.barman.service;

import java.io.InputStream;
import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.CocktailRepositoryImpl;
import com.labutin.barman.repository.RatingRepository;
import com.labutin.barman.specification.cocktail.FindCocktailById;
import com.labutin.barman.specification.cocktail.FindCocktailForBarmenAccept;
import com.labutin.barman.specification.cocktail.FindCocktailPublishedSet;
import com.labutin.barman.specification.cocktail.FindCocktailSetByBarmanId;

public class CocktailService {
	private static CocktailService instance;
	private final CocktailRepositoryImpl cocktailRepository;
	private final RatingRepository ratingRepository;

	private CocktailService() {
		cocktailRepository = CocktailRepositoryImpl.getInstance();
		ratingRepository = RatingRepository.getInstance();
	}

	public static CocktailService getInstance() {
		return (instance == null) ? instance = new CocktailService() : instance;
	}

	public Cocktail add(String cocktailName, int userId, String cocktailDescription, int cocktailVol,
			boolean isPublished, InputStream imageStream) throws ServiceException {
		try {
			Cocktail cocktail = new Cocktail();
			cocktail.setCocktailName(cocktailName);
			cocktail.setCocktailDescription(cocktailDescription);
			cocktail.setUserId(userId);
			cocktail.setCocktailVol(cocktailVol);
			cocktail.setIsPublished(isPublished);
			cocktail.setImage(imageStream);
			cocktailRepository.add(cocktail);
			return cocktail;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}

	}

	public void addCocktailRating(int cocktailRating, int cocktailId, int userId) throws ServiceException {

		try {
			Rating rating = new Rating(userId, cocktailId, cocktailRating);
			ratingRepository.addCocktailRating(rating);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void addImage(int cocktailId, InputStream image) throws ServiceException {
		try {
			cocktailRepository.addImage(cocktailId, image);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void publishCocktail(int cocktailId) throws ServiceException {
		try {
			cocktailRepository.setPublished(cocktailId);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Cocktail receiveCocktailById(int cocktailId) throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailById(cocktailId)).iterator().next();
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Cocktail> receiveCocktailSetByUserId(int userId) throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailSetByBarmanId(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Cocktail> receiveNotPublishedCocktail() throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailForBarmenAccept());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Cocktail> receivePublishedCocktail() throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailPublishedSet());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCocktail(int cocktailId) throws ServiceException {
		try {
			cocktailRepository.remove(cocktailId);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}

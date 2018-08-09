package com.labutin.barman.service;

import java.io.InputStream;
import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.CocktailRepositoryImpl;
import com.labutin.barman.repository.RatingRepository;
import com.labutin.barman.specification.cocktail.FindCocktailById;
import com.labutin.barman.specification.cocktail.FindCocktailForBarmenAccept;
import com.labutin.barman.specification.cocktail.FindCocktailPublishedSet;

public class CocktailService {
	private final CocktailRepositoryImpl cocktailRepository;
	private final RatingRepository ratingRepository;
	public CocktailService() throws ServiceException {
		try {
			cocktailRepository = CocktailRepositoryImpl.getInstance();
			ratingRepository = RatingRepository.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void addImage(int cocktailId, InputStream inputStream) throws ServiceException {
		try {
			cocktailRepository.addImage(cocktailId, inputStream);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
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
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}

	}
	public void addCocktailRating(int cocktailRating, int cocktailId, int userId) throws ServiceException
	{
		
		try {
			Rating rating = new Rating(userId, cocktailId, cocktailRating);
			ratingRepository.addCocktailRating(rating);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	public Set<Cocktail> receivePublishedCocktail() throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailPublishedSet());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Set<Cocktail> receiveNotPublishedCocktail() throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailForBarmenAccept());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Cocktail receiveCocktailById(int cocktailId) throws ServiceException {
		try {
			return cocktailRepository.query(new FindCocktailById(cocktailId)).iterator().next();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void publishCocktail(int cocktailId) throws ServiceException {
		try {
			cocktailRepository.setPublished(cocktailId);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void removeCocktail(int cocktailId) throws ServiceException {
		try {
			Cocktail item = new Cocktail();
			item.setCocktailId(cocktailId);
			cocktailRepository.remove(item);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}

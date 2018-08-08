package com.labutin.barman.service;

import java.io.InputStream;
import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.CocktailRepositoryImpl;
import com.labutin.barman.repository.IngredientRepositoryImpl;
import com.labutin.barman.repository.UserRepositoryImpl;
import com.labutin.barman.specification.cocktail.FindCocktailById;
import com.labutin.barman.specification.cocktail.FindCocktailForBarmenAccept;
import com.labutin.barman.specification.cocktail.FindCocktailPublishedSet;
import com.labutin.barman.specification.ingredient.FindIngredientSet;

public class CocktailService {
	private final CocktailRepositoryImpl cocktailRepository;

	public CocktailService() throws ServiceException {
		try {
			cocktailRepository = CocktailRepositoryImpl.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	public void addImage(int cocktailId,InputStream inputStream)
	{
		cocktailRepository.addImage(cocktailId, inputStream);
	}
	public Cocktail add(String cocktailName, int userId, String cocktailDescription, int cocktailVol, boolean isPublished,InputStream imageStream) {
	Cocktail cocktail = new Cocktail();
	cocktail.setCocktailName(cocktailName);
	cocktail.setCocktailDescription(cocktailDescription);
	cocktail.setUserId(userId);
	cocktail.setCocktailVol(cocktailVol);
	cocktail.setIsPublished(isPublished);
	cocktail.setImage(imageStream);
	cocktailRepository.add(cocktail);
	return cocktail;
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
	public void publishCocktail(int cocktailId)
	{
		cocktailRepository.setPublished(cocktailId);
	}
	public void removeCocktail(int cocktailId)
	{
		Cocktail item = new Cocktail();
		item.setCocktailId(cocktailId);
		cocktailRepository.remove(item);
	}

}

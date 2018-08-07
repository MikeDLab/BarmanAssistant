package com.labutin.barman.service;

import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.IngredientRepositoryImpl;
import com.labutin.barman.repository.UserRepositoryImpl;
import com.labutin.barman.specification.ingredient.AddIngredientHasCocktail;
import com.labutin.barman.specification.ingredient.DeleteIngredientByCocktailId;
import com.labutin.barman.specification.ingredient.FindIngredientSet;
import com.labutin.barman.specification.ingredient.FindIngredientSetByCocktailId;

public class IngredientService {
	private final IngredientRepositoryImpl ingredientRepository;

	public IngredientService() throws ServiceException{
		try {
			ingredientRepository = IngredientRepositoryImpl.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			throw new ServiceException(e);
		}
	}

	public Ingredient add(String ingredientName, String ingredientDescription) throws ServiceException {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName(ingredientName);
		ingredient.setIngredientDescription(ingredientDescription);
		try {
			ingredientRepository.add(ingredient);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
		return ingredient;
	}

	public Set<Ingredient> receiveIngredient() throws ServiceException {
		try {
			return ingredientRepository.query(new FindIngredientSet());
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	public void insertIngredientCocktail(int ingredientId,int cocktailId) throws ServiceException
	{
		try {
			ingredientRepository.query(new AddIngredientHasCocktail(ingredientId, cocktailId));
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	public Set<Ingredient> receiveIngredientByCocktailId(int cocktailId) throws ServiceException
	{
		try {
			return ingredientRepository.query(new FindIngredientSetByCocktailId(cocktailId));
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	public void removeIngredient(int cocktailId) throws ServiceException
	{
		try {
			ingredientRepository.query(new DeleteIngredientByCocktailId(cocktailId));
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

}

package com.labutin.barman.service;

import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.IngredientRepositoryImpl;
import com.labutin.barman.specification.ingredient.AddIngredientHasCocktail;
import com.labutin.barman.specification.ingredient.DeleteIngredientByCocktailId;
import com.labutin.barman.specification.ingredient.FindIngredientById;
import com.labutin.barman.specification.ingredient.FindIngredientSet;
import com.labutin.barman.specification.ingredient.FindIngredientSetByCocktailId;

public class IngredientService {
	private static IngredientService instance;
	private final IngredientRepositoryImpl ingredientRepository;

	public IngredientService() {
		ingredientRepository = IngredientRepositoryImpl.getInstance();
	}

	public static IngredientService getInstance() {
		return (instance == null) ? instance = new IngredientService() : instance;
	}

	public Ingredient add(String ingredientName, String ingredientDescription) throws ServiceException {
		try {
			Ingredient ingredient = new Ingredient();
			ingredient.setIngredientName(ingredientName);
			ingredient.setIngredientDescription(ingredientDescription);
			ingredientRepository.add(ingredient);
			return ingredient;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}

	}

	public void insertIngredientCocktail(int ingredientId, int cocktailId) throws ServiceException {
		try {
			ingredientRepository.query(new AddIngredientHasCocktail(ingredientId, cocktailId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Ingredient> receiveIngredienSet() throws ServiceException {
		try {
			return ingredientRepository.query(new FindIngredientSet());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<Ingredient> receiveIngredientByCocktailId(int cocktailId) throws ServiceException {
		try {
			return ingredientRepository.query(new FindIngredientSetByCocktailId(cocktailId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Ingredient receiveIngredientById(int ingredientId) throws ServiceException {
		try {
			Set<Ingredient> setIngredient = ingredientRepository.query(new FindIngredientById(ingredientId));
			if (setIngredient.iterator().hasNext()) {
				return setIngredient.iterator().next();
			} else {
				return null;
			}
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void removeIngredient(int cocktailId) throws ServiceException {
		try {
			ingredientRepository.query(new DeleteIngredientByCocktailId(cocktailId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void updateIngredient(int ingredientId, String ingredientName, String ingredientDescription)
			throws ServiceException {
		try {
			Ingredient item = new Ingredient();
			item.setIngredientId(ingredientId);
			item.setIngredientName(ingredientName);
			item.setIngredientDescription(ingredientDescription);
			ingredientRepository.update(item);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}

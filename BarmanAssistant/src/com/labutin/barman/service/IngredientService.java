package com.labutin.barman.service;

import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.repository.IngredientRepository;
import com.labutin.barman.repository.UserRepository;
import com.labutin.barman.specification.ingredient.AddIngredientHasCocktail;
import com.labutin.barman.specification.ingredient.FindIngredientSet;
import com.labutin.barman.specification.ingredient.FindIngredientSetByCocktailId;

public class IngredientService {
	private final IngredientRepository ingredientRepository;

	public IngredientService() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		ingredientRepository = IngredientRepository.getInstance();
	}

	public Ingredient add(String ingredientName, String ingredientDescription) {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName(ingredientName);
		ingredient.setIngredientDescription(ingredientDescription);
		ingredientRepository.add(ingredient);
		return ingredient;
	}

	public Set<Ingredient> receiveIngredient() {
		return ingredientRepository.query(new FindIngredientSet());
	}
	public void insertIngredientCocktail(int ingredientId,int cocktailId)
	{
		ingredientRepository.query(new AddIngredientHasCocktail(ingredientId, cocktailId));
	}
	public Set<Ingredient> receiveIngredientByCocktailId(int cocktailId)
	{
		return ingredientRepository.query(new FindIngredientSetByCocktailId(cocktailId));
	}

}

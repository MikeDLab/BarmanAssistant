package com.labutin.barman.service;

import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.repository.CocktailRepository;
import com.labutin.barman.repository.IngredientRepository;
import com.labutin.barman.repository.UserRepository;
import com.labutin.barman.specification.cocktail.FindCocktailById;
import com.labutin.barman.specification.cocktail.FindCocktailSet;
import com.labutin.barman.specification.ingredient.FindIngredientSet;

public class CocktailService {
	private final CocktailRepository cocktailRepository;

	public CocktailService() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		cocktailRepository = CocktailRepository.getInstance();
	}
	public Cocktail add(String cocktailName, int userId, String cocktailDescription, int cocktailVol, boolean isPublished) {
	Cocktail cocktail = new Cocktail();
	cocktail.setCocktailName(cocktailName);
	cocktail.setCocktailDescription(cocktailDescription);
	cocktail.setUserId(userId);
	cocktail.setCocktailVol(cocktailVol);
	cocktail.setIsPublished(isPublished);
	cocktailRepository.add(cocktail);
	return cocktail;
}
//	public Ingredient add(String ingredientName, String ingredientDescription) {
//		Ingredient ingredient = new Ingredient();
//		ingredient.setIngredientName(ingredientName);
//		ingredient.setIngredientDescription(ingredientDescription);
//		ingredientRepository.add(ingredient);
//		return ingredient;
//	}
//
	public Set<Cocktail> receiveCocktail() {
		return cocktailRepository.query(new FindCocktailSet());
	}
	public Cocktail receiveCocktailById(int cocktailId) {
		return cocktailRepository.query(new FindCocktailById(cocktailId)).iterator().next();
	}

}

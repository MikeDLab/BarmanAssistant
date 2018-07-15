package com.labutin.barman.command;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.repository.IngredientRepository;
import com.labutin.barman.repository.UserRepository;

public class IngredientReceiver {
	private final IngredientRepository ingredientRepository;
	public IngredientReceiver() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		 ingredientRepository = IngredientRepository.getInstance();
	}
//	public User registration(String userLogin, String userName, String userPassword, String userEmail) throws AddUserException {
//		User user = new User();
//		user.setUserLogin(userLogin);
//		user.setUserName(userName);
//		user.setUserEmail(userEmail);
//		user.setUserPassword(userPassword);
//		ingredientRepository.add(user);
//		return user;
//	}
	public Ingredient add(String ingredientName,String ingredientDescription)
	{
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName(ingredientName);
		ingredient.setIngredientDescription(ingredientDescription);
		ingredientRepository.add(ingredient);
		return ingredient;
	}
//	public User login(String login,String password)
//	{
//		User user = new User();
//		user.setName(login);
//		user.setPassword(password);
//		user = userRepository.query(new FindUserByLogin(login)).iterator().next();
//		return user;	
//	}
}

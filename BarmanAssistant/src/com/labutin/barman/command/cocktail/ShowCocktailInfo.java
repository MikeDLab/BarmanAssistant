package com.labutin.barman.command.cocktail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

public class ShowCocktailInfo implements Command {
	private CocktailService receiverCocktail;
	private UserService receiverUser;
	private IngredientService receiverIngredient;

	public ShowCocktailInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		int cocktailId = Integer.parseInt(request.getParameter("cocktail_id"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		try {
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.receiveCocktailById(cocktailId);
			receiverUser = new UserService();
			User user = receiverUser.receiveUserById(userId);
			receiverIngredient = new IngredientService();
			Set<Ingredient> setIngredient = receiverIngredient.receiveIngredientByCocktailId(cocktailId);
			request.setAttribute("setIngredient", setIngredient);
			request.setAttribute("user", user);
			request.setAttribute("cocktail", cocktail);

		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return PageEnum.COCKTAIL_INFO_PAGE;

	}

}

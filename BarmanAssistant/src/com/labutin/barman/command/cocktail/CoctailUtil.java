package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

class CoctailUtil {
	private CocktailService receiver;
	private IngredientService receiverIngredient;
	private UserService receiverUser;
	private ImageUtil imageUtil = new ImageUtil();
	public CoctailUtil() {
		// TODO Auto-generated constructor stub
	}
	void addCocktail(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
}

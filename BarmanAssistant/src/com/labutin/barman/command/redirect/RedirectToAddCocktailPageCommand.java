package com.labutin.barman.command.redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.ingredient.IngredientCommand;

public class RedirectToAddCocktailPageCommand extends IngredientCommand {
	public RedirectToAddCocktailPageCommand() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.showIngredientSet(request, response);
		return PageEnum.ADD_COCKTAIL;
	}

}

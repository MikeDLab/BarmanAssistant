package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class ShowNotPublishedCocktailListCommand extends CocktailCommand {
	public ShowNotPublishedCocktailListCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.init(request, response);
		util.showNotPublishedCocktail(request, response);
		return PageEnum.COCKTAIL_LIST_FOR_BARMAN;

	}

}

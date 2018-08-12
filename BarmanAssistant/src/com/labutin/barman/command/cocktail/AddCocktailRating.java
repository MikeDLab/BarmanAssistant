package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class AddCocktailRating extends CocktailCommand {
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.addCocktailRating(request, response);
		util.showCocktailInfo(request, response);
		return PageEnum.COCKTAIL_INFO_PAGE;
	}

}

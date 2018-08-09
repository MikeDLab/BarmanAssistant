package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class PublishCocktail extends CocktailCommand {
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		util.publishCocktail(request, response);
		util.showNotPublishedCocktail(request, response);
		return PageEnum.COCKTAIL_LIST_FOR_BARMAN;
	}

}

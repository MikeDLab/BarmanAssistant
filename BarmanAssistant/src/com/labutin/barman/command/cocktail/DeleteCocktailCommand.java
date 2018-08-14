package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class DeleteCocktailCommand extends CocktailCommand {

	public DeleteCocktailCommand() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		util.init(request, response);
		util.deleteCocktail(request, response);
		util.showPublishedCocktail(request, response);
		return PageEnum.COCKTAIL_LIST_PAGE;
	}

}

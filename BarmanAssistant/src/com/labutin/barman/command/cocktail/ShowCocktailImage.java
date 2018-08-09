package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class ShowCocktailImage extends CocktailCommand {

	public ShowCocktailImage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.findCocktailImage(request, response);
		return null;
	}

}

package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class ShowCocktailImageCommand extends CocktailCommand {

	public ShowCocktailImageCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.init(request, response);
		util.findCocktailImage(request, response);
		return null;
	}

}

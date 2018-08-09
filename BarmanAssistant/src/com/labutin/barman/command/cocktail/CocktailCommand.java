package com.labutin.barman.command.cocktail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;

public abstract class CocktailCommand implements Command {
	protected CoctailUtil util = new CoctailUtil();

	public CocktailCommand() {
		// TODO Auto-generated constructor stub
	}

	public abstract PageEnum execute(HttpServletRequest request, HttpServletResponse response);

}

package com.labutin.barman.command.ingredient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;

public abstract class IngredientCommand implements Command {
	protected IngredientUtil util = new IngredientUtil();
	public IngredientCommand() {
		// TODO Auto-generated constructor stub
	}
	public abstract PageEnum execute(HttpServletRequest request,HttpServletResponse response);

}

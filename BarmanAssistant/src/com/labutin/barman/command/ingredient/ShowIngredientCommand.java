package com.labutin.barman.command.ingredient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class ShowIngredientCommand extends IngredientCommand {

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.init(request, response);
		util.showIngredientSet(request, response);
		return PageEnum.INGREDIENT_LIST;
	}

}

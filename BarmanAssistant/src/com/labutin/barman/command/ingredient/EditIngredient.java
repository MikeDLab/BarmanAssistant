package com.labutin.barman.command.ingredient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.labutin.barman.command.PageEnum;

public class EditIngredient extends IngredientCommand {
	// Service

	public EditIngredient() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.editIngredient(request, response);
		util.showIngredientSet(request, response);
		return PageEnum.INGREDIENT_LIST;
	}

}

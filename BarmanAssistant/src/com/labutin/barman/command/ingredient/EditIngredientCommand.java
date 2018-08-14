package com.labutin.barman.command.ingredient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.labutin.barman.command.PageEnum;

public class EditIngredientCommand extends IngredientCommand {
	// Service

	public EditIngredientCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.init(request, response);
		if(util.editIngredient(request, response))
		{
			util.showIngredientSet(request, response);
			return PageEnum.INGREDIENT_LIST;
		}
		else
		{
			return PageEnum.EDIT_INGREDIENT_PAGE;
		}
	
	}

}

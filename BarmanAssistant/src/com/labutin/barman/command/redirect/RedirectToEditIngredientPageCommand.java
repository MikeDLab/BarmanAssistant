package com.labutin.barman.command.redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Ingredient;

public class RedirectToEditIngredientPageCommand implements Command {
	public RedirectToEditIngredientPageCommand() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		int ingredientId = Integer.parseInt(request.getParameter("ingredient_id"));
		String ingredientName = request.getParameter("ingredient_name");
		String ingredientDescription = request.getParameter("ingredient_description");
		Ingredient ingredeint = new Ingredient(ingredientId,ingredientName,ingredientDescription);
		request.setAttribute("ingredient", ingredeint);
		return PageEnum.EDIT_INGREDIENT_PAGE;
	}

}

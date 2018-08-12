package com.labutin.barman.command.redirect;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.ingredient.IngredientCommand;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.IngredientService;

public class RedirectToAddCocktailPage extends IngredientCommand {
	public RedirectToAddCocktailPage() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		util.showIngredientSet(request, response);
		return PageEnum.ADD_COCKTAIL;
	}

}

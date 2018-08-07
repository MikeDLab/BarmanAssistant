package com.labutin.barman.command.cocktail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

public class DeleteCocktail implements Command {
	private static Logger logger = LogManager.getLogger();
	private CocktailService receiver;
	private IngredientService receiverIngredient;
	public DeleteCocktail() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int cocktailId = Integer.parseInt(request.getParameter("cocktail_id"));
		try {
			// TODO Auto-generated method stub
			receiverIngredient = new IngredientService();
			receiverIngredient.removeIngredient(cocktailId);
			receiver = new CocktailService();
			receiver.removeCocktail(cocktailId);
		}catch (ServiceException e) {
			// TODO: handle exception
		}

		return new ShowPublishedCocktailList().execute(request, response);
	}

}

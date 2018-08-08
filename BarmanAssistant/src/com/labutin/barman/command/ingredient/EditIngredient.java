package com.labutin.barman.command.ingredient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.validator.UserValidator;

public class EditIngredient implements Command {
	// Service
	private static Logger logger = LogManager.getLogger();
	private IngredientService receiver;

	public EditIngredient() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		int ingredientId = Integer.parseInt(request.getParameter("ingredient_id"));
		String ingredientName = request.getParameter(JspParameter.INGREDIENT_NAME.getValue());
		String ingredientDescription = request.getParameter(JspParameter.INGREDIENT_DESCRIPTION.getValue());
		// TODO Auto-generated method stub
		try {
			receiver = new IngredientService();
			receiver.updateIngredient(ingredientId, ingredientName, ingredientDescription);
			return new ShowIngredientCommand().execute(request, response);
		}catch (ServiceException e) {
			// TODO: handle exception
		}
		
		throw new RuntimeException();

	}

}

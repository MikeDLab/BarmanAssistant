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
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.validator.UserValidator;

public class AddIngredientCommand implements Command {
	// Service
	private static Logger logger = LogManager.getLogger();
	private IngredientService receiver;

	public AddIngredientCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		String ingredientName = request.getParameter(JspParameter.INGREDIENT_NAME.getValue());
		String ingredientDescription = request.getParameter(JspParameter.INGREDIENT_DESCRIPTION.getValue());
		// TODO Auto-generated method stub
		try {
			receiver = new IngredientService();
			logger.info("JDBC IS OK");
		} catch (NoJDBCDriverException e) {
			logger.info("No JDBC DRIVER");
			// TODO Auto-generated catch block
			// setAtribute
			// return ERROR_PAGE;
			e.printStackTrace();
		} catch (NoJDBCPropertiesFileException e) {
			logger.info("No Prop JDBC");
			// setAtribute
			// return ERROR_PAGE;
		}
		Ingredient ingredient;
		ingredient = receiver.add(ingredientName, ingredientDescription);
		if (ingredient != null) {
			// request.getSession().setAttribute(JspParameter.USER.getValue(), user);
			return PageEnum.HOME_PAGE;
		}
		throw new RuntimeException();

	}

}

package com.labutin.barman.command.ingredient;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.util.XssParser;

public class IngredientUtil {
	private IngredientService receiver;

	public IngredientUtil() {
		// TODO Auto-generated constructor stub
	}

	void addIngredient(HttpServletRequest request, HttpServletResponse response) {
		String ingredientName = XssParser.parse(request.getParameter(JspParameter.INGREDIENT_NAME.getValue()));
		String ingredientDescription = XssParser
				.parse(request.getParameter(JspParameter.INGREDIENT_DESCRIPTION.getValue()));
		try {
			receiver = new IngredientService();
			receiver.add(ingredientName, ingredientDescription);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
	}

	public void showIngredientSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new IngredientService();
			Set<Ingredient> setIngredient = receiver.receiveIngredient();
			request.setAttribute(JspParameter.INGREDIENT_SET.getValue(), setIngredient);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void editIngredient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int ingredientId = Integer.parseInt(request.getParameter(JspParameter.INGREDIENT_ID.getValue()));
			String ingredientName = XssParser.parse(request.getParameter(JspParameter.INGREDIENT_NAME.getValue()));
			String ingredientDescription = XssParser
					.parse(request.getParameter(JspParameter.INGREDIENT_DESCRIPTION.getValue()));
			receiver = new IngredientService();
			receiver.updateIngredient(ingredientId, ingredientName, ingredientDescription);
		} catch (ServiceException | NumberFormatException e) {
			// TODO: handle exception
		}

	}

}

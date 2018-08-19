package com.labutin.barman.command.ingredient;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.LocaleKey;
import com.labutin.barman.command.UtilCommand;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.util.XssParser;

public class IngredientUtil extends UtilCommand {
	private static final Logger logger = LogManager.getLogger();
	private IngredientService ingredientService;

	boolean addIngredient(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return false;
		}
		String ingredientName = request.getParameter(JspParameter.INGREDIENT_NAME.getValue());
		String ingredientDescription = request.getParameter(JspParameter.INGREDIENT_DESCRIPTION.getValue());
		if (ingredientName != null && ingredientDescription != null) {
			ingredientName = XssParser.parse(ingredientName);
			ingredientDescription = XssParser.parse(ingredientDescription);
			try {
				ingredientService = new IngredientService();
				ingredientService.add(ingredientName, ingredientDescription);
			} catch (ServiceException e) {
				logger.warn("Add ingredient exception", e);
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			}
			return true;
		}
		return false;

	}

	public void showIngredientSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			ingredientService = new IngredientService();
			Set<Ingredient> setIngredient = ingredientService.receiveIngredienSet();
			request.setAttribute(JspParameter.INGREDIENT_SET.getValue(), setIngredient);
		} catch (ServiceException e) {
			logger.warn("Show ingredient set exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	boolean editIngredient(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return false;
		}
		try {
			ingredientService = new IngredientService();
			String ingredientName = request.getParameter(JspParameter.INGREDIENT_NAME.getValue());
			int ingredientId = Integer.parseInt(request.getParameter(JspParameter.INGREDIENT_ID.getValue()));
			if (ingredientName.isEmpty()) {
				Ingredient ingredient = ingredientService.receiveIngredientById(ingredientId);
				request.setAttribute(JspParameter.INGREDIENT.getValue(), ingredient);
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.NO_INGREDIENT_NAME.getValue()));
				return false;
			}
			ingredientName = XssParser.parse(ingredientName);
			String ingredientDescription = XssParser
					.parse(request.getParameter(JspParameter.INGREDIENT_DESCRIPTION.getValue()));
			ingredientService.updateIngredient(ingredientId, ingredientName, ingredientDescription);
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Edit ingredient exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return false;
		}
		return true;

	}

}

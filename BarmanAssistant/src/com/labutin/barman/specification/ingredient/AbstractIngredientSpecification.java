package com.labutin.barman.specification.ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Ingredient;

public abstract class AbstractIngredientSpecification {
	private static Logger logger = LogManager.getLogger();
	protected static final String INGREDIENT_DESCRIPTION = "Ingredient_description";
	protected static final String INGREDIENT_ID = "Ingredient_id";
	protected static final String INGREDIENT_NAME = "Ingredient_name";
	protected ResultSet resultSet;

	public AbstractIngredientSpecification() {
	}

	protected void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.warn(e);
			}
		}
	}

	protected Ingredient loadIngredientData(ResultSet resultSet) {
		Ingredient ingredient = null;
		try {
			if (resultSet != null) {
				ingredient = new Ingredient();
				ingredient.setIngredientId(resultSet.getInt(INGREDIENT_ID));
				ingredient.setIngredientName(resultSet.getString(INGREDIENT_NAME));
				ingredient.setIngredientDescription(resultSet.getString(INGREDIENT_DESCRIPTION));
			}
		} catch (SQLException e) {
			logger.warn(e);
		}
		return ingredient;
	}
}

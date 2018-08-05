package com.labutin.barman.specification.ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.labutin.barman.entity.Ingredient;

public abstract class AbstractIngredientSpecification {
	protected ResultSet resultSet;
	protected final String INGREDIENT_ID="Ingredient_id";
	protected final String INGREDIENT_NAME = "Ingredient_name";
	protected final String INGREDIENT_DESCRIPTION = "Ingredient_description";
	public AbstractIngredientSpecification() {
		// TODO Auto-generated constructor stub
	}

	protected void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected Ingredient loadIngredientData(ResultSet resultSet)
	{
		Ingredient ingredient = null;
		try {
			if (resultSet != null)
			{
				ingredient = new Ingredient();
				ingredient.setIngredientId(resultSet.getInt(INGREDIENT_ID));
				ingredient.setIngredientName(resultSet.getString(INGREDIENT_NAME));
				ingredient.setIngredientDescription(resultSet.getString(INGREDIENT_DESCRIPTION));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return ingredient;
	}
}

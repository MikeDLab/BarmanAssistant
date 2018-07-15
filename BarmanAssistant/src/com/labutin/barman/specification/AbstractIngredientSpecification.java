package com.labutin.barman.specification;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.labutin.barman.entity.Ingredient;

public abstract class AbstractIngredientSpecification {
	protected ResultSet resultSet;

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
				ingredient.setIngredientId(resultSet.getInt("Ingredient_id"));
				ingredient.setIngredientName(resultSet.getString("Ingredient_name"));
				ingredient.setIngredientDescription(resultSet.getString("Ingredient_description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return ingredient;
	}
}

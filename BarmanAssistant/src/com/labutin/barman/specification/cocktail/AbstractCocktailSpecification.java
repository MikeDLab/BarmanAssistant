package com.labutin.barman.specification.cocktail;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;

public abstract class AbstractCocktailSpecification {
	protected ResultSet resultSet;

	public AbstractCocktailSpecification() {
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

	protected Cocktail loadCocktailData(ResultSet resultSet)
	{
		Cocktail cocktail = null;
		try {
			if (resultSet != null)
			{
				cocktail = new Cocktail();
				cocktail.setCocktailId(resultSet.getInt("cocktail_id"));
				cocktail.setCocktailName(resultSet.getString("cocktail_name"));
				cocktail.setCocktailDescription(resultSet.getString("cocktail_description"));
				cocktail.setUserId(resultSet.getInt("user_id"));
				cocktail.setCocktailVol(resultSet.getInt("cocktail_vol"));
				cocktail.setIsPublished(resultSet.getBoolean("cocktail_isPublished"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return cocktail;
	}
}

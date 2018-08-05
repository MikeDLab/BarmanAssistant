package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class AddIngredientHasCocktail extends AbstractIngredientSpecification implements IngredientSpecification {
	private int ingredientId;
	private int cocktailId;
	private final static String INSERT_INGREDIENT_AND_COCKTAIL = "INSERT INTO Ingredient_has_Cocktail(Ingredient_Ingredient_id,  Cocktail_cocktail_id) VALUES (?,?)";

	public AddIngredientHasCocktail(int ingredientId, int cocktailId) {
		// TODO Auto-generated constructor stubt
		this.cocktailId = cocktailId;
		this.ingredientId = ingredientId;
	}

	@Override
	public Set<Ingredient> querry() {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INGREDIENT_AND_COCKTAIL);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, ingredientId);
				preparedStatement.setInt(2, cocktailId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
		} finally {
			closeResultSet();
		}
		return null;
	}
}

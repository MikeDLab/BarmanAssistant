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
	private final static String INSERT_INGREDIENT_AND_COCKTAIL = "INSERT INTO Ingredient_has_cocktail(Ingredient_id,cocktail_id) VALUES (?,?)";

	public AddIngredientHasCocktail(int ingredientId, int cocktailId) {
		// TODO Auto-generated constructor stubt
		this.cocktailId = cocktailId;
		this.ingredientId = ingredientId;
	}

	@Override
	public Set<Ingredient> querry() {
		System.out.println("Trye to insert ingredient");
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INGREDIENT_AND_COCKTAIL);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, ingredientId);
				preparedStatement.setInt(2, cocktailId);
				preparedStatement.executeUpdate();
				System.out.println("ingredient id inserted");
			}
		} catch (SQLException e) {
			System.out.println("Sql exception in ingredient");
		} finally {
			closeResultSet();
		}
		return null;
	}
}

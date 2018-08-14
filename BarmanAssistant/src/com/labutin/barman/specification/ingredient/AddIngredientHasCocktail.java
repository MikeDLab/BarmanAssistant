package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class AddIngredientHasCocktail extends AbstractIngredientSpecification implements IngredientSpecification {
	private final static String INSERT_INGREDIENT_AND_COCKTAIL = "INSERT INTO Ingredient_has_cocktail(Ingredient_id,cocktail_id) VALUES (?,?)";
	private int cocktailId;
	private int ingredientId;

	public AddIngredientHasCocktail(int ingredientId, int cocktailId) {
		// TODO Auto-generated constructor stubt
		this.cocktailId = cocktailId;
		this.ingredientId = ingredientId;
	}

	@Override
	public Set<Ingredient> query() throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INGREDIENT_AND_COCKTAIL);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, ingredientId);
				preparedStatement.setInt(2, cocktailId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

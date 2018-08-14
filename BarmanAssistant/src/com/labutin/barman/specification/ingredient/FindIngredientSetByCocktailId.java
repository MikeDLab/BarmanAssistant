package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientSetByCocktailId extends AbstractIngredientSpecification implements IngredientSpecification {
	private final static String FIND_INGREDIENT_SET = "SELECT Ingredient_id, Ingredient_name, Ingredient_description FROM Ingredient INNER JOIN Ingredient_has_Cocktail USING(Ingredient_id) WHERE cocktail_id = ?";
	private int cocktailId;

	public FindIngredientSetByCocktailId(int cocktailId) {
		this.cocktailId = cocktailId;
	}

	@Override
	public Set<Ingredient> query() throws RepositoryException {
		Set<Ingredient> ingredients = new HashSet<>();

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_SET);) {

			if (preparedStatement != null) {
				preparedStatement.setInt(1, cocktailId);
				resultSet = preparedStatement.executeQuery();
			}
			while (resultSet.next()) {
				ingredients.add(loadIngredientData(resultSet));
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			closeResultSet();
		}
		return ingredients;
	}
}

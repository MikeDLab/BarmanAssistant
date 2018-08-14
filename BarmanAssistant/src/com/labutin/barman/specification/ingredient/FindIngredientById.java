package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientById extends AbstractIngredientSpecification implements IngredientSpecification {
	private final static String FIND_INGREDIENT_BY_NAME = "SELECT Ingredient_id,Ingredient_name,Ingredient_description FROM Ingredient WHERE Ingredient_id = ?";
	private int ingredientId;

	public FindIngredientById(int ingredientId) {
		// TODO Auto-generated constructor stubt
		this.ingredientId = ingredientId;
	}

	@Override
	public Set<Ingredient> query() throws RepositoryException {
		Set<Ingredient> ingredients = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_BY_NAME);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, ingredientId);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
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

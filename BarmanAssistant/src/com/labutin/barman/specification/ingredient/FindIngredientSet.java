package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;


import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientSet extends AbstractIngredientSpecification implements IngredientSpecification {
	private final static String FIND_INGREDIENT_SET = "SELECT Ingredient_id, Ingredient_name, Ingredient_description FROM Ingredient";

	public FindIngredientSet() {
	}

	@Override
	public Set<Ingredient> query() throws RepositoryException {
		Comparator<Ingredient> comparator = Comparator.comparing(obj -> obj.getIngredientId());
		TreeSet<Ingredient> ingredients = new TreeSet<>(comparator);
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_SET);) {
			if (preparedStatement != null) {
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

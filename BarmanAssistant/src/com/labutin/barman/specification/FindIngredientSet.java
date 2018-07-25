package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientSet extends AbstractIngredientSpecification implements IngredientSpecification {
	private final static String FIND_INGREDIENT_SET = "SELECT * FROM Ingredient";

	public FindIngredientSet() {
	}

	@Override
	public Set<Ingredient> querry() {
		Set<Ingredient> ingredients = new HashSet<>();
		ProxyConnection connection = null;
		try {
			connection = PoolConnection.POOL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_SET);
			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			}
			connection.close();
			while (resultSet.next()) {
				ingredients.add(loadIngredientData(resultSet));
			}
		} catch (SQLException e) {
		} finally {

			if (connection != null) {
				connection.close();
			}
			closeResultSet();
		}
		return ingredients;
	}
}

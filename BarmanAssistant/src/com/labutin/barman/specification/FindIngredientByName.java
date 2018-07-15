package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientByName extends AbstractIngredientSpecification implements IngredientSpecification {
	private String name;
	private final static String FIND_INGREDIENT_BY_NAME = "SELECT Ingredient_id,Ingredient_name,Ingredient_description FROM Ingredient WHERE Ingredient_name = ?";

	public FindIngredientByName(String name) {
		// TODO Auto-generated constructor stubt
		this.name = name;
	}

	@Override
	public Set<Ingredient> querry() {
		Set<Ingredient> ingredients = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_BY_NAME)) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, name);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
				ingredients.add(loadIngredientData(resultSet));
				return ingredients;
			}
		} catch (SQLException e) {
		} finally {
			closeResultSet();
		}
		return null;
	}
}

package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientByName extends AbstractIngredientSpecification implements IngredientSpecification {
	private static Logger logger = LogManager.getLogger();
	private String ingredientName;
	private final static String FIND_INGREDIENT_BY_NAME = "SELECT Ingredient_id,Ingredient_name,Ingredient_description FROM Ingredient WHERE Ingredient_name = ?";
	public FindIngredientByName(String name) {
		// TODO Auto-generated constructor stubt
		this.ingredientName = name;
	}

	@Override
	public Set<Ingredient> querry() {
		Set<Ingredient> ingredients = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_BY_NAME);) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, ingredientName);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
				ingredients.add(loadIngredientData(resultSet));
				return ingredients;
			}
		} catch (SQLException e) {
			logger.warn("Sql exception", e);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

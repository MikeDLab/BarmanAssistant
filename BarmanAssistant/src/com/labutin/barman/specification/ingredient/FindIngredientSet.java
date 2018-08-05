package com.labutin.barman.specification.ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindIngredientSet extends AbstractIngredientSpecification implements IngredientSpecification {
	private final static String FIND_INGREDIENT_SET = "SELECT Ingredient_id, Ingredient_name, Ingredient_description FROM Ingredient";
	private static Logger logger = LogManager.getLogger();

	public FindIngredientSet() {
	}

	@Override
	public Set<Ingredient> querry() {
		Set<Ingredient> ingredients = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_INGREDIENT_SET);) {
			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			}
			connection.close();
			while (resultSet.next()) {
				ingredients.add(loadIngredientData(resultSet));
			}
		} catch (SQLException e) {
			logger.info("Sqlexception", e);
		} finally {
			closeResultSet();
		}
		return ingredients;
	}
}

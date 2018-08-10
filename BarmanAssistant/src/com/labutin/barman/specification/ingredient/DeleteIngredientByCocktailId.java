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

public class DeleteIngredientByCocktailId extends AbstractIngredientSpecification implements IngredientSpecification {
	private static Logger logger = LogManager.getLogger();
	private int cocktailId;
	private final static String DELETE_INGREDIENT_BY_COCKTAIL_ID = "DELETE FROM Ingredient_has_cocktail WHERE cocktail_id = ?";
	public DeleteIngredientByCocktailId(int cocktailId) {
		// TODO Auto-generated constructor stubt
		this.cocktailId = cocktailId;
	}

	@Override
	public Set<Ingredient> querry() {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INGREDIENT_BY_COCKTAIL_ID);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, cocktailId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.warn("Sql exception", e);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

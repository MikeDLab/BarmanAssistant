package com.labutin.barman.specification.cocktail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindCocktailById extends AbstractCocktailSpecification implements CocktailSpecification {
	private final static String FIND_COCKTAIL_SET = "SELECT * FROM Cocktail WHERE cocktail_id=?";
	private static Logger logger = LogManager.getLogger();
	private int cocktailId;
	public FindCocktailById(int cocktailId) {
		this.cocktailId = cocktailId;
	}

	@Override
	public Set<Cocktail> querry() {
		Set<Cocktail> cocktails = new HashSet<>();
		ProxyConnection connection = null;
		try {
			connection = PoolConnection.POOL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_SET);
			if (preparedStatement != null) {
				preparedStatement.setInt(1, cocktailId);
				resultSet = preparedStatement.executeQuery();
			}
			connection.close();
			while (resultSet.next()) {
				cocktails.add(loadCocktailData(resultSet));
			}
		} catch (SQLException e) {
			logger.info("Sqlexception", e);
		} finally {

			if (connection != null) {
				connection.close();
			}
			closeResultSet();
		}
		return cocktails;
	}
}

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

public class FindCocktailSet extends AbstractCocktailSpecification implements CocktailSpecification {
	private final static String FIND_COCKTAIL_SET = "SELECT * FROM Cocktail";
	private static Logger logger = LogManager.getLogger();

	public FindCocktailSet() {
	}

	@Override
	public Set<Cocktail> querry() {
		Set<Cocktail> cocktails = new HashSet<>();
		ProxyConnection connection = null;
		try {
			connection = PoolConnection.POOL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_SET);
			if (preparedStatement != null) {
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

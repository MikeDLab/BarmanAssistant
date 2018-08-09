package com.labutin.barman.specification.cocktail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindCocktailPublishedSet extends AbstractCocktailSpecification implements CocktailSpecification {
	private final static String FIND_COCKTAIL_SET = "SELECT * FROM Cocktail Where cocktail_isPublished = 1";
	private static Logger logger = LogManager.getLogger();

	public FindCocktailPublishedSet() {
	}

	@Override
	public Set<Cocktail> querry() {
		Set<Cocktail> cocktails = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_SET);) {
			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			}
			while (resultSet.next()) {
				cocktails.add(loadCocktailData(resultSet));
			}
		} catch (SQLException e) {
			logger.info("Sqlexception", e);
		} finally {
			closeResultSet();
		}
		return cocktails;
	}
}
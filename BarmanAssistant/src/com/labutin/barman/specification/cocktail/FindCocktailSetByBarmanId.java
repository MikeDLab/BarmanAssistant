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

public class FindCocktailSetByBarmanId extends AbstractCocktailSpecification implements CocktailSpecification {
	private final static String FIND_COCKTAIL_SET = "SELECT cocktail_id,cocktail_name,user_id,cocktail_description,cocktail_vol,cocktail_isPublished,cocktail_img  From Cocktail Inner Join User USING(user_id) Where user_id = ?";
	private static Logger logger = LogManager.getLogger();
	private int userId;

	public FindCocktailSetByBarmanId(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<Cocktail> querry() {
		Set<Cocktail> cocktails = new HashSet<>();

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_SET);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
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

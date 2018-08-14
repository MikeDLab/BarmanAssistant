package com.labutin.barman.specification.cocktail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;



import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindCocktailForBarmenAccept extends AbstractCocktailSpecification implements CocktailSpecification {
	private final static String FIND_COCKTAIL_SET = "SELECT cocktail_id, cocktail_name, user_id, cocktail_description, cocktail_vol, cocktail_isPublished, cocktail_img FROM Cocktail Where cocktail_isPublished = 0";

	public FindCocktailForBarmenAccept() {
	}

	@Override
	public Set<Cocktail> query() throws RepositoryException {
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
			throw new RepositoryException(e);
		} finally {
			closeResultSet();
		}
		return cocktails;
	}
}

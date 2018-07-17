package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class AddCocktailRating extends AbstractUserSpecification implements CocktailSpecification {
	private int cocktailRating;
	private int userId;
	private int cocktailId;
	private final static String INSERT_COCKTAIL_RATING = "INSERT INTO CocktailRating(user_id, cocktail_id, cocktail_rating) VALUES (?,?,?)";

	public AddCocktailRating(int cocktailRating, int cocktailId, int userId) {
		// TODO Auto-generated constructor stubt
		this.cocktailRating = cocktailRating;
		this.cocktailId = cocktailId;
		this.userId = userId;
	}

	@Override
	public Set<Cocktail> querry() {
		Set<Cocktail> cocktail = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COCKTAIL_RATING)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, cocktailId);
				preparedStatement.setInt(3, cocktailRating);
				resultSet = preparedStatement.executeQuery();
			}
//			if (resultSet.next()) {
//				users.add(loadUserData(resultSet));
//				return users;
//			}
		} catch (SQLException e) {
		} finally {
			closeResultSet();
		}
		return null;
	}
}

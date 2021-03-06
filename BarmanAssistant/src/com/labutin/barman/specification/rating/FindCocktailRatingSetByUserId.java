package com.labutin.barman.specification.rating;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindCocktailRatingSetByUserId extends AbstractRatingSpecification implements RatingSpecification {
	private static final String FIND_COCKTAIL_RATING_SET_BY_USER_ID = "SELECT cocktail_id, user_id, cocktail_rating FROM CocktailRating WHERE user_id = ?";
	private int userId;

	public FindCocktailRatingSetByUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<Rating> query() throws RepositoryException {
		Set<Rating> ratingSet = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(FIND_COCKTAIL_RATING_SET_BY_USER_ID)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						ratingSet.add(loadCockctailRatingData());
					}
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
		return ratingSet;
	}
}

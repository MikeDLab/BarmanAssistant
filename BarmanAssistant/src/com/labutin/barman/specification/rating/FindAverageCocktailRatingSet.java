package com.labutin.barman.specification.rating;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindAverageCocktailRatingSet extends AbstractRatingSpecification implements RatingSpecification {
	private final static String FIND_COCKTAIL_RATING_SET_BY_USER_ID = "SELECT cocktail_id, user_id, cocktail_rating  FROM (Select cocktail_id as cocktail_id, user_id as emb_id FROM COCKTAIL) as Cocktail  INNER JOIN CocktailRating USING(cocktail_id) WHERE cocktail_id = ?";
	private int coktailId;

	public FindAverageCocktailRatingSet(int userId) {
		this.coktailId = userId;
	}

	@Override
	public Set<Rating> query() throws RepositoryException {
		Set<Rating> ratingSet = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_RATING_SET_BY_USER_ID)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, coktailId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					if (resultSet != null) {
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

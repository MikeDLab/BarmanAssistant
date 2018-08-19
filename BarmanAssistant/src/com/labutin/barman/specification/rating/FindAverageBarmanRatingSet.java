package com.labutin.barman.specification.rating;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindAverageBarmanRatingSet extends AbstractRatingSpecification implements RatingSpecification {
	private static final String FIND_BARMAN_RATING_SET_BY_USER_ID = "SELECT barman_rating, barman_id, user_id FROM (SELECT user_id as barman_id FROM User) User  INNER JOIN BarmanRating USING(barman_id) WHERE barman_id = ?";
	private int userId;

	public FindAverageBarmanRatingSet(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<Rating> query() throws RepositoryException {
		Set<Rating> ratingSet = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_BARMAN_RATING_SET_BY_USER_ID)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						ratingSet.add(loadBarmanRatingData());
					}
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
		return ratingSet;
	}
}

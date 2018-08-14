package com.labutin.barman.specification.rating;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindBarmanRatingSetByUserId extends AbstractRatingSpecification implements RatingSpecification {
	private final static String FIND_BARMAN_RATING_SET_BY_USER_ID = "SELECT barman_rating, barman_id, user_id FROM BarmanRating WHERE user_id = ?";
	private int userId;

	public FindBarmanRatingSetByUserId(int userId) {
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
				while (resultSet.next()) {
					if (resultSet != null) {
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

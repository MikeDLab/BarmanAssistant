package com.labutin.barman.specification.rating;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindBarmanRatingSet implements RatingSpecification {
	private static Logger logger = LogManager.getLogger();
	private final static String FIND_BARMAN_RATING_SET_BY_USER_ID = "Select * from BarmanRating Where user_id = ?";
	private int userId;

	public FindBarmanRatingSet(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<Rating> querry() {
		ResultSet resultSet = null;
		Set<Rating> ratingSet = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_BARMAN_RATING_SET_BY_USER_ID)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					if (resultSet != null) {
						Rating barmanRating = new Rating();
						barmanRating.setEstimating(resultSet.getInt("user_id"));
						barmanRating.setEstimated(resultSet.getInt("barman_id"));
						barmanRating.setRating(resultSet.getInt("barman_rating"));
						ratingSet.add(barmanRating);
					}
				}
				return ratingSet;
			}
		} catch (SQLException e) {
		}
		throw new RuntimeException();
	}
}

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

public class FindCocktailRatingSetByUserId implements RatingSpecification {
	private static Logger logger = LogManager.getLogger();
	private final static String FIND_COCKTAIL_RATING_SET_BY_USER_ID = "Select * from CocktailRating Where user_id = ?";
	private int userId;

	public FindCocktailRatingSetByUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<Rating> querry() {
		ResultSet resultSet = null;
		Set<Rating> ratingSet = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_RATING_SET_BY_USER_ID)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					if (resultSet != null) {
						Rating barmanRating = new Rating();
						barmanRating.setEstimating(resultSet.getInt("user_id"));
						barmanRating.setEstimated(resultSet.getInt("cocktail_id"));
						barmanRating.setRating(resultSet.getInt("cocktail_rating"));
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

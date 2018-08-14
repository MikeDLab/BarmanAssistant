package com.labutin.barman.specification.rating;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Rating;

public abstract class AbstractRatingSpecification {
	protected static final String USER_ID = "user_id";
	protected static final String BARMAN_RATING = "barman_rating";
	protected static final String BARMAN_ID = "barman_id";
	protected static final String COCKTAIL_RATING = "cocktail_rating";
	protected static final String COCKTAIL_ID = "cocktail_id";
	protected ResultSet resultSet;
	private static Logger logger = LogManager.getLogger();

	public AbstractRatingSpecification() {
		// TODO Auto-generated constructor stub
	}

	protected void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.warn("ResultSet close exception", e);
			}
		}
	}

	protected Rating loadBarmanRatingData() {
		Rating rating = null;
		try {
			if (resultSet != null) {
				rating = new Rating();
				rating.setEstimated(resultSet.getInt(BARMAN_ID));
				rating.setEstimating(resultSet.getInt(USER_ID));
				rating.setRating(resultSet.getInt(BARMAN_RATING));
			}
		} catch (SQLException e) {
			logger.warn("Load user data exception", e);
		}
		return rating;
	}
	protected Rating loadCockctailRatingData() {
		Rating rating = null;
		try {
			if (resultSet != null) {
				rating = new Rating();
				rating.setEstimated(resultSet.getInt(COCKTAIL_ID));
				rating.setEstimating(resultSet.getInt(USER_ID));
				rating.setRating(resultSet.getInt(COCKTAIL_RATING));
			}
		} catch (SQLException e) {
			logger.warn("Load user data exception", e);
		}
		return rating;
	}
}

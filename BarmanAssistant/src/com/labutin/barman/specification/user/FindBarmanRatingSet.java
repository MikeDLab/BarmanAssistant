package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Barman;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindBarmanRatingSet extends AbstractUserSpecification implements UserSpecification<Barman> {
	private static Logger logger = LogManager.getLogger();
	private final static String FIND_BARMAN_SET = "Select * from BarmanRating Where user_id = ?";
	private int userId;
	public FindBarmanRatingSet(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<Barman> querry() {
		Set<Barman> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_BARMAN_SET)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
			}
		} catch (SQLException e) {
			logger.warn("Find barman rating set exception",e);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

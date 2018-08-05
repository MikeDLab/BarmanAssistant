package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class AddBarmanRating extends AbstractUserSpecification implements UserSpecification {
	private int barmanRating;
	private int userId;
	private int barmanId;
	private final static String INSERT_BARMAN_RATING = "INSERT INTO BarmanRating(barman_rating, barman_id, user_id) VALUES (?,?,?)";

	public AddBarmanRating(int barmanRating, int barmanId, int userId) {
		// TODO Auto-generated constructor stubt
		this.barmanRating = barmanRating;
		this.barmanId = barmanId;
		this.userId = userId;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BARMAN_RATING)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, barmanRating);
				preparedStatement.setInt(2, barmanId);
				preparedStatement.setInt(3, userId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
		} finally {
			closeResultSet();
		}
		return null;
	}
}

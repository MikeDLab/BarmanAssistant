package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class DowngradeBarmanToUser extends AbstractUserSpecification implements UserSpecification {
	private static Logger logger = LogManager.getLogger();
	private final static String DOWNGRADE_TO_USER = "UPDATE  User Set user_role=2 WHERE user_id = ?";
	private int userId;

	public DowngradeBarmanToUser(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<User> querry() {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DOWNGRADE_TO_USER)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.info("Cannot downgrade barman to user with user_id=" + userId);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

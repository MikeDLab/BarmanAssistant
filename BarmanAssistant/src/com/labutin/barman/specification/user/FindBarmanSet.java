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

public class FindBarmanSet extends AbstractUserSpecification implements UserSpecification {
	private static Logger logger = LogManager.getLogger();
	private final static String FIND_BARMAN_SET = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User WHERE user_role=1 AND user_id != ?";
	private int userId;

	public FindBarmanSet(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_BARMAN_SET)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
			}
			while (resultSet.next()) {
				users.add(loadUserData());
			}
		} catch (SQLException e) {
			logger.warn("Find barman set - SQL exception", e);
		} finally {
			closeResultSet();
		}
		return users;
	}
}

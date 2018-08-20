package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserById extends AbstractUserSpecification implements UserSpecification {
	private int userId;
	private static final String FIND_USER_BY_ID = "SELECT user_id,user_login,user_name,user_password,user_email,user_role, user_isAvaible FROM User WHERE user_id = ?";

	public FindUserById(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<User> query() throws RepositoryException {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
				users.add(loadUserData());
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			closeResultSet();
		}
		return users;
	}
}

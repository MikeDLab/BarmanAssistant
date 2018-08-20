package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindBarmanSet extends AbstractUserSpecification implements UserSpecification {
	private static final  String FIND_BARMAN_SET = "SELECT user_id,user_login,user_name,user_password,user_email,user_role, user_isAvaible FROM User INNER JOIN Cocktail USING(user_id) WHERE user_id != ? AND cocktail_isPublished != 0 AND user_isAvaible != 0";
	private int userId;

	public FindBarmanSet(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<User> query() throws RepositoryException {
		Set<User> users = null;
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_BARMAN_SET)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
			}
			users = new HashSet<>();
			while (resultSet.next()) {
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

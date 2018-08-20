package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserSet extends AbstractUserSpecification implements UserSpecification {
	private static final String FIND_USER_SET = "SELECT user_id,user_login,user_name,user_password,user_email,user_role,user_isAvaible FROM User WHERE user_role != 0 AND user_isAvaible != 0";

	@Override
	public Set<User> query() throws RepositoryException {
		Comparator<User> comparator = Comparator.comparing(obj -> obj.getUserId());
		TreeSet<User> users = new TreeSet<>(comparator);
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SET)) {
			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			}
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

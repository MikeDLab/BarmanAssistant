package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserSet extends AbstractUserSpecification implements UserSpecification {
	private final static String FIND_USER_SET = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User Where user_role != 0";

	public FindUserSet() {
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SET)) {
			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			}
			while (resultSet.next()) {
				users.add(loadUserData(resultSet));
			}
		} catch (SQLException e) {
		} finally {
			closeResultSet();
		}
		return users;
	}
}

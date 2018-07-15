package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserByLogin extends AbstractUserSpecification implements UserSpecification {
	private String login;
	private final static String FIND_USER_BY_LOGIN = "SELECT user_login FROM User WHERE user_login = ?";

	public FindUserByLogin(String login) {
		// TODO Auto-generated constructor stubt
		this.login = login;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, login);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
				users.add(loadUserData(resultSet));
				return users;
			}
		} catch (SQLException e) {
		} finally {
			closeResultSet();
		}
		return null;
	}
}

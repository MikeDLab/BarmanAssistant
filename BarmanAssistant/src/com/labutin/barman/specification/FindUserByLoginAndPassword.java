package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserByLoginAndPassword extends AbstractUserSpecification implements UserSpecification {
	private String login;
	private String password;
	private final static String FIND_USER_BY_LOGIN = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User WHERE user_login = ? AND userl_password= ?";

	public FindUserByLoginAndPassword(String login, String password) {
		// TODO Auto-generated constructor stubt
		this.login = login;
		this.password = password;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, login);
				preparedStatement.setString(1, password);
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

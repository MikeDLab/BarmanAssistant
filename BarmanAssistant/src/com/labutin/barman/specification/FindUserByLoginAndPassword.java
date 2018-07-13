package com.labutin.barman.specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserByLoginAndPassword implements Specification {
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
		ResultSet resultSet = null;
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, login);
				preparedStatement.setString(1, password);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet != null && resultSet.next()) {
				User user = new User();
				user.setUser_login(resultSet.getString("user_login"));
				user.setUser_email(resultSet.getString("user_email"));
				user.setUser_name(resultSet.getString("user_name"));
				user.setUser_id(resultSet.getInt("user_id"));
				user.setUser_role(resultSet.getInt("user_role"));
				user.setUser_password(resultSet.getString("user_password"));
				users.add(user);
				return users;
			}
		} catch (SQLException e) {
		} finally {
			// Abstract
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}

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

public class FindUserByLogin extends AbstractUserSpecification implements UserSpecification {
	private static Logger logger = LogManager.getLogger();
	private String userLogin;
	private final static String FIND_USER_BY_LOGIN = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User WHERE user_login = ?";

	public FindUserByLogin(String login) {
		// TODO Auto-generated constructor stubt
		this.userLogin = login;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, userLogin);
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
				users.add(loadUserData(resultSet));
				return users;
			}

		} catch (SQLException e) {
			logger.info("Sqlexception", e);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

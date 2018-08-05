package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserByLoginAndPassword extends AbstractUserSpecification implements UserSpecification {
	private String login;
	private String password;
	private static Logger logger = LogManager.getLogger();
	private final static String FIND_USER_BY_LOGIN = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User WHERE user_login = ? AND user_password= ?";

	public FindUserByLoginAndPassword(String login, String password) {
		// TODO Auto-generated constructor stubt
		this.login = login;
		this.password = password;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();
		ProxyConnection connection = null;
		try {
			connection = PoolConnection.POOL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
			if (preparedStatement != null) {
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, DigestUtils.md5Hex(password));
				resultSet = preparedStatement.executeQuery();
			}
			if (resultSet.next()) {
				users.add(loadUserData(resultSet));
				return users;
			}
		} catch (SQLException e) {
			logger.warn("Sql exception",e);
		} finally {
			if (connection != null) {
				connection.close();
			}
			closeResultSet();
		}
		return users;
	}
}

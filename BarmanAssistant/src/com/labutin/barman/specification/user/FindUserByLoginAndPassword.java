package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class FindUserByLoginAndPassword extends AbstractUserSpecification implements UserSpecification {
	private String login;
	private String password;
	private final static String FIND_USER_BY_LOGIN = "SELECT user_id,user_login,user_name,user_password,user_email,user_role,user_isAvaible FROM User WHERE user_login = ? AND user_password= ? AND user_isAvaible != 0";

	public FindUserByLoginAndPassword(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public Set<User> query() throws RepositoryException {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, DigestUtils.md5Hex(password));
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

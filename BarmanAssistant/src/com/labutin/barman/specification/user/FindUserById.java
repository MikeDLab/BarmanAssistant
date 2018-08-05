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

public class FindUserById extends AbstractUserSpecification implements UserSpecification {
	private static Logger logger = LogManager.getLogger();
	private int user_id;
	private final static String FIND_USER_BY_ID = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User WHERE user_id = ?";

	public FindUserById(int user_id) {
		// TODO Auto-generated constructor stubt
		this.user_id = user_id;
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, user_id);
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

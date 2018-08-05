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

public class FindCocktailAuthrorSet extends AbstractUserSpecification implements UserSpecification {
	private final static String FIND_COCKTAIL_AUTHOR_SET = "SELECT user_id,user_login,user_name,user_password,user_email,user_role FROM User INNER JOIN Cocktail USING(user_id)";
	private static Logger logger = LogManager.getLogger();

	public FindCocktailAuthrorSet() {
	}

	@Override
	public Set<User> querry() {
		Set<User> users = new HashSet<>();

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_COCKTAIL_AUTHOR_SET)) {
			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			}
			while (resultSet.next()) {
				users.add(loadUserData(resultSet));
			}
		} catch (SQLException e) {
			logger.info("Sqlexception", e);
		} finally {
			closeResultSet();
		}
		return users;
	}
}

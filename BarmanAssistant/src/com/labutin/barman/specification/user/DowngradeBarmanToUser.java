package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;



import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class DowngradeBarmanToUser extends AbstractUserSpecification implements UserSpecification {
	private final static String DOWNGRADE_TO_USER = "UPDATE  User SET user_role= 2 WHERE user_id = ?";
	private int userId;

	public DowngradeBarmanToUser(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<User> query() throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DOWNGRADE_TO_USER)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			closeResultSet();
		}
		return null;
	}
}

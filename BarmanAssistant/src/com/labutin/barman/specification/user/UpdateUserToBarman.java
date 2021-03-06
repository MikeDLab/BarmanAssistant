package com.labutin.barman.specification.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;

public class UpdateUserToBarman extends AbstractUserSpecification implements UserSpecification {
	private static Logger logger = LogManager.getLogger();
	private static final  String UPDATE_TO_BARMAN = "UPDATE  User Set user_role=1 WHERE user_id = ?";
	private int userId;

	public UpdateUserToBarman(int userId) {
		this.userId = userId;
	}

	@Override
	public Set<User> query() throws RepositoryException {
		Set<User> users = new HashSet<>();
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TO_BARMAN)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, userId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.info("Cannot update user to barman with user_id=" + userId,e);
			throw new RepositoryException(e);
		} finally {
			closeResultSet();
		}
		return users;
	}
}

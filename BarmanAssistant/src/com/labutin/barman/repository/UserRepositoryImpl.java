package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.user.UserSpecification;
import com.mysql.jdbc.Statement;

public class UserRepositoryImpl implements UserRepository {
	private static class SingletonHandler {
		private final static UserRepositoryImpl INSTANCE = new UserRepositoryImpl();
	}

	private final static String INSERT_USER = "INSERT INTO User(user_login, user_name, user_password, user_email) VALUES (?,?,?,?)";
	private final static String REMOVE_USER = "UPDATE User SET user_isAvaible = 0 WHERE user_id = ?";

	private final static String UPDATE_USER = "UPDATE User SET user_name = ?, user_password = ?, user_email = ?  WHERE user_id = ?";

	public static UserRepositoryImpl getInstance() throws RepositoryException {
		try {
			PoolConnection pool = PoolConnection.POOL;
			pool.initialization();
			return SingletonHandler.INSTANCE;
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			throw new RepositoryException(e);
		}

	}

	private UserRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(User item) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER,
						Statement.RETURN_GENERATED_KEYS);) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, item.getUserLogin());
				preparedStatement.setString(2, item.getUserName());
				preparedStatement.setString(3, DigestUtils.md5Hex(item.getUserPassword()));
				preparedStatement.setString(4, item.getUserEmail());
				preparedStatement.executeUpdate();
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					item.setUserId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Add user id failed");
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public Set<User> query(UserSpecification specification) throws RepositoryException {
		return specification.query();
	}

	@Override
	public void remove(int userId) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);) {
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public void update(User item) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);) {
			preparedStatement.setString(1, item.getUserName());
			preparedStatement.setString(2, item.getUserPassword());
			preparedStatement.setString(3, item.getUserEmail());
			preparedStatement.setInt(4, item.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}

	}

}

package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.Specification;
import com.labutin.barman.specification.user.FindUserByLogin;
import com.labutin.barman.specification.user.UserSpecification;

public class UserRepositoryImpl implements UserRepository {
	private static Logger logger = LogManager.getLogger();
	private final static String INSERT_USER = "INSERT INTO User(user_login, user_name, user_password, user_email) VALUES (?,?,?,?)";
	private final static String REMOVE_USER = "UPDATE User SET user_isAvaible = 0 Where user_id = ?";
	private final static String UPDATE_USER = "UPDATE User SET user_name = ?, user_password = ?, user_email = ?  where user_login = ?";

	private UserRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	private static class SingletonHandler {
		private final static UserRepositoryImpl INSTANCE = new UserRepositoryImpl();
	}

	public static UserRepositoryImpl getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}

	@Override
	public void add(User item) throws RepositoryException {
		logger.info(item + " try to register");
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, item.getUserLogin());
				preparedStatement.setString(2, item.getUserName());
				preparedStatement.setString(3, DigestUtils.md5Hex(item.getUserPassword()));
				preparedStatement.setString(4, item.getUserEmail());
				preparedStatement.executeUpdate();
				logger.info(item + " registered");
			}
		} catch (SQLException e) {
			logger.info(item + " has problem");
			throw new RepositoryException(e);
		} finally {
		}
		// logger.info("User: " + item + "cannot register");

	}

	@Override
	public void remove(User item) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);){
			preparedStatement.setInt(1, item.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			preparedStatement.setString(4, item.getUserLogin());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RepositoryException(e);
		}

	}

	@Override
	public Set<User> query(UserSpecification specification) throws RepositoryException {
		// TODO Auto-generated method stub
		return specification.querry();
	}

}

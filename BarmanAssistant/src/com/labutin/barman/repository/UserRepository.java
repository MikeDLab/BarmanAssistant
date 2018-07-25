package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RemoveUserException;
import com.labutin.barman.exception.UpdateUserException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.FindUserByLogin;
import com.labutin.barman.specification.Specification;

public class UserRepository implements IUserRepository {
	private static Logger logger = LogManager.getLogger();
	private final static String INSERT_USER = "INSERT INTO User(user_login, user_name, user_password, user_email) VALUES (?,?,?,?)";
	private final static String REMOVE_USER = "DELETE FROM User WHERE user_login = ?";
	private final static String UPDATE_USER = "UPDATE User SET user_name = ?, user_password = ?, user_email = ?  where user_login = ?";

	private UserRepository() {
		// TODO Auto-generated constructor stub
	}

	private static class SingletonHandler {
		private final static UserRepository INSTANCE = new UserRepository();
	}

	public static UserRepository getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}

	@Override
	public void add(User item) throws AddUserException {
		ProxyConnection connection = null;
		PreparedStatement preparedStatement;
	//	if (query(new FindUserByLogin(item.getUserLogin())) == null) {
			logger.info(item + " try to register");
			try {
				connection = PoolConnection.POOL.getConnection();
				preparedStatement = connection.prepareStatement(INSERT_USER);
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
				throw new AddUserException();
			}
			finally {
				if(connection != null)
				{
					connection.close();
				}
			}
	//	}
		//logger.info("User: " + item + "cannot register");
		
	}

	@Override
	public void remove(User item) throws RemoveUserException {
		ProxyConnection connection;
		PreparedStatement preparedStatement;
		connection = PoolConnection.POOL.getConnection();
		try {
			preparedStatement = connection.prepareStatement(REMOVE_USER);
			preparedStatement.setString(1, item.getUserLogin());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RemoveUserException();
		}
	}

	@Override
	public void update(User item) throws UpdateUserException {
		ProxyConnection connection;
		PreparedStatement preparedStatement;
		connection = PoolConnection.POOL.getConnection();
		try {
			preparedStatement = connection.prepareStatement(UPDATE_USER);
			preparedStatement.setString(1, item.getUserName());
			preparedStatement.setString(2, item.getUserPassword());
			preparedStatement.setString(3, item.getUserEmail());
			preparedStatement.setString(4, item.getUserLogin());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new UpdateUserException();
		}

	}

	@Override
	public Set<User> query(Specification<User> specification) {
		// TODO Auto-generated method stub
		return specification.querry();
	}

}

package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import com.labutin.barman.entity.User;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.Specification;

public class UserRepository implements Repository<User> {
	// !!SINGLETON!!!
	static {
		PoolConnection pool = PoolConnection.POOL;
			pool.initialization();
	}
	private final static String INSERT_USER = "INSERT INTO User(user_login, user_name, user_password, user_email) VALUES (?,?,?,?)";

	public UserRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(User item) {
		// TODO Auto-generated method stub
		try {
			ProxyConnection connection = PoolConnection.POOL.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
			if (preparedStatement != null) {
				preparedStatement.setString(1, item.getUser_login());
				preparedStatement.setString(2, item.getUser_name());
				preparedStatement.setString(3, DigestUtils.md5Hex(item.getUser_password()));
				preparedStatement.setString(4, item.getUser_email());
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println(item);
			}

		} catch (SQLException e) {
		}
	}

	@Override
	public void remove(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<User> query(Specification specification) {
		// TODO Auto-generated method stub
		return specification.querry();
	}

}

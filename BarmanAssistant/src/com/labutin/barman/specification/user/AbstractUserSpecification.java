package com.labutin.barman.specification.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;

public abstract class AbstractUserSpecification {
	protected static final String USER_ID = "user_id";
	protected static final String USER_NAME = "user_name";
	protected static final String USER_LOGIN = "user_login";
	protected static final String USER_PASSWORD = "user_password";
	protected static final String USER_EMAIL = "user_email";
	protected static final String USER_ROLE = "user_role";
	protected static final String USER_AVAIBLE = "user_isAvaible";
	protected ResultSet resultSet;
	private static Logger logger = LogManager.getLogger();

	public AbstractUserSpecification() {
		// TODO Auto-generated constructor stub
	}

	protected void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.warn("ResultSet close exception", e);
			}
		}
	}

	protected User loadUserData() {
		User user = null;
		try {
			if (resultSet != null) {
				user = new User();
				user.setUserId(resultSet.getInt(USER_ID));
				user.setUserLogin(resultSet.getString(USER_LOGIN));
				user.setUserName(resultSet.getString(USER_NAME));
				user.setUserPassword(resultSet.getString(USER_PASSWORD));
				user.setUserEmail(resultSet.getString(USER_EMAIL));
				user.setUserRole(resultSet.getInt(USER_ROLE));
				user.setAvaible(resultSet.getBoolean(USER_AVAIBLE));
			}
		} catch (SQLException e) {
			logger.warn("Load user data exception", e);
		}
		return user;
	}
}

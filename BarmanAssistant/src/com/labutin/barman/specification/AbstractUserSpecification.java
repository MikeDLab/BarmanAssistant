package com.labutin.barman.specification;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.labutin.barman.entity.User;

public abstract class AbstractUserSpecification {
	protected ResultSet resultSet;

	public AbstractUserSpecification() {
		// TODO Auto-generated constructor stub
	}

	protected void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected User loadUserData(ResultSet resultSet)
	{
		User user = null;
		try {
			if (resultSet != null)
			{
				user = new User();
				user.setUserId(resultSet.getInt("user_id"));
				user.setUserLogin(resultSet.getString("user_login"));
				user.setUserName(resultSet.getString("user_name"));
				user.setUserPassword(resultSet.getString("user_password"));
				user.setUserEmail(resultSet.getString("user_email"));
				user.setUserRole(resultSet.getInt("user_role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return user;
	}
}

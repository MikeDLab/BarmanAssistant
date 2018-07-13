package com.labutin.barman.command;

import com.labutin.barman.entity.User;
import com.labutin.barman.repository.UserRepository;

public class UserReceiver {
	private final UserRepository userRepository = new UserRepository();
	public UserReceiver() {
		// TODO Auto-generated constructor stub
	}
	public User registration(String user_login, String user_name, String user_password, String user_email) {
		User user = new User();
		user.setUser_login(user_login);
		user.setUser_name(user_name);
		user.setUser_email(user_email);
		user.setUser_password(user_password);
		userRepository.add(user);
		return user;
	}
//	public User login(String login,String password)
//	{
//		User user = new User();
//		user.setName(login);
//		user.setPassword(password);
//		user = userRepository.query(new FindUserByLogin(login)).iterator().next();
//		return user;	
//	}
}

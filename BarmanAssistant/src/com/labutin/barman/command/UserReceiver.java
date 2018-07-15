package com.labutin.barman.command;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.repository.UserRepository;

public class UserReceiver {
	private final UserRepository userRepository;
	public UserReceiver() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		 userRepository = UserRepository.getInstance();
	}
	public User registration(String userLogin, String userName, String userPassword, String userEmail) throws AddUserException {
		User user = new User();
		user.setUserLogin(userLogin);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
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

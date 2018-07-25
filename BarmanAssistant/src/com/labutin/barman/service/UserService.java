package com.labutin.barman.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.UpdateToBarmanCommand;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.repository.UserRepository;
import com.labutin.barman.specification.AddBarmanRating;
import com.labutin.barman.specification.DowngradeBarmanToUser;
import com.labutin.barman.specification.FindBarmanSet;
import com.labutin.barman.specification.FindIngredientSet;
import com.labutin.barman.specification.FindUserByLoginAndPassword;
import com.labutin.barman.specification.FindUserSet;
import com.labutin.barman.specification.UpdateUserToBarman;

public class UserService {
	private static Logger logger = LogManager.getLogger();
	private final UserRepository userRepository;

	public UserService() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		userRepository = UserRepository.getInstance();
	}

	public User registration(String userLogin, String userName, String userPassword, String userEmail)
			throws AddUserException {
		User user = new User();
		user.setUserLogin(userLogin);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		userRepository.add(user);
		return user;
	}

	public User login(String login, String password) {
		User user = new User();
		user = userRepository.query(new FindUserByLoginAndPassword(login, password)).iterator().next();
		return user;
	}

	public Set<User> receiveBarman(int userId) {
		return userRepository.query(new FindBarmanSet(userId));
	}

	public Set<User> receiveAllUsers() {
		return userRepository.query(new FindUserSet());
	}

	public void updateToBarman(int userId) {
		userRepository.query(new UpdateUserToBarman(userId));
	}
	public void downgradeToUser(int userId) {
		userRepository.query(new DowngradeBarmanToUser(userId));
	}
	public void addBarmanRating(int barmanRating,int barmanId,int userId)
	{
		userRepository.query(new AddBarmanRating(barmanRating, barmanId, userId));
	}
}

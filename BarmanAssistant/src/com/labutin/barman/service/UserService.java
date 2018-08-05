package com.labutin.barman.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.user.UpdateToBarmanCommand;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.repository.UserRepository;
import com.labutin.barman.specification.ingredient.FindIngredientSet;
import com.labutin.barman.specification.user.AddBarmanRating;
import com.labutin.barman.specification.user.DowngradeBarmanToUser;
import com.labutin.barman.specification.user.FindBarmanSet;
import com.labutin.barman.specification.user.FindCocktailAuthrorSet;
import com.labutin.barman.specification.user.FindUserById;
import com.labutin.barman.specification.user.FindUserByLoginAndPassword;
import com.labutin.barman.specification.user.FindUserSet;
import com.labutin.barman.specification.user.UpdateUserToBarman;

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
		User user = null;
		if (userRepository.query(new FindUserByLoginAndPassword(login, password)).iterator().hasNext()) {
			user = (User) userRepository.query(new FindUserByLoginAndPassword(login, password)).iterator().next();
		}
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

	public void addBarmanRating(int barmanRating, int barmanId, int userId) {
		userRepository.query(new AddBarmanRating(barmanRating, barmanId, userId));
	}
	public Set<User> receiveCocktailAuthorSet()
	{
		return userRepository.query(new FindCocktailAuthrorSet());
	}
	public User receiveUserById(int userId)
	{
		return (User) userRepository.query(new FindUserById(userId)).iterator().next();
	}
}

package com.labutin.barman.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.user.UpdateToBarmanCommand;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.UserRepositoryImpl;
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
	private final UserRepositoryImpl userRepository;

	public UserService() throws ServiceException {
		try {
			userRepository = UserRepositoryImpl.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public User registration(String userLogin, String userName, String userPassword, String userEmail)
			throws RepositoryException {
		User user = new User();
		user.setUserLogin(userLogin);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		userRepository.add(user);
		return user;
	}

	public User login(String login, String password) throws ServiceException {
		User user = null;
		try {
			if (userRepository.query(new FindUserByLoginAndPassword(login, password)).iterator().hasNext()) {
				user = (User) userRepository.query(new FindUserByLoginAndPassword(login, password)).iterator().next();
			}
			return user;
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}

	}

	public Set<User> receiveBarman(int userId) throws ServiceException {
		try {
			return userRepository.query(new FindBarmanSet(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Set<User> receiveAllUsers() throws ServiceException {
		try {
			return userRepository.query(new FindUserSet());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void updateToBarman(int userId) throws ServiceException {
		try {
			userRepository.query(new UpdateUserToBarman(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void downgradeToUser(int userId) throws ServiceException {
		try {
			userRepository.query(new DowngradeBarmanToUser(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void addBarmanRating(int barmanRating, int barmanId, int userId) throws ServiceException {
		try {
			userRepository.query(new AddBarmanRating(barmanRating, barmanId, userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public Set<User> receiveCocktailAuthorSet() throws ServiceException {
		try {
			return userRepository.query(new FindCocktailAuthrorSet());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public User receiveUserById(int userId) throws ServiceException {
		try {
			return (User) userRepository.query(new FindUserById(userId)).iterator().next();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public void removeUser(int userId) throws ServiceException {
		User user = new User();
		user.setUserId(userId);
		try {
			userRepository.remove(user);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
}

package com.labutin.barman.service;

import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.RatingRepository;
import com.labutin.barman.repository.UserRepositoryImpl;
import com.labutin.barman.specification.user.DowngradeBarmanToUser;
import com.labutin.barman.specification.user.FindBarmanSet;
import com.labutin.barman.specification.user.FindCocktailAuthrorSet;
import com.labutin.barman.specification.user.FindUserById;
import com.labutin.barman.specification.user.FindUserByLoginAndPassword;
import com.labutin.barman.specification.user.FindUserSet;
import com.labutin.barman.specification.user.UpdateUserToBarman;

public class UserService {
	private static UserService INSTANCE;
	private final RatingRepository ratingRepository;
	private final UserRepositoryImpl userRepository;

	private UserService() throws ServiceException {
		try {
			userRepository = UserRepositoryImpl.getInstance();
			ratingRepository = new RatingRepository();
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public static UserService getInstance() throws ServiceException {
		return (INSTANCE == null) ? INSTANCE = new UserService() : INSTANCE;
	}

	public void addBarmanRating(int barmanRating, int barmanId, int userId) throws ServiceException {
		try {
			Rating rating = new Rating(userId, barmanId, barmanRating);
			ratingRepository.addBarmanRating(rating);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void downgradeToUser(int userId) throws ServiceException {
		try {
			userRepository.query(new DowngradeBarmanToUser(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public User login(String login, String password) throws ServiceException {
		try {
			Set<User> setUser = userRepository.query(new FindUserByLoginAndPassword(login, password));
			if (setUser.iterator().hasNext()) {
				return setUser.iterator().next();
			} else {
				return null;
			}
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<User> receiveAllUsers() throws ServiceException {
		try {
			return userRepository.query(new FindUserSet());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<User> receiveBarman(int userId) throws ServiceException {
		try {
			return userRepository.query(new FindBarmanSet(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Set<User> receiveCocktailAuthorSet() throws ServiceException {
		try {
			return userRepository.query(new FindCocktailAuthrorSet());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public User receiveUserById(int userId) throws ServiceException {
		try {
			Set<User> setUser = userRepository.query(new FindUserById(userId));
			if (setUser.iterator().hasNext()) {
				return setUser.iterator().next();
			} else {
				return null;
			}
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public User registration(String userLogin, String userName, String userPassword, String userEmail)
			throws ServiceException {

		try {
			User user = new User();
			user.setUserLogin(userLogin);
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUserPassword(userPassword);
			userRepository.add(user);
			return user;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void removeUser(int userId) throws ServiceException {
		try {
			userRepository.remove(userId);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void updateToBarman(int userId) throws ServiceException {
		try {
			userRepository.query(new UpdateUserToBarman(userId));
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
}

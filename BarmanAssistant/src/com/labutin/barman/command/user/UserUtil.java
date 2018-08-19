package com.labutin.barman.command.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.LocaleKey;
import com.labutin.barman.command.UtilCommand;
import com.labutin.barman.controller.UserType;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.RatingService;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.MailSender;
import com.labutin.barman.util.XssParser;
import com.labutin.barman.validator.UserValidator;

class UserUtil extends UtilCommand {
	private static Logger logger = LogManager.getLogger();
	private UserService userService;
	private CocktailService cocktailService;
	private RatingService ratingService;

	void addBarmanRating(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			userService = UserService.getInstance();
			User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			int barmanId = Integer.parseInt(request.getParameter(JspParameter.BARMAN_ID.getValue()));
			int barmanRating = Integer.parseInt(request.getParameter(JspParameter.BARMAN_RATING.getValue()));
			userService.addBarmanRating(barmanRating, barmanId, user.getUserId());
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Add barman rating exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	private Integer averageRating(Set<Rating> ratingSet) {
		if (ratingSet.isEmpty()) {
			return null;
		}
		Integer sum = 0;
		for (Rating rating : ratingSet) {
			sum += rating.getRating();
		}
		return sum / ratingSet.size();
	}

	void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			userService = UserService.getInstance();
			userService.removeUser(userId);
			if (UserService.getUserList().containsKey(userId)) {
				UserService.getUserList().get(userId).setAvaible(false);
			}
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Delete exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	void downgradeToUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			userService = UserService.getInstance();
			userService.downgradeToUser(userId);
			if (UserService.getUserList().containsKey(userId)) {
				UserService.getUserList().get(userId).setUserRole(2);
			}
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Downgrade to user exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	UserType logIn(HttpServletRequest request, HttpServletResponse response) {
		String userLogin = XssParser.parse(request.getParameter(JspParameter.USER_LOGIN.getValue()));
		String userPassword = XssParser.parse(request.getParameter(JspParameter.USER_PASSWORD.getValue()));
		UserType userRole;
		try {
			userService = UserService.getInstance();
			User user = userService.login(userLogin, userPassword);
			if (user != null) {
				switch (user.getUserRole()) {
				case 0:
					userRole = UserType.ADMIN;
					break;
				case 1:
					userRole = UserType.BARMAN;
					break;
				case 2:
					userRole = UserType.USER;
					break;
				default:
					userRole = UserType.GUEST;
				}
				request.getSession().setAttribute(JspParameter.ROLE.getValue(), userRole);
				request.getSession().setAttribute(JspParameter.USER.getValue(), user);
				UserService.addUser(user);
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.INVALID_USER.getValue()));
				userRole = UserType.GUEST;
			}
		} catch (ServiceException e) {
			logger.warn("Login exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			userRole = UserType.GUEST;
		}
		return userRole;
	}

	void logOut(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
		if (user != null) {
			UserService.getUserList().remove(user.getUserId());
		}
		request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
		request.getSession().removeAttribute(JspParameter.USER.getValue());
		logger.info("Users onine: " + UserService.getUserList().size());

	}

	private Map<User, Integer> receiveAverageRatingMap(Set<User> barmanSet) {
		try {
			ratingService = RatingService.getInstance();
			Map<User, Integer> userAverageRatingMap = new HashMap<>();
			for (User user : barmanSet) {
				Set<Rating> averageRating = ratingService.receiveBarmanRatingSetByBarmanId(user.getUserId());
				userAverageRatingMap.put(user, averageRating(averageRating));
			}
			return userAverageRatingMap;
		} catch (ServiceException e) {
			return null;
		}

	}

	private Map<User, Rating> receiveBarmanRatingByUser(Set<User> setBarman, Set<Rating> setRating) {
		Map<User, Rating> userRatingMap = new HashMap<>();
		if (setBarman != null) {
			for (User usr : setBarman) {
				userRatingMap.put(usr, null);
				if (setRating != null) {
					for (Rating rating : setRating) {
						if (usr != null && rating != null && usr.getUserId() == rating.getEstimated()) {
							userRatingMap.replace(usr, rating);
						}
					}
				}
			}
		}
		return userRatingMap;
	}

	UserType registerUser(HttpServletRequest request, HttpServletResponse response) {
		UserValidator validator = new UserValidator();
		String userLogin = XssParser.parse(request.getParameter(JspParameter.USER_LOGIN.getValue()));
		String userName = XssParser.parse(request.getParameter(JspParameter.USER_NAME.getValue()));
		String userPassword = XssParser.parse(request.getParameter(JspParameter.USER_PASSWORD.getValue()));
		String userEmail = XssParser.parse(request.getParameter(JspParameter.USER_EMAIL.getValue()));
		if (!validator.isLogin(userLogin)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.INCORRECT_LOGIN.getValue()));
			return UserType.GUEST;
		}
		if (!validator.isName(userName)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.INCORRECT_NAME.getValue()));
			return UserType.GUEST;
		}
		if (!validator.isPassword(userPassword)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.INCORRECT_PASSWORD.getValue()));
			return UserType.GUEST;
		}
		if (!validator.isEmail(userEmail)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.INCORRECT_EMAIL.getValue()));
			return UserType.GUEST;
		}
		try {
			userService = UserService.getInstance();
			User user = userService.registration(userLogin, userName, userPassword, userEmail);
			new Thread(new MailSender(userEmail, userName)).start();
			request.getSession(true).setAttribute(JspParameter.ROLE.getValue(), UserType.USER);
			request.getSession(true).setAttribute(JspParameter.USER.getValue(), user);
			return UserType.USER;

		} catch (ServiceException e) {
			logger.warn("Register user exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return UserType.GUEST;
		}

	}

	void showBarmanSet(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			userService = UserService.getInstance();
			ratingService = RatingService.getInstance();
			Set<User> setBarman = null;
			Set<Rating> setRating = null;
			User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			if (user != null) {
				setBarman = userService.receiveBarman(user.getUserId());
				setRating = ratingService.receiveBarmanRatingSetByUserId(user.getUserId());
				Map<User, Rating> userRatingMap = new HashMap<>();
				Map<User, Integer> userAverageRatingMap = new HashMap<>();
				if (setBarman != null) {
					userAverageRatingMap = receiveAverageRatingMap(setBarman);
					userRatingMap = receiveBarmanRatingByUser(setBarman, setRating);
				}
				request.setAttribute(JspParameter.USER_RATING_MAP.getValue(), userRatingMap);
				request.setAttribute(JspParameter.USER_AVERAGE_RATING_MAP.getValue(), userAverageRatingMap);
				request.setAttribute(JspParameter.BARMAN_SET.getValue(), setBarman);
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			}

		} catch (ServiceException e) {
			logger.warn("Show barman set exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	void showUserSet(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			userService = UserService.getInstance();
			cocktailService = CocktailService.getInstance();
			Set<User> setUser = userService.receiveAllUsers();
			Map<User, Integer> userAverageRatingMap = receiveAverageRatingMap(setUser);
			Set<Cocktail> cocktailSet;
			Map<User, Integer> userCocktailNumberMap = new HashMap<>();
			for (User usr : setUser) {
				cocktailSet = cocktailService.receiveCocktailSetByUserId(usr.getUserId());
				userCocktailNumberMap.put(usr, cocktailSet.size());
			}
			request.setAttribute(JspParameter.USER_AVERAGE_RATING_MAP.getValue(), userAverageRatingMap);
			request.setAttribute(JspParameter.USER_COCKTAIL_NUMBER_MAP.getValue(), userCocktailNumberMap);
			request.setAttribute(JspParameter.USER_SET.getValue(), setUser);
		} catch (ServiceException e) {
			logger.warn("Show user set exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	void updateToBarman(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			userService = UserService.getInstance();
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			userService.updateToBarman(userId);
			if (UserService.getUserList().containsKey(userId)) {
				UserService.getUserList().get(userId).setUserRole(1);
			}
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Update user to Barman exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}
}

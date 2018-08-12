package com.labutin.barman.command.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.JspParameter;
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

class UserUtil {
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;
	private CocktailService receiverCocktail;
	private RatingService receiverRating;

	public UserUtil() {
		// TODO Auto-generated constructor stub
	}

	void addBarmanRating(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			int barmanId = Integer.parseInt(request.getParameter(JspParameter.BARMAN_ID.getValue()));
			int barmanRating = Integer.parseInt(request.getParameter(JspParameter.BARMAN_RATING.getValue()));
			receiver.addBarmanRating(barmanRating, barmanId, user.getUserId());
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Cannot update set rating to barman");
		}
	}

	private Integer averageRating(Set<Rating> rating) {
		if (rating.isEmpty()) {
			return null;
		}
		Integer sum = 0;
		for (Rating r : rating) {
			sum += r.getRating();
		}
		return sum / rating.size();
	}

	void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			receiver = new UserService();
			receiver.removeUser(userId);
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry,try later");
		}
	}

	void downgradeToUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiver.downgradeToUser(Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue())));
		} catch (ServiceException | NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Cannot downgrade barman to user");
		}
	}

	UserType logIn(HttpServletRequest request, HttpServletResponse response) {
		String userLogin = XssParser.parse(request.getParameter(JspParameter.USER_LOGIN.getValue()));
		String userPassword = XssParser.parse(request.getParameter(JspParameter.USER_PASSWORD.getValue()));
		UserType userRole;
		try {
			receiver = new UserService();
			User user = receiver.login(userLogin, userPassword);
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
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						"Incorrect user login or password, or your accoun is banned");
				userRole = UserType.GUEST;
			}
		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry,try later");
			userRole = UserType.GUEST;
		}
		return userRole;
	}

	void logOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
		request.getSession().removeAttribute(JspParameter.USER.getValue());
	}

	private Map<User, Integer> receiveAverageRatingMap(Set<User> barmanSet) {
		try {
			receiverRating = new RatingService();
			Map<User, Integer> userAverageRatingMap = new HashMap<>();
			for (User usr : barmanSet) {

				Set<Rating> averageRating = receiverRating.receiveBarmanRatingSetByBarmanId(usr.getUserId());
				userAverageRatingMap.put(usr, averageRating(averageRating));
			}
			return userAverageRatingMap;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
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
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Incorrect login");
			return UserType.GUEST;
		}
		if (!validator.isName(userName)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Incorrect name");
			return UserType.GUEST;
		}
		if (!validator.isPassword(userPassword)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Incorrect password");
			return UserType.GUEST;
		}
		if (!validator.isEmail(userEmail)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Incorrect Email");
			return UserType.GUEST;
		}
		// TODO Auto-generated method stub
		try {
			receiver = new UserService();
			User user = receiver.registration(userLogin, userName, userPassword, userEmail);
			new Thread(new MailSender(userEmail, userName)).start();
			request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.USER);
			request.getSession().setAttribute(JspParameter.USER.getValue(), user);
			return UserType.USER;

		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry,try later");
			return UserType.GUEST;
		}

	}

	void showBarmanSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiverRating = new RatingService();
			Set<User> setBarman = null;
			Set<Rating> setRating = null;
			User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			if (user != null) {
				setBarman = receiver.receiveBarman(user.getUserId());
				setRating = receiverRating.receiveBarmanRatingSetByUserId(user.getUserId());
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
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
			}

		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
		}
	}

	void showUserSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiverCocktail = new CocktailService();
			Set<User> setUser = receiver.receiveAllUsers();
			Map<User, Integer> userAverageRatingMap = receiveAverageRatingMap(setUser);
			Set<Cocktail> cocktailSet;
			Map<User, Integer> userCocktailNumberMap = new HashMap<>();
			for (User usr : setUser) {
				cocktailSet = receiverCocktail.receiveCocktailSetByUserId(usr.getUserId());
				userCocktailNumberMap.put(usr, cocktailSet.size());
			}
			request.setAttribute(JspParameter.USER_AVERAGE_RATING_MAP.getValue(), userAverageRatingMap);
			request.setAttribute(JspParameter.USER_COCKTAIL_NUMBER_MAP.getValue(), userCocktailNumberMap);
			request.setAttribute(JspParameter.USER_SET.getValue(), setUser);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Some problem");
		}
	}

	void updateToBarman(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			receiver.updateToBarman(userId);
		} catch (ServiceException | NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Cannot update user to barman");
		}
	}
}

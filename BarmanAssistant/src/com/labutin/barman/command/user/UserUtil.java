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
import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.RatingService;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.MailSender;
import com.labutin.barman.util.XssParser;
import com.labutin.barman.validator.UserValidator;

class UserUtil {
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;
	private RatingService receiverRating;

	public UserUtil() {
		// TODO Auto-generated constructor stub
	}
	void checkUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			User user = (User) request.getSession().getAttribute("User");
			if(user != null)
			{
			System.out.println("User session to string: " + user);
			User userBd = receiver.receiveUserById(user.getUserId());
			System.out.println("User from bd to string: " + user);
			}
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute("Errormessage", "Cannot update set rating to barman");
		}
	}
	void addBarmanRating(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			User user = (User) request.getSession().getAttribute("User");
			int barmanId = Integer.parseInt(request.getParameter("barmanid"));
			int barmanRating = Integer.parseInt(request.getParameter("cocktailvol"));
			receiver.addBarmanRating(barmanRating, barmanId, user.getUserId());
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute("Errormessage", "Cannot update set rating to barman");
		}
	}

	void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int userId = Integer.parseInt(request.getParameter("user_id"));
			receiver = new UserService();
			receiver.removeUser(userId);
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute("Errormessage", "Sorry,try later");
		}
	}

	void downgradeToUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiver.downgradeToUser(Integer.parseInt(request.getParameter("userId")));
		} catch (ServiceException | NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute("Errormessage", "Cannot downgrade barman to user");
		}
	}

	UserType logIn(HttpServletRequest request, HttpServletResponse response) {
		String userLogin = XssParser.parse(request.getParameter("user_login"));
		String userPassword = XssParser.parse(request.getParameter("user_password"));
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
				request.getSession().setAttribute("Role", userRole);
				request.getSession().setAttribute("User", user);
			} else {
				request.setAttribute("Errormessage", "Incorrect user login or password, or your accoun is banned");
				userRole = UserType.GUEST;
			}
		} catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry,try later");
			userRole = UserType.GUEST;
		}
		return userRole;
	}

	void logOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("Role", UserType.GUEST);
		request.getSession().removeAttribute("User");
	}

	UserType registerUser(HttpServletRequest request, HttpServletResponse response) {
		UserValidator validator = new UserValidator();
		String userLogin = XssParser.parse(request.getParameter(JspParameter.USER_LOGIN.getValue()));
		String userName = XssParser.parse(request.getParameter(JspParameter.USER_NAME.getValue()));
		String userPassword = XssParser.parse(request.getParameter(JspParameter.USER_PASSWORD.getValue()));
		String userEmail = XssParser.parse(request.getParameter(JspParameter.USER_EMAIL.getValue()));
		if (!validator.isLogin(userLogin)) {
			request.setAttribute("Errormessage", "Incorrect login");
			return UserType.GUEST;
		}
		if (!validator.isName(userName)) {
			request.setAttribute("Errormessage", "Incorrect name");
			return UserType.GUEST;
		}
		if (!validator.isPassword(userPassword)) {
			request.setAttribute("Errormessage", "Incorrect password");
			return UserType.GUEST;
		}
		if (!validator.isEmail(userEmail)) {
			request.setAttribute("Errormessage", "Incorrect Email");
			return UserType.GUEST;
		}
		// TODO Auto-generated method stub
		try {
			receiver = new UserService();
			User user = receiver.registration(userLogin, userName, userPassword, userEmail);
			new Thread(new MailSender(userEmail, userName)).start();
			request.getSession().setAttribute("Role", UserType.USER);
			request.getSession().setAttribute("User", user);
			return UserType.USER;

		} catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry,try later");
			return UserType.GUEST;
		}

	}

	void showBarmanSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiverRating = new RatingService();
			Set<User> setBarman = null;
			Set<Rating> setRating = null;
			User user = (User) request.getSession().getAttribute("User");
			if (user != null) {
				setBarman = receiver.receiveBarman(user.getUserId());
				setRating = receiverRating.receiveBarmanRatingSet(user.getUserId());
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

				request.setAttribute("userRatingMap", userRatingMap);
				request.setAttribute("setBarman", setBarman);
			} else {
				request.setAttribute("Errormessage", "Sorry try later");
			}

		} catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
	}

	void showUserSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			Set<User> setUser = receiver.receiveAllUsers();
			request.setAttribute("setUser", setUser);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			request.setAttribute("Errormessage", "Some problem");
		}
	}

	void updateToBarman(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			int userId = Integer.parseInt(request.getParameter("userId"));
			receiver.updateToBarman(userId);
		} catch (ServiceException | NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute("Errormessage", "Cannot update user to barman");
		}
	}
}

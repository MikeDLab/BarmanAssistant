package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.controller.UserType;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.UserService;

public class UserLoginCommand implements Command {
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;
	private UserType role;

	public UserLoginCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			receiver = new UserService();
			logger.info("Try to login user");
			User user = receiver.login((String) request.getParameter(JspParameter.USER_NAME.getValue()),
					(String) request.getParameter(JspParameter.USER_PASSWORD.getValue()));
			if (user != null) {
				switch (user.getUserRole()) {
				case 0:
					role = UserType.ADMIN;
					break;
				case 1:
					role = UserType.BARMAN;
					break;
				case 2:
					role = UserType.USER;
					break;
				default:
					role = UserType.GUEST;
				}
				request.getSession().setAttribute("Role", role);
				request.getSession().setAttribute("User", user);
				return PageEnum.HOME_PAGE;
			}
			else
			{
				request.setAttribute("Errormessage", "Incorrect user login or password!");
				return PageEnum.LOGIN_PAGE;
			}
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			logger.warn("JDBC problem",e);
		}

		throw new RuntimeException();
	}

}

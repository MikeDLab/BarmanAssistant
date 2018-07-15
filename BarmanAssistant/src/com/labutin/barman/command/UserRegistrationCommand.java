package com.labutin.barman.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.validator.UserValidator;

public class UserRegistrationCommand implements Command {
	// Service
	private static Logger logger = LogManager.getLogger();
	private UserReceiver receiver;

	public UserRegistrationCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		UserValidator validator = new UserValidator();
		String userLogin = request.getParameter(JspParameter.USER_LOGIN.getValue());
		String userName = request.getParameter(JspParameter.USER_NAME.getValue());
		String userPassword = request.getParameter(JspParameter.USER_PASSWORD.getValue());
		String userEmail = request.getParameter(JspParameter.USER_EMAIL.getValue());
		if(!validator.isLogin(userLogin))
		{
			//return excp page login
			logger.info("incorrect login");
		}
		if(!validator.isName(userName))
		{
			logger.info("incorrect name");
		}
		if(!validator.isPassword(userPassword))
		{
			logger.info("incorrect password");
			//return excp page password
		}
//		if(!validator.isEmail(userEmail))
//		{
//			logger.info("incorrect email");
//			//return excp page login
//		}
		// TODO Auto-generated method stub
		try {
			receiver = new UserReceiver();
			logger.info("JDBC IS OK");
		} catch (NoJDBCDriverException e) {
			logger.info("No JDBC DRIVER");
			// TODO Auto-generated catch block
			// setAtribute
			// return ERROR_PAGE;
			e.printStackTrace();
		} catch (NoJDBCPropertiesFileException e) {
			logger.info("No Prop JDBC");
			// setAtribute
			// return ERROR_PAGE;
		}
		User user;
		try {
			user = receiver.registration(userLogin, userName, userPassword, userEmail);
			if (user != null) {
				//request.getSession().setAttribute(JspParameter.USER.getValue(), user);
				return PageEnum.HOME_PAGE;
			}
		} catch (AddUserException e) {
			// TODO Auto-generated catch block
			// return ERROR_PAGE;
		}
		throw new RuntimeException();
	}

}

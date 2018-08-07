package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.MailSender;
import com.labutin.barman.validator.UserValidator;

public class UserRegistrationCommand implements Command {
	// Service
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;

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
		if (!validator.isLogin(userLogin)) {
			logger.info("incorrect login");
			request.setAttribute("Errormessage", "Incorrect login");
			return PageEnum.REGISTRATION_PAGE;
		}
		if (!validator.isName(userName)) {
			logger.info("incorrect name");
			request.setAttribute("Errormessage", "Incorrect name");
			return PageEnum.REGISTRATION_PAGE;
		}
		if (!validator.isPassword(userPassword)) {
			System.out.println(userPassword + " incorrect");
			logger.info("incorrect password");
			request.setAttribute("Errormessage", "Incorrect password");
			return PageEnum.REGISTRATION_PAGE;
		}
//		if(!validator.isEmail(userEmail))
//		{
//			logger.info("incorrect email");
//			//return excp page login
//		}
		// TODO Auto-generated method stub
		try {
			receiver = new UserService();
			logger.info("JDBC IS OK");
		}catch (ServiceException e) {
			// TODO: handle exception
		}
		User user;

		try {
			user = receiver.registration(userLogin, userName, userPassword, userEmail);
			if (user != null) {
				// request.getSession().setAttribute(JspParameter.USER.getValue(), user);
				new Thread(new MailSender(userEmail, userName)).start();
				return PageEnum.HOME_PAGE;
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		throw new RuntimeException();
	}

}

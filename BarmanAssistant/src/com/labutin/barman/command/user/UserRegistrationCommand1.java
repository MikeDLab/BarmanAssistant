package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.CommandDecorator;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.MailSender;
import com.labutin.barman.validator.UserValidator;

public class UserRegistrationCommand1 extends CommandDecorator implements Command {
	public UserRegistrationCommand1(Command command) {
		super(command);
		// TODO Auto-generated constructor stub
	}

	private static Logger logger = LogManager.getLogger();

	protected boolean validate(HttpServletRequest request, HttpServletResponse response) {
		String userLogin = request.getParameter(JspParameter.USER_LOGIN.getValue());
		String userName = request.getParameter(JspParameter.USER_NAME.getValue());
		String userPassword = request.getParameter(JspParameter.USER_PASSWORD.getValue());
		String userEmail = request.getParameter(JspParameter.USER_EMAIL.getValue());
		UserValidator validator = new UserValidator();
		if (!validator.isLogin(userLogin)) {
			logger.info("incorrect login");
			request.setAttribute("Errormessage", "Incorrect login");
			return false;
		}
		if (!validator.isName(userName)) {
			logger.info("incorrect name");
			request.setAttribute("Errormessage", "Incorrect name");
			return false;
		}
		if (!validator.isPassword(userPassword)) {
			System.out.println(userPassword + " incorrect");
			logger.info("incorrect password");
			request.setAttribute("Errormessage", "Incorrect password");
			return false;
		}
		return true;
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if (validate(request, response)) {
			return command.execute(request, response);
		}
		return PageEnum.REGISTRATION_PAGE;
	}

}

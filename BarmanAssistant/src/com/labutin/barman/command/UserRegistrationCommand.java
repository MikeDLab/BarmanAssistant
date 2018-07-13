package com.labutin.barman.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.entity.User;

public class UserRegistrationCommand implements Command {
	// Service
	private UserReceiver receiver = new UserReceiver();

	public UserRegistrationCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user = receiver.registration(request.getParameter(JspParameter.USER_LOGIN.getValue()),
				request.getParameter(JspParameter.USER_NAME.getValue()),
				request.getParameter(JspParameter.USER_PASSWORD.getValue()),
				request.getParameter(JspParameter.USER_EMAIL.getValue()));
		if (user != null) {
			request.getSession().setAttribute(JspParameter.USER.getValue(), user);
			return PageEnum.HOME_PAGE;
		}
		throw new RuntimeException();
	}

}

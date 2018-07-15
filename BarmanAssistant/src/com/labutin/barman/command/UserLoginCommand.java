package com.labutin.barman.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.entity.User;



public class UserLoginCommand implements Command {
	private UserReceiver receiver;

	public UserLoginCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		User user = receiver.login((String) request.getParameter(JspParameter.USER_NAME.getValue()),
//				(String) request.getParameter(JspParameter.USER_PASSWORD.getValue()));
//		request.getSession().setAttribute(JspParameter.USER.getValue(), user);
//		if (user != null) {
//			return PageEnum.LOAD_FILE_PAGE;
//		}
		throw new RuntimeException();
	}

}

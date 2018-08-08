package com.labutin.barman.command.user;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.MailSender;

class UserUtil {
	private UserService receiver;

	public UserUtil() {
		// TODO Auto-generated constructor stub
	}

	void showUserSet(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			Set<User> setUser = receiver.receiveAllUsers();
			request.setAttribute("setUser", setUser);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

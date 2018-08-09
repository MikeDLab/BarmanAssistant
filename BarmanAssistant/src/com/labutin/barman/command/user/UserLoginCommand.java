package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;
import com.labutin.barman.controller.UserType;


public class UserLoginCommand extends AbstractUserCommand {
	public UserLoginCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	 UserType userType =  userUtil.logIn(request, response);
	 if(userType == UserType.GUEST)
	 {
		 return PageEnum.LOGIN_PAGE;
	 }
	 else
	 {
		 return PageEnum.HOME_PAGE;
	 }
	}

}

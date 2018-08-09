package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;
import com.labutin.barman.controller.UserType;


public class UserRegistrationCommand extends AbstractUserCommand {
	
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {	
		UserType userType =  userUtil.registerUser(request, response);
		 if(userType == UserType.GUEST)
		 {
			 return PageEnum.REGISTRATION_PAGE;
		 }
		 else
		 {
			 return PageEnum.HOME_PAGE;
		 }
	}

}

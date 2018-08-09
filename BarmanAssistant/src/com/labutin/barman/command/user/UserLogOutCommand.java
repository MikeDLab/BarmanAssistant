package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class UserLogOutCommand extends AbstractUserCommand {

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		userUtil.logOut(request, response);
		return PageEnum.HOME_PAGE;
	}

}

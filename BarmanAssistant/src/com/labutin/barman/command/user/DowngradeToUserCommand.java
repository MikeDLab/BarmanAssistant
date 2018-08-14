package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class DowngradeToUserCommand extends AbstractUserCommand {
	public DowngradeToUserCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		userUtil.init(request, response);
		userUtil.downgradeToUser(request, response);
		userUtil.showUserSet(request, response);
		return PageEnum.USER_LIST;

	}

}

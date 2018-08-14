package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class UpdateToBarmanCommand extends AbstractUserCommand{
	public UpdateToBarmanCommand() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		userUtil.init(request, response);
		userUtil.updateToBarman(request, response);
		userUtil.showUserSet(request, response);
		return PageEnum.USER_LIST;
	}

}

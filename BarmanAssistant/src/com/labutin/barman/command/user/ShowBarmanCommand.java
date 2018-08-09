package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;

public class ShowBarmanCommand extends AbstractUserCommand {
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		userUtil.showBarmanSet(request, response);
		return PageEnum.BARMAN_LIST;

	}

}

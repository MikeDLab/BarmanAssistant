package com.labutin.barman.command.redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;

public class RedirectToRegisterPageCommand implements Command {
	public RedirectToRegisterPageCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.REGISTRATION_PAGE;
	}

}

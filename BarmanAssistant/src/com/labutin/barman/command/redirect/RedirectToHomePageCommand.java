package com.labutin.barman.command.redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;

public class RedirectToHomePageCommand implements Command {
	public RedirectToHomePageCommand() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.HOME_PAGE;
	}

}

package com.labutin.barman.command.user;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;

public class ShowUserCommand implements Command {
	private UserService receiver;
	private UserUtil userUtil = new UserUtil();
	public ShowUserCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		userUtil.showUserSet(request, response);
		return PageEnum.USER_LIST;

	}

}

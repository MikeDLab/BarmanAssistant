package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.controller.UserType;

public class UserLogOutCommand implements Command {
	private static Logger logger = LogManager.getLogger();
	public UserLogOutCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
				request.getSession().setAttribute("Role",UserType.GUEST);
				request.getSession().removeAttribute("User");
				logger.info("Log out");
				return PageEnum.HOME_PAGE;
	}

}

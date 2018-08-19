package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;

public abstract class AbstractUserCommand implements Command {
	protected UserUtil userUtil = new UserUtil();
	public AbstractUserCommand() {
	}
	public abstract PageEnum execute(HttpServletRequest request,HttpServletResponse response);

}

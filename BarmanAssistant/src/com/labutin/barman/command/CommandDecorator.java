package com.labutin.barman.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.exception.ServiceException;

public abstract class CommandDecorator implements Command {
	protected Command command;

	public CommandDecorator(Command command) {
		// TODO Auto-generated constructor stub
		this.command = command;
	}
	
	public abstract PageEnum execute(HttpServletRequest request, HttpServletResponse response);

}

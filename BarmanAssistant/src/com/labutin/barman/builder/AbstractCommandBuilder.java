package com.labutin.barman.builder;

import com.labutin.barman.command.Command;

public abstract class AbstractCommandBuilder {
	protected Command command;
	public AbstractCommandBuilder() {
		// TODO Auto-generated constructor stub
	}
	public Command getCommand()
	{
		return command;
	}
	public abstract void buildCommand();
}

package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;

public abstract class AbstactTypeCommandBuilder {
	protected TypeCommand typeCommand;
	public AbstactTypeCommandBuilder() {
		// TODO Auto-generated constructor stub
	}
	public TypeCommand getTypeCommand()
	{
		return typeCommand;
	}
	public abstract void buildTypeCommand();

}

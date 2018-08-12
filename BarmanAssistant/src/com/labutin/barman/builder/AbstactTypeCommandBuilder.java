package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;

public abstract class AbstactTypeCommandBuilder {
	protected TypeCommand typeCommand;
	public TypeCommand getTypeCommand()
	{
		return typeCommand;
	}
	public abstract void buildTypeCommand();

}

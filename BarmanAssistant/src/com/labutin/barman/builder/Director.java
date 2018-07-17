package com.labutin.barman.builder;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.TypeCommand;

public class Director {

	public Director() {
		// TODO Auto-generated constructor stub
	}

	public static TypeCommand createTypeCommand(AbstactTypeCommandBuilder builder) {
		builder.buildTypeCommand();
		return builder.getTypeCommand();
	}
	public static Command createCommand(AbstractCommandBuilder builder)
	{
		builder.buildCommand();
		return builder.getCommand();
	}

}

package com.labutin.barman.builder;

import java.util.EnumSet;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.TypeCommand;


public class Director {
	
	public static TypeCommand createTypeCommand(AbstactTypeCommandBuilder builder) {
		builder.buildTypeCommand();
		return builder.getTypeCommand();
	}
	public static Command createCommand(AbstractCommandBuilder builder)
	{
		builder.buildCommand();
		return builder.getCommand();
	}
	public static EnumSet<TypeCommand> createEnumTypeCommandSet(AbstractEnumSetBuilder builder)
	{
		builder.buildEnumTypeCommandSet();
		return builder.getEnumTypeCommandSet();
	}
}

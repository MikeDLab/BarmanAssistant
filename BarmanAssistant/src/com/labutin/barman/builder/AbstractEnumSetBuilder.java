package com.labutin.barman.builder;


import java.util.EnumSet;

import com.labutin.barman.command.TypeCommand;
public abstract class AbstractEnumSetBuilder {
	protected EnumSet<TypeCommand> typeCommand;
	public EnumSet<TypeCommand> getEnumTypeCommandSet()
	{
		return typeCommand;
	}
	public abstract void buildEnumTypeCommandSet();
}

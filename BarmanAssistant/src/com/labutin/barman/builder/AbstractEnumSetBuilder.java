package com.labutin.barman.builder;


import java.util.EnumSet;

import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.controller.UserType;
public abstract class AbstractEnumSetBuilder {
	protected EnumSet<TypeCommand> typeCommand;
	public AbstractEnumSetBuilder() {
		// TODO Auto-generated constructor stub
	}
	public EnumSet<TypeCommand> getEnumTypeCommandSet()
	{
		return typeCommand;
	}
	public abstract void buildEnumTypeCommandSet();
}

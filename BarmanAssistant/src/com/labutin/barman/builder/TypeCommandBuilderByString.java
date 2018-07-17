package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;

public class TypeCommandBuilderByString extends AbstactTypeCommandBuilder {
	private String command;
	public TypeCommandBuilderByString(String command) {
		// TODO Auto-generated constructor stub
		this.command = command;
	}
	@Override
	public void buildTypeCommand() {
		typeCommand = TypeCommand.valueOf(command);
	}

}

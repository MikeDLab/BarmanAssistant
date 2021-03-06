package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;

public class TypeCommandBuilder extends AbstactTypeCommandBuilder {
	private String command;
	public TypeCommandBuilder(String command) {
		this.command = command;
	}
	@Override
	public void buildTypeCommand() {
		typeCommand = TypeCommand.valueOf(command.toUpperCase());
	}

}

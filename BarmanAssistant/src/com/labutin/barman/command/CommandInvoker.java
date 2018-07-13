package com.labutin.barman.command;

public class CommandInvoker {

	public CommandInvoker() {
		// TODO Auto-generated constructor stub
	}
	public Command getCommand(TypeCommand typeCommand) {
		//ENUM MAP
		Command command = null;
		switch (typeCommand) {
		case HOME:
			command = new RedirectToHomePageCommand();
			break;
		case SIGNIN:
			command = new RedirectToLoginPageCommand();
			break;
		case REGISTER:
			command = new UserRegistrationCommand();
			break;
//		case LOGIN:
//			command = new UserLoginCommand();
//			break;
		case REGISTERPAGE:
			command = new RedirectToRegisterPageCommand();
			break;
//		case LOADFILE:
//			command = new LoadFileCommand();
//			break;
//		case PARSER:
//			command = new ParserCommand();
//			break;
		default:
			break;
		}
		return command;
	}

}

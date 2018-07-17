package com.labutin.barman.builder;

import com.labutin.barman.command.AddIngredientCommand;
import com.labutin.barman.command.RedirectToAddIngredientPage;
import com.labutin.barman.command.RedirectToHomePageCommand;
import com.labutin.barman.command.RedirectToLoginPageCommand;
import com.labutin.barman.command.RedirectToRegisterPageCommand;
import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.command.UserLoginCommand;
import com.labutin.barman.command.UserRegistrationCommand;

public class CommandBuilderByType extends AbstractCommandBuilder {
	private TypeCommand typeCommand;

	public CommandBuilderByType(TypeCommand typeCommand) {
		// TODO Auto-generated constructor stub
		this.typeCommand = typeCommand;
	}

	@Override
	public void buildCommand() {
		switch (typeCommand) {
		case Home:
			command = new RedirectToHomePageCommand();
			break;
		case SignIn:
			command = new RedirectToLoginPageCommand();
			break;
		case AddIngredient:
			command = new RedirectToAddIngredientPage();
			break;
		case Login:
			command = new UserLoginCommand();
			break;
		case Registration:
			command = new RedirectToRegisterPageCommand();
			break;
		case Register:
			command = new UserRegistrationCommand();
			break;
		case PushIngredient:
			command = new AddIngredientCommand();
			break;
		}
	}

}

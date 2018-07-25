package com.labutin.barman.builder;

import com.labutin.barman.command.AddBarmanRatingCommand;
import com.labutin.barman.command.AddIngredientCommand;
import com.labutin.barman.command.DowngradeToUserCommand;
import com.labutin.barman.command.RedirectToAddCocktailPage;
import com.labutin.barman.command.RedirectToAddIngredientPage;
import com.labutin.barman.command.RedirectToAdminPanel;
import com.labutin.barman.command.RedirectToHomePageCommand;
import com.labutin.barman.command.RedirectToLoginPageCommand;
import com.labutin.barman.command.RedirectToRegisterPageCommand;
import com.labutin.barman.command.RedirectToUserPanelCommand;
import com.labutin.barman.command.ShowBarmanCommand;
import com.labutin.barman.command.ShowIngredientCommand;
import com.labutin.barman.command.ShowUserCommand;
import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.command.UpdateToBarmanCommand;
import com.labutin.barman.command.UserLogOutCommand;
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
		case LogOut:
			command = new UserLogOutCommand();
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
		case ShowIngredient:
			command = new ShowIngredientCommand();
			break;
		case AdminPanel:
			command = new RedirectToAdminPanel();
			break;
		case AddCocktail:
			command = new RedirectToAddCocktailPage();
			break;
		case ShowBarman:
			command = new ShowBarmanCommand();
			break;
		case UserPanel:
			command = new RedirectToUserPanelCommand();
			break;
		case ShowUserList:
			command = new ShowUserCommand();
			break;
		case UpdateToBarman:
			command = new UpdateToBarmanCommand();
			break;
		case DowngradeToUser:
			command = new DowngradeToUserCommand();
			break;
		case AddBarmanRating:
			command = new AddBarmanRatingCommand();
			break;
		}
	}

}

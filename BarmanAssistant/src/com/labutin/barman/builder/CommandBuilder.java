package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.command.cocktail.AddCocktailCommand;
import com.labutin.barman.command.cocktail.ShowCocktailInfo;
import com.labutin.barman.command.cocktail.ShowCocktailList;
import com.labutin.barman.command.ingredient.AddIngredientCommand;
import com.labutin.barman.command.ingredient.ShowIngredientCommand;
import com.labutin.barman.command.redirect.RedirectToAddCocktailPage;
import com.labutin.barman.command.redirect.RedirectToAddIngredientPage;
import com.labutin.barman.command.redirect.RedirectToAdminPanel;
import com.labutin.barman.command.redirect.RedirectToHomePageCommand;
import com.labutin.barman.command.redirect.RedirectToLoginPageCommand;
import com.labutin.barman.command.redirect.RedirectToRegisterPageCommand;
import com.labutin.barman.command.redirect.RedirectToUserPanelCommand;
import com.labutin.barman.command.user.AddBarmanRatingCommand;
import com.labutin.barman.command.user.DowngradeToUserCommand;
import com.labutin.barman.command.user.ShowBarmanCommand;
import com.labutin.barman.command.user.ShowUserCommand;
import com.labutin.barman.command.user.UpdateToBarmanCommand;
import com.labutin.barman.command.user.UserLogOutCommand;
import com.labutin.barman.command.user.UserLoginCommand;
import com.labutin.barman.command.user.UserRegistrationCommand;

public class CommandBuilder extends AbstractCommandBuilder {
	private TypeCommand typeCommand;

	public CommandBuilder(TypeCommand typeCommand) {
		// TODO Auto-generated constructor stub
		this.typeCommand = typeCommand;
	}

	@Override
	public void buildCommand() {
		switch (typeCommand) {
		case HOME:
			command = new RedirectToHomePageCommand();
			break;
		case SIGNIN:
			command = new RedirectToLoginPageCommand();
			break;
		case ADDINGREDIENT:
			command = new RedirectToAddIngredientPage();
			break;
		case LOGIN:
			command = new UserLoginCommand();
			break;
		case LOGOUT:
			command = new UserLogOutCommand();
			break;
		case REGISTRATION:
			command = new RedirectToRegisterPageCommand();
			break;
		case REGISTER:
			command = new UserRegistrationCommand();
			break;
		case PUSHINGREDIENT:
			command = new AddIngredientCommand();
			break;
		case SHOWINGREDIENT:
			command = new ShowIngredientCommand();
			break;
		case ADMINPANEL:
			command = new RedirectToAdminPanel();
			break;
		case ADDCOCKTAIL:
			command = new RedirectToAddCocktailPage();
			break;
		case SHOWBARMAN:
			command = new ShowBarmanCommand();
			break;
		case USERPANEL:
			command = new RedirectToUserPanelCommand();
			break;
		case SHOWUSERLIST:
			command = new ShowUserCommand();
			break;
		case UPDATETOBARMAN:
			command = new UpdateToBarmanCommand();
			break;
		case DOWNGRADETOUSER:
			command = new DowngradeToUserCommand();
			break;
		case ADDBARMANRATING:
			command = new AddBarmanRatingCommand();
			break;
		case PUSHCOCKTAIL:
			command = new AddCocktailCommand();
			break;
		case COCKTAILLIST:
			command = new ShowCocktailList();
			break;
		case COCKTAILINFO:
			command = new ShowCocktailInfo();
			break;
		}
	}

}

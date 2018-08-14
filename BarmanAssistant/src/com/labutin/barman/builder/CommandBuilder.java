package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.command.cocktail.AddCocktailCommand;
import com.labutin.barman.command.cocktail.AddCocktailRatingCommand;
import com.labutin.barman.command.cocktail.DeleteCocktailCommand;
import com.labutin.barman.command.cocktail.PublishCocktailCommand;
import com.labutin.barman.command.cocktail.ShowCocktailImageCommand;
import com.labutin.barman.command.cocktail.ShowCocktailInfoCommand;
import com.labutin.barman.command.cocktail.ShowNotPublishedCocktailListCommand;
import com.labutin.barman.command.cocktail.ShowPublishedCocktailListCommand;
import com.labutin.barman.command.ingredient.AddIngredientCommand;
import com.labutin.barman.command.ingredient.EditIngredientCommand;
import com.labutin.barman.command.ingredient.ShowIngredientCommand;
import com.labutin.barman.command.redirect.RedirectToAddCocktailPageCommand;
import com.labutin.barman.command.redirect.RedirectToAddIngredientPageCommand;
import com.labutin.barman.command.redirect.RedirectToAdminPanelPageCommand;
import com.labutin.barman.command.redirect.RedirectToBarmanPanelPageCommand;
import com.labutin.barman.command.redirect.RedirectToEditIngredientPageCommand;
import com.labutin.barman.command.redirect.RedirectToHomePageCommand;
import com.labutin.barman.command.redirect.RedirectToLoginPageCommand;
import com.labutin.barman.command.redirect.RedirectToRegisterPageCommand;
import com.labutin.barman.command.redirect.RedirectToUserPanelCommand;
import com.labutin.barman.command.user.AddBarmanRatingCommand;
import com.labutin.barman.command.user.DeleteUserCommand;
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
		this.typeCommand = typeCommand;
	}

	@Override
	public void buildCommand() {
		
		switch (typeCommand) {
		case HOME:
			command = new RedirectToHomePageCommand();
			break;
		case SIGN_IN:
			command = new RedirectToLoginPageCommand();
			break;
		case ADD_INGREDIENT:
			command = new RedirectToAddIngredientPageCommand();
			break;
		case LOGIN:
			command = new UserLoginCommand();
			break;
		case LOG_OUT:
			command = new UserLogOutCommand();
			break;
		case REGISTRATION:
			command = new RedirectToRegisterPageCommand();
			break;
		case REGISTER:
			command = new UserRegistrationCommand();
			break;
		case PUSH_INGREDIENT:
			command = new AddIngredientCommand();
			break;
		case SHOW_INGREDIENT:
			command = new ShowIngredientCommand();
			break;
		case ADMIN_PANEL:
			command = new RedirectToAdminPanelPageCommand();
			break;
		case ADD_COCKTAIL:
			command = new RedirectToAddCocktailPageCommand();
			break;
		case SHOW_BARMAN:
			command = new ShowBarmanCommand();
			break;
		case USER_PANEL:
			command = new RedirectToUserPanelCommand();
			break;
		case SHOW_USER_LIST:
			command = new ShowUserCommand();
			break;
		case UPDATE_TO_BARMAN:
			command = new UpdateToBarmanCommand();
			break;
		case DOWNGRADE_TO_USER:
			command = new DowngradeToUserCommand();
			break;
		case ADD_BARMAN_RATING:
			command = new AddBarmanRatingCommand();
			break;
		case PUSH_COCKTAIL:
			command = new AddCocktailCommand();
			break;
		case COCKTAIL_LIST:
			command = new ShowPublishedCocktailListCommand();
			break;
		case COCKTAIL_INFO:
			command = new ShowCocktailInfoCommand();
			break;
		case CHECK_COCKTAIL_LIST:
			command = new ShowNotPublishedCocktailListCommand();
			break;
		case BARMAN_PANEL:
			command = new RedirectToBarmanPanelPageCommand();
			break;
		case PUBLISH_COCKTAIL:
			command = new PublishCocktailCommand();
			break;
		case DELETE_COCKTAIL:
			command = new DeleteCocktailCommand();
			break;
		case DELETE_USER:
			command = new DeleteUserCommand();
			break;
		case EDIT_INGREDIENT_PAGE:
			command = new RedirectToEditIngredientPageCommand();
			break;
		case EDIT_INGREDIENT:
			command = new EditIngredientCommand();
			break;
		case SHOW_COCKTAIL_IMAGE:
			command = new ShowCocktailImageCommand();
			break;
		case ADD_COCKTAIL_RATING:
			command = new AddCocktailRatingCommand();
			break;
		}
	}

}

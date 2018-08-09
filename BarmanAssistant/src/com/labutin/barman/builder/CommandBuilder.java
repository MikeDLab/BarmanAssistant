package com.labutin.barman.builder;

import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.command.cocktail.AddCocktailCommand;
import com.labutin.barman.command.cocktail.AddCocktailRating;
import com.labutin.barman.command.cocktail.DeleteCocktail;
import com.labutin.barman.command.cocktail.PublishCocktail;
import com.labutin.barman.command.cocktail.ShowCocktailImage;
import com.labutin.barman.command.cocktail.ShowCocktailInfo;
import com.labutin.barman.command.cocktail.ShowNotPublishedCocktailList;
import com.labutin.barman.command.cocktail.ShowPublishedCocktailList;
import com.labutin.barman.command.ingredient.AddIngredientCommand;
import com.labutin.barman.command.ingredient.EditIngredient;
import com.labutin.barman.command.ingredient.ShowIngredientCommand;
import com.labutin.barman.command.redirect.RedirectToAddCocktailPage;
import com.labutin.barman.command.redirect.RedirectToAddIngredientPage;
import com.labutin.barman.command.redirect.RedirectToAdminPanel;
import com.labutin.barman.command.redirect.RedirectToBarmanPanel;
import com.labutin.barman.command.redirect.RedirectToEditIngredient;
import com.labutin.barman.command.redirect.RedirectToHomePageCommand;
import com.labutin.barman.command.redirect.RedirectToLoginPageCommand;
import com.labutin.barman.command.redirect.RedirectToRegisterPageCommand;
import com.labutin.barman.command.redirect.RedirectToUserPanelCommand;
import com.labutin.barman.command.user.AddBarmanRatingCommand;
import com.labutin.barman.command.user.DeleteUser;
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
		case SIGN_IN:
			command = new RedirectToLoginPageCommand();
			break;
		case ADD_INGREDIENT:
			command = new RedirectToAddIngredientPage();
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
			command = new RedirectToAdminPanel();
			break;
		case ADD_COCKTAIL:
			command = new RedirectToAddCocktailPage();
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
			command = new ShowPublishedCocktailList();
			break;
		case COCKTAIL_INFO:
			command = new ShowCocktailInfo();
			break;
		case CHECK_COCKTAIL_LIST:
			command = new ShowNotPublishedCocktailList();
			break;
		case BARMAN_PANEL:
			command = new RedirectToBarmanPanel();
			break;
		case PUBLISH_COCKTAIL:
			command = new PublishCocktail();
			break;
		case DELETE_COCKTAIL:
			command = new DeleteCocktail();
			break;
		case DELETE_USER:
			command = new DeleteUser();
			break;
		case EDIT_INGREDIENT_PAGE:
			command = new RedirectToEditIngredient();
			break;
		case EDIT_INGREDIENT:
			command = new EditIngredient();
			break;
		case SHOW_COCKTAIL_IMAGE:
			command = new ShowCocktailImage();
			break;
		case ADD_COCKTAIL_RATING:
			command = new AddCocktailRating();
			break;
		}
	}

}

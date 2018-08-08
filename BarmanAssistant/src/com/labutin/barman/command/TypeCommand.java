package com.labutin.barman.command;

public enum TypeCommand{
	HOME("Home"),
	USER_PANEL("UserPanel"),
	LOG_OUT("LogOut"),
	SIGN_IN("SignIn"),
	ADMIN_PANEL("AdminPanel"),
	ADD_COCKTAIL("AddCocktail"),
	ADD_INGREDIENT("AddIngredient"),
	BARMAN_PANEL("BarmanPanel"),
	LOGIN("Login"),
	REGISTRATION("Registration"),
	REGISTER("Register"),
	PUSH_INGREDIENT("PushIngredient"),
	DELETE_COCKTAIL("DeleteCocktail"),
	DELETE_USER("DeleteUser"),
	SHOW_INGREDIENT("ShowIngredient"),
	SHOW_BARMAN("ShowBarman"),
	SHOW_USER_LIST("ShowUserList"),
	UPDATE_TO_BARMAN("UpdateToBarman"),
	DOWNGRADE_TO_USER("DowngradeToUser"),
	ADD_BARMAN_RATING("AddBarmanRating"),
	PUSH_COCKTAIL("PushCocktail"),
	PUBLISH_COCKTAIL("PublishCocktail"),
	COCKTAIL_LIST("CocktailList"),
	COCKTAIL_INFO("CocktailInfo"),
	CHECK_COCKTAIL_LIST("CheckCocktailList"),
	EDIT_INGREDIENT_PAGE("EditIngredientPage"),
	EDIT_INGREDIENT("EditIngredient");
	private String value;
	private TypeCommand(String value)
	{
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
}

package com.labutin.barman.command;

public enum TypeCommand{
	ADD_BARMAN_RATING("AddBarmanRating"),
	ADD_COCKTAIL("AddCocktail"),
	ADD_COCKTAIL_RATING("AddCocktailRating"),
	ADD_INGREDIENT("AddIngredient"),
	ADMIN_PANEL("AdminPanel"),
	BARMAN_PANEL("BarmanPanel"),
	CHECK_COCKTAIL_LIST("CheckCocktailList"),
	COCKTAIL_INFO("CocktailInfo"),
	COCKTAIL_LIST("CocktailList"),
	DELETE_COCKTAIL("DeleteCocktail"),
	DELETE_USER("DeleteUser"),
	DOWNGRADE_TO_USER("DowngradeToUser"),
	EDIT_INGREDIENT("EditIngredient"),
	EDIT_INGREDIENT_PAGE("EditIngredientPage"),
	HOME("Home"),
	LOG_OUT("LogOut"),
	LOGIN("Login"),
	PUBLISH_COCKTAIL("PublishCocktail"),
	PUSH_COCKTAIL("PushCocktail"),
	PUSH_INGREDIENT("PushIngredient"),
	REGISTER("Register"),
	REGISTRATION("Registration"),
	SHOW_BARMAN("ShowBarman"),
	SHOW_COCKTAIL_IMAGE("ShowCocktailImage"),
	SHOW_INGREDIENT("ShowIngredient"),
	SHOW_USER_LIST("ShowUserList"),
	SIGN_IN("SignIn"),
	UPDATE_TO_BARMAN("UpdateToBarman"),
	USER_PANEL("UserPanel");
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

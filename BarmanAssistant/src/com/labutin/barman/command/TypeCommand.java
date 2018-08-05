package com.labutin.barman.command;

public enum TypeCommand{
	HOME("Home"),
	USERPANEL("UserPanel"),
	LOGOUT("LogOut"),
	SIGNIN("SignIn"),
	ADMINPANEL("AdminPanel"),
	ADDCOCKTAIL("AddCocktail"),
	ADDINGREDIENT("AddIngredient"),
	LOGIN("Login"),
	REGISTRATION("Registration"),
	REGISTER("Register"),
	PUSHINGREDIENT("PushIngredient"),
	SHOWINGREDIENT("ShowIngredient"),
	SHOWBARMAN("ShowBarman"),
	SHOWUSERLIST("ShowUserList"),
	UPDATETOBARMAN("UpdateToBarman"),
	DOWNGRADETOUSER("DowngradeToUser"),
	ADDBARMANRATING("AddBarmanRating"),
	PUSHCOCKTAIL("PushCocktail"),
	COCKTAILLIST("CocktailList"),
	COCKTAILINFO("CocktailInfo");
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

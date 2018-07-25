package com.labutin.barman.command;

public enum TypeCommand{
	Home("Home"),
	UserPanel("UserPanel"),
	LogOut("LogOut"),
	SignIn("SignIn"),
	AdminPanel("AdminPanel"),
	AddCocktail("AddCocktail"),
	AddIngredient("AddIngredient"),
	Login("Login"),
	Registration("Registration"),
	Register("Register"),
	PushIngredient("PushIngredient"),
	ShowIngredient("ShowIngredient"),
	ShowBarman("ShowBarman"),
	ShowUserList("ShowUserList"),
	UpdateToBarman("UpdateToBarman"),
	DowngradeToUser("DowngradeToUser"),
	AddBarmanRating("AddBarmanRating");
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

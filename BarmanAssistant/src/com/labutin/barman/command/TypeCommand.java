package com.labutin.barman.command;

public enum TypeCommand{
	ADDINGREDIENT("AddIngredient"),
	ADDINGREDIENTPAGE("AddIngredient"),
	COCKTAILLIST("CocktailList"),
	HOME("Home"),
	SIGNIN("SignIn"),
	REGISTER("Registration"),
	LOGIN("Login"),
	REGISTERPAGE("RegisterPage"),
	PARSER("Parser"),
	LOADFILE("LoadFile");
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

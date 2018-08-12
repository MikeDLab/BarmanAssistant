package com.labutin.barman.command;

public enum JspParameter {
	INGREDIENT("ingredient"),
	INGREDIENT_ID("ingredient_id"),
	INGREDIENT_NAME("ingredient_name"),
	INGREDIENT_DESCRIPTION("ingredient_description"),
	INGREDIENT_SET("ingredientSet"),
	COCKTAIL("cocktail"),
	COCKTAIL_INGREDIENT_ARRAY("cocktail_ingredients"),
	COCKTAIL_ID("cocktail_id"),
	COCKTAIL_NAME("cocktail_name"),
	COCKTAIL_VOL("cocktail_vol"),
	COCKTAIL_DESCRIPTION("cocktail_description"),
	COCKTAIL_RATING("cocktail_rating"),
	COCKTAIL_USER_MAP("userCocktailMap"),
	COCKTAIL_RATING_MAP("cocktailRatingMap"),
	COCKTAIL_SET("cocktailSet"),
	COCKTAIL_AVERAGE_RATING_MAP("cocktailAverageRatingMap"),
	USER("User"),
	USER_LOGIN("user_login"),
	USER_NAME("user_name"),
	USER_PASSWORD("user_password"),
	USER_EMAIL("user_email"),
	USER_ID("user_id"),
	USER_SET("userSet"),
	USER_RATING_MAP("userRatingMap"),
	USER_AVERAGE_RATING_MAP("userAverageRatingMap"),
	USER_COCKTAIL_NUMBER_MAP("userCocktailNumberMap"),
	BARMAN_ID("barman_id"),
	BARMAN_NAME("barman_name"),
	BARMAN_RATING("barman_rating"),
	BARMAN_SET("setBarman"),
	AUTHOR("author"),
	ERROR_MESSAGE("Errormessage"),
	ROLE("Role"),
	COMMAND("command"),
	LANGUAGE("language"),
	LOCALE("locale");
	private String value;
	private JspParameter(String value)
	{
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
}

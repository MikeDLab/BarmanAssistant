package com.labutin.barman.command;

public enum LocaleKey {
	LOCALE_PROPERTIES("resources.locale"),
	USER_NO_SESSION("user.nosession"),
	GENERAL_EXCEPTION("service.exception"),
	NO_INGREDIENT_NAME("ingredient.noname"),
	NO_IMAGE("image.select"),
	INVALID_USER("invalid.user"),
	INCORRECT_LOGIN("incorrect.login"),
	INCORRECT_NAME("incorrect.name"),
	INCORRECT_PASSWORD("incorrect.password"),
	INCORRECT_EMAIL("incorrect.email"),
	COCKTAIL_WITHOUT_INGREDIENT("cocktail.noingredient");
	private String value;
	private LocaleKey(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
}

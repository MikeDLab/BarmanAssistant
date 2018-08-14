package com.labutin.barman.command;

public enum PageEnum {
	USER_PANEL("jsp/user_panel.jsp"),
	ADMIN_PANEL("/adminpages/admin_panel.jsp"),
	BARMAN_PANEL("/adminpages/barman_panel.jsp"),
	USER_LIST("/adminpages/user_list.jsp"),
	BARMAN_LIST("/jsp/barman_list.jsp"),
	ADD_COCKTAIL("/jsp/add_cocktail.jsp"),
	INGREDIENT_LIST("/jsp/ingredient_list.jsp"),
	ADD_INGRIDIENT_PAGE("/adminpages/add_ingridient.jsp"),
	COCKTAIL_LIST_PAGE("/jsp/cocktail_list.jsp"),
	COCKTAIL_LIST_FOR_BARMAN("/adminpages/cocktail_list.jsp"),
	COCKTAIL_INFO_PAGE("jsp/cocktail_info.jsp"),
	HOME_PAGE("/index.jsp"),
	LOGIN_PAGE("/jsp/login.jsp"),
	REGISTRATION_PAGE("/jsp/register.jsp"),
	EDIT_INGREDIENT_PAGE("/adminpages/edit_ingredient.jsp"),
	ERROR_PAGE("/jsp/error_page.jsp");
	private String value;
	private PageEnum(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
}

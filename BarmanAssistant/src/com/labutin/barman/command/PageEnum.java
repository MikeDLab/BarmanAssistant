package com.labutin.barman.command;

public enum PageEnum {
	ADD_INGRIDIENT_PAGE("/adminpages/add_ingridient.jsp"),
	COCKTAIL_LIST_PAGE("/jsp/cocktail_list.jsp"),
	HOME_PAGE("index.jsp"),
	LOGIN_PAGE("/jsp/login.jsp"),
	REGISTRATION_PAGE("/jsp/register.jsp"),
	LOAD_FILE_PAGE("/WEB-INF/startPage.jsp"),
	SELECT_PARSER_PAGE("/WEB-INF/parser.jsp"),
	XML_OUTPUT_PAGE("/WEB-INF/output.jsp");
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

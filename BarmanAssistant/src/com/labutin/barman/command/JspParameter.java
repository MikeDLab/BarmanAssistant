package com.labutin.barman.command;

public enum JspParameter {
	USER("user"),
	USER_LOGIN("userlogin"),
	USER_NAME("username"),
	USER_PASSWORD("password"),
	USER_EMAIL("email");
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

package com.labutin.barman.repository;

public enum UserQuerry {
	INSERT_USER("INSERT INTO User(user_login, user_password) VALUES (?,?)"),
	FIND_USER_BY_LOGIN("SELECT user_login,user_password FROM User WHERE user_login = ?");
	private String value;
	private UserQuerry(String value)
	{
	this.value = value;	
	}
	public String getValue()
	{
		return value;
	}
}

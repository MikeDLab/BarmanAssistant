package com.labutin.barman.util;

public class XssParser {

	public XssParser() {
		// TODO Auto-generated constructor stub
	}
	public String parse(String str)
	{
		return str.replace('<', '&');
	}
}

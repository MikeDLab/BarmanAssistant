package com.labutin.barman.util;

public class XssParser {
	private final static String REGEX = "[<>'\"]";
	private final static String REPLACEMENT ="&";
	public XssParser() {
		// TODO Auto-generated constructor stub
	}
	public static String parse(String str)
	{
		return str.replaceAll(REGEX, REPLACEMENT);
	}
}

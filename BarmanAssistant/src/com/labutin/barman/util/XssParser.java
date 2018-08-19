package com.labutin.barman.util;

public class XssParser {
	private XssParser() {
		throw new IllegalStateException();
	}

	private static final String REGEX = "[<>\"]";
	private static final String REPLACEMENT = "&";
	public static String parse(String str) {
		return str.replaceAll(REGEX, REPLACEMENT);
	}
}

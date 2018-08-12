package com.labutin.barman.util;

import java.io.UnsupportedEncodingException;

public class XssParser {
	private final static String REGEX = "[<>'\"]";
	private final static String REPLACEMENT = "&";

	public XssParser() {
		// TODO Auto-generated constructor stub
	}

	public static String parse(String str) {
		return str.replaceAll(REGEX, REPLACEMENT);
	}

	public static String decode(String input) {
		try {
			if (input != null) {
				byte[] tmp;

				tmp = input.getBytes("UTF-8");
				return (new String(tmp, "UTF-8"));
			} else {
				return (null);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return (null);
		}
	}

}

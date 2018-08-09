package com.labutin.barman.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_REGEX = "^[_A-Za-z\\d-\\+]+(\\.[_A-Za-z\\d-]+)*@"
			+ "[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";
	private static final String USER_LOGIN_REGEX = "[\\p{Lower}\\p{Upper}\\d]{3,45}";
	private static final String USER_NAME_REGEX = "[a-zA-Z\\._\\-]{3,45}";
	private static final String USER_PASSWORD_REGEX= "((?=.*\\d)(?=.*[a-z]).{6,45})";

	public UserValidator() {
		// TODO Auto-generated constructor stub
	}

	public boolean isEmail(String email) {
		pattern = Pattern.compile(EMAIL_REGEX);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean isLogin(String userLogin) {
		pattern = Pattern.compile(USER_LOGIN_REGEX);
		matcher = pattern.matcher(userLogin);
		return matcher.matches();
	}

	public boolean isName(String userName) {
		pattern = Pattern.compile(USER_NAME_REGEX);
		matcher = pattern.matcher(userName);
		return matcher.matches();
	}
	public boolean isPassword(String userPassword)
	{
		pattern = Pattern.compile(USER_PASSWORD_REGEX);
		matcher = pattern.matcher(userPassword);
		return matcher.matches();
	}
}

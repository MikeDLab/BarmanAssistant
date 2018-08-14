package com.labutin.barman.command;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilCommand {
	protected Locale locale;
	protected  ResourceBundle resourceBundle;
	public UtilCommand() {
		// TODO Auto-generated constructor stub
	}
	protected boolean checkRequestParameterSetOnNull(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> parameterEnumeration = request.getParameterNames();
		String parameterName;
		while (parameterEnumeration.hasMoreElements()) {
			parameterName = parameterEnumeration.nextElement();
			if (request.getParameter(parameterName) == null) {
				return false;
			}
		}
		return true;
	}
	public void init(HttpServletRequest request, HttpServletResponse response) {
		locale = new Locale((String) request.getSession().getAttribute(JspParameter.LANGUAGE.getValue()));
		resourceBundle = ResourceBundle.getBundle(LocaleKey.LOCALE_PROPERTIES.getValue(), locale);
	}

}

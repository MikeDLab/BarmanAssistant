package com.labutin.barman.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;

public class LocaleFilter implements Filter {
	private FilterConfig filterConfig;
	private static Logger logger = LogManager.getLogger();
	private static ArrayList<String> pages; // хранилище страниц

	public LocaleFilter() {
		// TODO Auto-generated constructor stub
		if (pages == null)
			pages = new ArrayList<String>();
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		arg0.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setLocale(request.getLocale());
		logger.info("Locale: " + request.getSession().getAttribute(JspParameter.LANGUAGE.getValue()));
		if (Boolean.parseBoolean(filterConfig.getInitParameter("active"))) {
			String[] list = request.getRequestURI().split("/");
			Locale locale;
			ResourceBundle rb;
			for (String localeLanguage : list) {
				switch (localeLanguage) {
				case "Es":
				case "Ru":
				case "En":
					locale = new Locale(localeLanguage);
					response.setLocale(locale);
					rb = ResourceBundle.getBundle("resources.locale", locale);
					request.getSession(true).setAttribute(JspParameter.LANGUAGE.getValue(), localeLanguage);
					request.getSession(true).setAttribute(JspParameter.LOCALE.getValue(), rb);
					response.sendRedirect(PageEnum.HOME_PAGE.getValue());
					return;
					default:
						if(request.getSession().getAttribute(JspParameter.LANGUAGE.getValue()) == null)
						{
						locale = new Locale("Ru");
						response.setLocale(locale);
						rb = ResourceBundle.getBundle("resources.locale", locale);
						request.getSession(true).setAttribute(JspParameter.LANGUAGE.getValue(), localeLanguage);
						request.getSession(true).setAttribute(JspParameter.LOCALE.getValue(), rb);
						response.sendRedirect(PageEnum.HOME_PAGE.getValue());
						return;
						}
				}
			}
		}
		arg2.doFilter(request, response);
	}

}

package com.labutin.barman.filter;

import java.io.IOException;
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
	private static final String ENCODING = "utf-8";
	private static final String ACTIVATION_FILTER_PARAMETER_NAME = "active";
	private static final String LOCALE_PATH = "resources.locale";
	private static final String RUSSIAN_LANG = "Ru";
	private static final String ENGLISH_LANG = "En";
	private static final String SPANISH_LANG = "Es";

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		arg0.setCharacterEncoding(ENCODING);
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setLocale(request.getLocale());
		logger.info("Locale: " + request.getSession().getAttribute(JspParameter.LANGUAGE.getValue()));
		if (Boolean.parseBoolean(filterConfig.getInitParameter(ACTIVATION_FILTER_PARAMETER_NAME))) {
			String[] list = request.getRequestURI().split("/");
			Locale locale;
			ResourceBundle resourceBundle;
			for (String localeLanguage : list) {
				switch (localeLanguage) {
				case ENGLISH_LANG:
				case RUSSIAN_LANG:
				case SPANISH_LANG:
					locale = new Locale(localeLanguage);
					response.setLocale(locale);
					resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);
					request.getSession(true).setAttribute(JspParameter.LANGUAGE.getValue(), localeLanguage);
					request.getSession(true).setAttribute(JspParameter.LOCALE.getValue(), resourceBundle);
					response.sendRedirect(request.getContextPath() + PageEnum.HOME_PAGE.getValue());
					return;
				default:
					if (request.getSession().getAttribute(JspParameter.LANGUAGE.getValue()) == null) {
						locale = new Locale(RUSSIAN_LANG);
						response.setLocale(locale);
						resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);
						request.getSession(true).setAttribute(JspParameter.LANGUAGE.getValue(), localeLanguage);
						request.getSession(true).setAttribute(JspParameter.LOCALE.getValue(), resourceBundle);
						response.sendRedirect(request.getContextPath() + PageEnum.HOME_PAGE.getValue());
						return;
					}
				}

			}
		}
		arg2.doFilter(request, response);
	}

}

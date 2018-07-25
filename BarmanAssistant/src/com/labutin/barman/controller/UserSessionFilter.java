package com.labutin.barman.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.PageEnum;

@WebFilter(filterName = "UserSessionFilter")
public class UserSessionFilter implements Filter {
	private FilterConfig filterConfig;
	private static Logger logger = LogManager.getLogger();
	public UserSessionFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// Если фильтр активной, то выполнить проверку
		HttpServletRequest request = (HttpServletRequest) arg0;
		logger.info("UserSessionFilet");
		if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
			if (request.getSession().getAttribute("Role") == null) {
				logger.info("UserSession: " + UserType.GUEST);
				request.getSession(true).setAttribute("Role", UserType.GUEST);
			}
			else
			{
				logger.info("UserSession: " + request.getSession().getAttribute("Role"));
			}
		}
		arg2.doFilter(arg0, arg1);
	}

}

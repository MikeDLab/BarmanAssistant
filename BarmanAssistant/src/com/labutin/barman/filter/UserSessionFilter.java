package com.labutin.barman.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.controller.UserType;
import com.labutin.barman.entity.User;

@WebFilter(filterName = "UserSessionFilter")
public class UserSessionFilter implements Filter {
	private FilterConfig filterConfig;
	private static final String ACTIVATION_FILTER_PARAMETER_NAME="active";
	private static Logger logger = LogManager.getLogger();

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (Boolean.parseBoolean(filterConfig.getInitParameter(ACTIVATION_FILTER_PARAMETER_NAME))) {
			if (request.getSession().getAttribute(JspParameter.ROLE.getValue()) == null) {
				logger.info("UserSession: " + UserType.GUEST);
				request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
			} else {
				User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
				if (user != null) {
					if (!user.isAvaible()) {
						request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
						request.getSession().removeAttribute(JspParameter.USER.getValue());
					} else {
						request.getSession().setAttribute(JspParameter.ROLE.getValue(), getUserType(user));
					}
				}
				logger.info("UserSession: " + request.getSession().getAttribute(JspParameter.ROLE.getValue()));
			}
		}
		arg2.doFilter(arg0, arg1);
	}

	private UserType getUserType(User user) {
		switch (user.getUserRole()) {
		case 0:
			return UserType.ADMIN;
		case 1:
			return UserType.BARMAN;
		case 2:
			return UserType.USER;
		default:
			return UserType.GUEST;
		}

	}

}

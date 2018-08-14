package com.labutin.barman.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
import com.labutin.barman.command.LocaleKey;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;

@WebFilter(filterName = "UserSessionFilter")
public class UserSessionFilter implements Filter {
	private FilterConfig filterConfig;
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;

	public UserSessionFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("SessionFilter");
		HttpServletRequest request = (HttpServletRequest) arg0;
		Locale locale = new Locale((String) request.getSession().getAttribute(JspParameter.LANGUAGE.getValue()));
		ResourceBundle resourceBundle = ResourceBundle.getBundle(LocaleKey.LOCALE_PROPERTIES.getValue(), locale);
		if (Boolean.parseBoolean(filterConfig.getInitParameter("active"))) {
			if (request.getSession().getAttribute(JspParameter.ROLE.getValue()) == null) {
				logger.info("UserSession: " + UserType.GUEST);
				request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
			} else {
				try {
					receiver = UserService.getInstance();
					User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
					if (user != null) {
						User userBd = receiver.receiveUserById(user.getUserId());
						if (userBd != null) {
							if (userBd.getUserRole() != user.getUserRole()) {
								UserType userRole = getUserType(userBd);
								request.getSession().setAttribute(JspParameter.ROLE.getValue(), userRole);
								request.getSession().setAttribute(JspParameter.USER.getValue(), userBd);
							} else {
								if (!userBd.isAvaible()) {
									request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
									request.getSession().removeAttribute(JspParameter.USER.getValue());
								}
							}
						} else {
							request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
							request.getSession().removeAttribute(JspParameter.USER.getValue());
						}
					} else {
						request.getSession().setAttribute(JspParameter.ROLE.getValue(), UserType.GUEST);
					}
				} catch (ServiceException | NumberFormatException e) {
					request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
							resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
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

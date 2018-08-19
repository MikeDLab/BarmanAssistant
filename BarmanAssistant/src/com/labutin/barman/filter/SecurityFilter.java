package com.labutin.barman.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.builder.Director;
import com.labutin.barman.builder.EnumSetBuilder;
import com.labutin.barman.builder.TypeCommandBuilder;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.controller.UserType;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
	private FilterConfig filterConfig;
	private static final  String ADMIN_PAGES_DIRECTORY = "adminpages";
	private static final String ACTIVATION_FILTER_PARAMETER_NAME="active";
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		if (Boolean.parseBoolean(filterConfig.getInitParameter(ACTIVATION_FILTER_PARAMETER_NAME))) {
			HttpServletResponse response = (HttpServletResponse) arg1;
			HttpServletRequest req = (HttpServletRequest) arg0;
			UserType type = (UserType) req.getSession().getAttribute(JspParameter.ROLE.getValue());
			if (type != null) {
				EnumSetBuilder builder = new EnumSetBuilder(type);
				EnumSet<TypeCommand> commands = Director.createEnumTypeCommandSet(builder);
				String commandName = req.getParameter(JspParameter.COMMAND.getValue());
				if (commandName != null) {
					TypeCommandBuilder typeBuilder = new TypeCommandBuilder(commandName);
					try {
						TypeCommand commandType = Director.createTypeCommand(typeBuilder);
						if (!commands.contains(commandType)) {
							response.sendRedirect(req.getContextPath() + PageEnum.HOME_PAGE.getValue());
							return;
						}
					} catch (IllegalArgumentException e) {
						response.sendRedirect(req.getContextPath() + PageEnum.HOME_PAGE.getValue());
						return;
					}
				}
			}
			ArrayList<String> pageList = new ArrayList<>(Arrays.asList(req.getRequestURI().split("/")));
			if (pageList.contains(ADMIN_PAGES_DIRECTORY)) {
				response.sendRedirect(req.getContextPath() + PageEnum.HOME_PAGE.getValue());
				return;
			}

		}
		arg2.doFilter(arg0, arg1);
	}

}

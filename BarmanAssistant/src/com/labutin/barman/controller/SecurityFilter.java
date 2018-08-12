package com.labutin.barman.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

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

import com.labutin.barman.builder.Director;
import com.labutin.barman.builder.EnumSetBuilder;
import com.labutin.barman.builder.TypeCommandBuilder;
import com.labutin.barman.command.Command;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.TypeCommand;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
	private FilterConfig filterConfig;
	private static ArrayList<String> pages; // хранилище страниц

	public SecurityFilter() {
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
		// Если фильтр активной, то выполнить проверку
		System.out.println("SecurityFilter");
		if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
			HttpServletResponse response = (HttpServletResponse) arg1;
			HttpServletRequest req = (HttpServletRequest) arg0;
			UserType type = (UserType) req.getSession().getAttribute(JspParameter.ROLE.getValue());
			if (type != null) {
				EnumSetBuilder builder = new EnumSetBuilder(type);
				EnumSet<TypeCommand> commands = Director.createEnumTypeCommandSet(builder);
				String commandName = req.getParameter(JspParameter.COMMAND.getValue());
				if (commandName != null) {
					TypeCommandBuilder typeBuilder = new TypeCommandBuilder(commandName);
					TypeCommand commandType = Director.createTypeCommand(typeBuilder);
					if (!commands.contains(commandType)) {
						response.sendRedirect(req.getContextPath() + "/index.jsp");
						return;
					}
				}
			}
			// Раскладываем адрес на составляющие
			ArrayList<String> pageList = new ArrayList<>(Arrays.asList(req.getRequestURI().split("/")));
//			for (String page : pageList) {
//				System.out.println("Page: " + page);
//			}
			if (pageList.contains("adminpages")) {
				response.sendRedirect(req.getContextPath() + "/index.jsp");
				return;
			}

		}
		arg2.doFilter(arg0, arg1);
	}

}

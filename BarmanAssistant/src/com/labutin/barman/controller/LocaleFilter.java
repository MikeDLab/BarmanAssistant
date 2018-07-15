package com.labutin.barman.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/app/*"})
public class LocaleFilter implements Filter {

	public LocaleFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("using filt");
		  HttpServletResponse resp = (HttpServletResponse) arg1;
	        HttpServletRequest req = (HttpServletRequest) arg0;

	        req.getSession().setAttribute("language", "ru");
	        arg2.doFilter(arg0, arg1);
	}

}

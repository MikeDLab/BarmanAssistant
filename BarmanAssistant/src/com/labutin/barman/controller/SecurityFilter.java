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

import com.labutin.barman.command.PageEnum;

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
			System.out.println("Filert usss");
			HttpServletRequest req = (HttpServletRequest) arg0;
			// Раскладываем адрес на составляющие
			System.out.println(req.getRequestURI());
			System.out.println(req.getRequestURL());
			String[] list = req.getRequestURI().split("/");
			for(String l: list)
			{
				System.out.println("Page: " + l);
			}
			// Извлекаем наименование страницы
			String page = null;
			if (list[list.length - 1].indexOf(".jsp") > 0) {
				page = list[list.length - 1];
			}
			// Если открывается главная страница, то выполняем проверку
			if ((page != null) && page.equalsIgnoreCase("index.jsp")) {
				// Если была предварительно открыта одна из страниц
				// login.jsp или registration.jsp, то передаем управление
				// следующему элементу цепочки фильтра
				if (pages.contains("login.jsp") || pages.contains("register.jsp")) {
					arg2.doFilter(arg0, arg1);
					return;
				} else {
					// Перенаправление на страницу login.jsp
					ServletContext ctx = filterConfig.getServletContext();
					arg0.getRequestDispatcher(PageEnum.HOME_PAGE.getValue()).forward(arg0, arg1);
					RequestDispatcher dispatcher = ctx.getRequestDispatcher("index.jsp");
					dispatcher.forward(arg0, arg1);
					return;
				}
			} else if (page != null) {
				// Добавляем страницу в список
				if (!pages.contains(page))
					pages.add(page);
			}
		}
		arg2.doFilter(arg0, arg1);
	}

}

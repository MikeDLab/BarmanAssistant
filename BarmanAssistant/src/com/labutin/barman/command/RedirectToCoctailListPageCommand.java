package com.labutin.barman.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToCoctailListPageCommand implements Command {
	public RedirectToCoctailListPageCommand() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.COCKTAIL_LIST_PAGE;
	}

}

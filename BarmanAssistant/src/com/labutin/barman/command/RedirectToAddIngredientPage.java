package com.labutin.barman.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToAddIngredientPage implements Command {
	public RedirectToAddIngredientPage() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.ADD_INGRIDIENT_PAGE;
	}

}
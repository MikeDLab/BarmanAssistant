package com.labutin.barman.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToAddCocktailPage implements Command {
	private String adres;
	public RedirectToAddCocktailPage() {
		// TODO Auto-generated constructor stub
	}
	public RedirectToAddCocktailPage(String adres)
	{
		this.adres = adres;
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.ADD_COCKTAIL;
	}

}

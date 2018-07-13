package com.labutin.barman.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToRegisterPageCommand implements Command {
	private String adres;
	public RedirectToRegisterPageCommand() {
		// TODO Auto-generated constructor stub
	}
	public RedirectToRegisterPageCommand(String adres)
	{
		this.adres = adres;
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.REGISTRATION_PAGE;
	}

}

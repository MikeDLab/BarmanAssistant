package com.labutin.barman.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToUserPanelCommand implements Command {
	private String adres;
	public RedirectToUserPanelCommand() {
		// TODO Auto-generated constructor stub
	}
	public RedirectToUserPanelCommand(String adres)
	{
		this.adres = adres;
	}
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return PageEnum.USER_PANEL;
	}

}

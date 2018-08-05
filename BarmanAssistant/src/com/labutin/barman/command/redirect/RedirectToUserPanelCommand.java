package com.labutin.barman.command.redirect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;

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

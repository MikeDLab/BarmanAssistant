package com.labutin.barman.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
public class MailCommand implements Command {
	//Mail util
	private MailReceiver receiver = new MailReceiver();
	public MailCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String toWhom = request.getParameter(JspParameter.USER_EMAIL.getValue());
		String userName = request.getParameter(JspParameter.USER_NAME.getValue());
		receiver.sendEmail(toWhom, userName);
		return PageEnum.LOAD_FILE_PAGE;
	}
}

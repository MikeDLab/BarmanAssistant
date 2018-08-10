package com.labutin.barman.command.user;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
public class DeleteUser extends AbstractUserCommand {
	public DeleteUser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		userUtil.delete(request, response);
		userUtil.showUserSet(request, response);
		return PageEnum.USER_LIST;
	}

}

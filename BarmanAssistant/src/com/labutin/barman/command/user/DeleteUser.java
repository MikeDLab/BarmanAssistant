package com.labutin.barman.command.user;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;

public class DeleteUser implements Command {
	private UserService receiver;

	public DeleteUser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		try {
			receiver = new UserService();
			receiver.removeUser(userId);
		} catch (ServiceException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ShowUserCommand().execute(request, response);

	}

}

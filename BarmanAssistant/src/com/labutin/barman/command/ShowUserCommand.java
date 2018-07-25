package com.labutin.barman.command;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.UserService;

public class ShowUserCommand implements Command {
	private UserService receiver;

	public ShowUserCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			Set<User> setUser = receiver.receiveAllUsers();
			request.setAttribute("setUser", setUser);
			System.out.println("good");
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageEnum.USER_LIST;

	}

}

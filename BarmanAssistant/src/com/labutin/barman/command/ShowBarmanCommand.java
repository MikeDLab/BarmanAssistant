package com.labutin.barman.command;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

public class ShowBarmanCommand implements Command {
	private UserService receiver;

	public ShowBarmanCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			User user = (User) request.getSession().getAttribute("User");
			Set<User> setBarman = receiver.receiveBarman(user.getUserId());
			request.setAttribute("setBarman", setBarman);
//			System.out.println("Current user: " + user);

			System.out.println("good");
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageEnum.BARMAN_LIST;

	}

}

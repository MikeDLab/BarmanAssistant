package com.labutin.barman.command.user;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
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
			Set<User> setBarman = null;
			User user = (User) request.getSession().getAttribute("User");
			if(user != null)
			{
				setBarman = receiver.receiveBarman(user.getUserId());
			}			
			request.setAttribute("setBarman", setBarman);
			System.out.println("good");
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageEnum.BARMAN_LIST;

	}

}

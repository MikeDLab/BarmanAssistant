package com.labutin.barman.command;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

public class DowngradeToUserCommand implements Command {
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;

	public DowngradeToUserCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiver.downgradeToUser(Integer.parseInt(request.getParameter("userId")));
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ShowUserCommand().execute(request, response);

	}

}

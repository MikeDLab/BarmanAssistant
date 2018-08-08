package com.labutin.barman.command.user;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

public class AddBarmanRatingCommand implements Command {
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;

	public AddBarmanRatingCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			User user = (User) request.getSession().getAttribute("User");
			int barmanId = Integer.parseInt(request.getParameter("barmanid"));
			int barmanRating = Integer.parseInt(request.getParameter("cocktailvol"));
			receiver.addBarmanRating(barmanRating, barmanId, user.getUserId());
		//	System.out.println("good");
		}catch(ServiceException e) {
			// TODO: handle exception
		}
		return new ShowBarmanCommand().execute(request, response);
	}

}

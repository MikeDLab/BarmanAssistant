package com.labutin.barman.command.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.RatingService;
import com.labutin.barman.service.UserService;

public class ShowBarmanCommand implements Command {
	private UserService receiver;
	private RatingService receiverRating;
	public ShowBarmanCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			receiverRating = new RatingService();
			Set<User> setBarman = null;
			Set<Rating> setRating = null;
			User user = (User) request.getSession().getAttribute("User");
			if(user != null)
			{
				setBarman = receiver.receiveBarman(user.getUserId());
				setRating = receiverRating.receiveBarmanRatingSet(user.getUserId());
				Map<User,Rating> userRatingMap = new HashMap<>();
				for(User usr : setBarman)
				{
					userRatingMap.put(usr, null);
					for(Rating rating: setRating)
					{
						if(usr.getUserId() == rating.getEstimated())
						{
							userRatingMap.replace(usr, rating);
						}
					}
				}
				request.setAttribute("userRatingMap", userRatingMap);
			}
			request.setAttribute("setBarman", setBarman);
		} catch (ServiceException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageEnum.BARMAN_LIST;

	}

}

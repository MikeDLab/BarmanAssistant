package com.labutin.barman.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.user.UpdateToBarmanCommand;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.repository.UserRepositoryImpl;
import com.labutin.barman.repository.UtilRepository;
import com.labutin.barman.specification.ingredient.FindIngredientSet;
import com.labutin.barman.specification.rating.FindBarmanRatingSet;
import com.labutin.barman.specification.user.AddBarmanRating;
import com.labutin.barman.specification.user.DowngradeBarmanToUser;
import com.labutin.barman.specification.user.FindBarmanSet;
import com.labutin.barman.specification.user.FindCocktailAuthrorSet;
import com.labutin.barman.specification.user.FindUserById;
import com.labutin.barman.specification.user.FindUserByLoginAndPassword;
import com.labutin.barman.specification.user.FindUserSet;
import com.labutin.barman.specification.user.UpdateUserToBarman;

public class RatingService {
	private static Logger logger = LogManager.getLogger();
	private final UtilRepository utilRepository;

	public RatingService() throws ServiceException{
		try {
			utilRepository = UtilRepository.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(); 
		}
	}
	public Set<Rating> receiveBarmanRatingSet(int userId) throws ServiceException
	{
		try {
			return utilRepository.querry(new FindBarmanRatingSet(userId));
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			throw new ServiceException();
		}
	}
	public void remove(int userId)
	{
	}
}

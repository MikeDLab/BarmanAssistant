package com.labutin.barman.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.repository.RatingRepository;
import com.labutin.barman.specification.rating.FindBarmanRatingSet;

public class RatingService {
	private static Logger logger = LogManager.getLogger();
	private final RatingRepository utilRepository;

	public RatingService() throws ServiceException{
		try {
			utilRepository = RatingRepository.getInstance();
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(); 
		}
	}
	public Set<Rating> receiveBarmanRatingSet(int userId) throws ServiceException
	{
		try {
			return utilRepository.query(new FindBarmanRatingSet(userId));
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new ServiceException();
		}
	}
	public void remove(int userId)
	{
	}
}

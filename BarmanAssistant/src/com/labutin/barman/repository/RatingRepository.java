package com.labutin.barman.repository;
import java.util.Set;

import javax.management.Query;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.specification.Specification;
public class RatingRepository {

	public RatingRepository() {
		// TODO Auto-generated constructor stub
	}
	private static class SingletonHandler {
		private final static RatingRepository INSTANCE = new RatingRepository();
	}
	public static RatingRepository getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}
	public Set<Rating> query(Specification<Rating> specification) throws RepositoryException
	{
			return specification.querry();
	}
}

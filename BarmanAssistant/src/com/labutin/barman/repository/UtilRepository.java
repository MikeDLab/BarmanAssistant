package com.labutin.barman.repository;
import java.util.Set;

import javax.management.Query;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.specification.Specification;
public class UtilRepository {

	public UtilRepository() {
		// TODO Auto-generated constructor stub
	}
	private static class SingletonHandler {
		private final static UtilRepository INSTANCE = new UtilRepository();
	}
	public static UtilRepository getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}
	public Set<Rating> querry(Specification<Rating> specification) throws EntityException
	{
			return specification.querry();
	}
}

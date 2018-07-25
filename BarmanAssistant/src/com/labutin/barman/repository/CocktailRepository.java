package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.FindIngredientByName;
import com.labutin.barman.specification.FindUserByLogin;
import com.labutin.barman.specification.Specification;

public class CocktailRepository implements IСocktailRepository {
	private static Logger logger = LogManager.getLogger();
	private final static String INSERT_COCKTAIL = "INSERT INTO Cocktail(cocktail_name, user_id, cocktail_description, cocktail_vol) VALUES (?,?,?,?)";
	private final static String REMOVE_COCKTAIL = "DELETE FROM Ingredient WHERE Ingredient_name = ?";
	private CocktailRepository() {
		// TODO Auto-generated constructor stub
	}

	private static class SingletonHandler {
		private final static CocktailRepository INSTANCE = new CocktailRepository();
	}

	public static CocktailRepository getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}

	@Override
	public void add(Cocktail item) {
		ProxyConnection connection;
		PreparedStatement preparedStatement;
		// TODO Auto-generated method stub
		logger.info(item + " try to register");
//		if (query(new FindIngredientByName(item.getIngredientName())) == null) {
		try {
			connection = PoolConnection.POOL.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_COCKTAIL);
			if (preparedStatement != null) {
				preparedStatement.setString(1, item.getCocktailName());
				preparedStatement.setInt(2, item.getUserId());
				preparedStatement.setString(3, item.getCocktailDescription());
				preparedStatement.setInt(4, item.getCocktailVol());
				preparedStatement.executeUpdate();
				connection.close();
				logger.info(item + " inseted");
			}
		} catch (SQLException e) {
			logger.info(item + " has problem");
		}
//		}
	}

	@Override
	public void remove(Cocktail item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Cocktail> query(Specification<Cocktail> specification) {
		// TODO Auto-generated method stub
		return specification.querry();
	}

//
//	@Override
//	public void remove(Ingredient item) {
//		// TODO Auto-generated method stub
//		connection = PoolConnection.POOL.getConnection();
//		try {
//			preparedStatement = connection.prepareStatement(REMOVE_INGREDIENT);
//			preparedStatement.setString(1, item.getIngredientName());
//			preparedStatement.executeUpdate();
//			connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//		}
//
//	}

}

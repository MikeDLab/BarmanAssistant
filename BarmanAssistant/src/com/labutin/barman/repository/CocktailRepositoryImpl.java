package com.labutin.barman.repository;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.cocktail.CocktailSpecification;
import com.mysql.jdbc.Statement;

public class CocktailRepositoryImpl implements СocktailRepository {
	private static Logger logger = LogManager.getLogger();
	private final static String INSERT_COCKTAIL = "INSERT INTO Cocktail(cocktail_name, user_id, cocktail_description, cocktail_vol,cocktail_isPublished,cocktail_img) VALUES (?,?,?,?,?,?)";
	private final static String SET_COCKTAIL = "UPDATE Cocktail SET cocktail_isPublished=1 WHERE cocktail_id = ?";
	private final static String SET_COCKTAIL_IMAGE = "UPDATE Cocktail SET cocktail_img= ? WHERE cocktail_id = ?";
	private final static String DELETE_COCKTAIL = "DELETE FROM Cocktail Where cocktail_id = ?";
	private CocktailRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	private static class SingletonHandler {
		private final static CocktailRepositoryImpl INSTANCE = new CocktailRepositoryImpl();
	}

	public static CocktailRepositoryImpl getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}
	public void addImage(int cocktailId, InputStream inputStream)
	{
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SET_COCKTAIL_IMAGE);){
			if (preparedStatement != null) {
				preparedStatement.setBlob(1,inputStream);
				preparedStatement.setInt(2, cocktailId);
				preparedStatement.executeUpdate();
			}
			logger.info("GOOD");
		} catch (SQLException e) {
			logger.info(" has problem");
		}
	}
	@Override
	public void add(Cocktail item) {
		ProxyConnection connection;
		PreparedStatement preparedStatement;
		// TODO Auto-generated method stub
		logger.info(item + " try to register");
		try {
			connection = PoolConnection.POOL.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_COCKTAIL, Statement.RETURN_GENERATED_KEYS);
			if (preparedStatement != null) {
				preparedStatement.setString(1, item.getCocktailName());
				preparedStatement.setInt(2, item.getUserId());
				preparedStatement.setString(3, item.getCocktailDescription());
				preparedStatement.setInt(4, item.getCocktailVol());
				preparedStatement.setBoolean(5, item.getIsPublished());
				preparedStatement.setBlob(6, item.getImage());
				int affectedRows = preparedStatement.executeUpdate();
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						item.setCocktailId(generatedKeys.getInt(1));
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
				connection.close();
				logger.info(item + " inseted");
			}
		} catch (SQLException e) {
			logger.info(item + " has problem");
		}
	}

	@Override
	public void remove(Cocktail item) {
		// TODO Auto-generated method stub
		logger.info(" try to update");
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COCKTAIL);){
			if (preparedStatement != null) {
				preparedStatement.setInt(1,item.getCocktailId());
				preparedStatement.executeUpdate();
			}
			logger.info("GOOD");
		} catch (SQLException e) {
			logger.info(" has problem");
		}
	}

	@Override
	public Set<Cocktail> query(CocktailSpecification specification) throws RepositoryException {
		// TODO Auto-generated method stub
		return specification.querry();
	}

	public void setPublished(int cocktailId) {
		// TODO Auto-generated method stub
		logger.info(" try to update");
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SET_COCKTAIL);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1,cocktailId);
				preparedStatement.executeUpdate();
			}
			logger.info("GOOD");
		} catch (SQLException e) {
			logger.info(" has problem");
		}
	}
}

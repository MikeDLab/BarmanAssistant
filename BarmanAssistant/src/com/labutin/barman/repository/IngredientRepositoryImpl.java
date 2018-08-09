package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.ingredient.FindIngredientByName;
import com.labutin.barman.specification.ingredient.IngredientSpecification;

public class IngredientRepositoryImpl implements IngredientRepository {
	private static Logger logger = LogManager.getLogger();
	private final static String INSERT_INGREDIENT = "INSERT INTO Ingredient(Ingredient_name, Ingredient_description) VALUES (?,?)";
	private final static String REMOVE_INGREDIENT = "DELETE FROM Ingredient WHERE Ingredient_name = ?";
	private final static String UPDATE_INGREDIENT = "Update Ingredient Set Ingredient_name = ? ,Ingredient_description= ? Where Ingredient_id= ?";

	private IngredientRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	private static class SingletonHandler {
		private final static IngredientRepositoryImpl INSTANCE = new IngredientRepositoryImpl();
	}

	public static IngredientRepositoryImpl getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}

	@Override
	public void add(Ingredient item) throws RepositoryException {

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INGREDIENT);) {
			if (query(new FindIngredientByName(item.getIngredientName())) == null) {
				if (preparedStatement != null) {
					preparedStatement.setString(1, item.getIngredientName());
					preparedStatement.setString(2, item.getIngredientDescription());
					preparedStatement.executeUpdate();
					logger.info(item + " inseted");
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public void remove(Ingredient item) throws RepositoryException {

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_INGREDIENT);) {
			preparedStatement.setString(1, item.getIngredientName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public Set<Ingredient> query(IngredientSpecification specification) throws RepositoryException {
		// TODO Auto-generated method stub
		return specification.querry();
	}

	public void update(Ingredient item) throws RepositoryException {

		// TODO Auto-generated method stub
		System.out.println("Try to change ");
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INGREDIENT);) {
			preparedStatement.setString(1, item.getIngredientName());
			preparedStatement.setString(2, item.getIngredientDescription());
			preparedStatement.setInt(3, item.getIngredientId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RepositoryException(e);
		}
	}

}

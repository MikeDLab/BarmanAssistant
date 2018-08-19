package com.labutin.barman.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.ingredient.FindIngredientByName;
import com.labutin.barman.specification.ingredient.IngredientSpecification;

public class IngredientRepositoryImpl implements IngredientRepository {
	private static class SingletonHandler {
		private static final IngredientRepositoryImpl INSTANCE = new IngredientRepositoryImpl();
	}

	private static final String INSERT_INGREDIENT = "INSERT INTO Ingredient(Ingredient_name, Ingredient_description) VALUES (?,?)";
	private static final String REMOVE_INGREDIENT = "DELETE FROM Ingredient WHERE Ingredient_name = ?";

	private static final String UPDATE_INGREDIENT = "UPDATE Ingredient SET Ingredient_name = ? ,Ingredient_description= ? WHERE Ingredient_id= ?";

	public static IngredientRepositoryImpl getInstance() {
		return SingletonHandler.INSTANCE;
	}

	private IngredientRepositoryImpl() {

	}

	@Override
	public void add(Ingredient item) throws RepositoryException {

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INGREDIENT);) {
			if (!query(new FindIngredientByName(item.getIngredientName())).iterator().hasNext()
					&& preparedStatement != null) {
				preparedStatement.setString(1, item.getIngredientName());
				preparedStatement.setString(2, item.getIngredientDescription());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Set<Ingredient> query(IngredientSpecification specification) throws RepositoryException {
		return specification.query();
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

	public void update(Ingredient item) throws RepositoryException {

		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INGREDIENT);) {
			preparedStatement.setString(1, item.getIngredientName());
			preparedStatement.setString(2, item.getIngredientDescription());
			preparedStatement.setInt(3, item.getIngredientId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

}

package com.labutin.barman.repository;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.cocktail.CocktailSpecification;
import com.mysql.jdbc.Statement;

public class CocktailRepositoryImpl implements СocktailRepository {
	private static class SingletonHandler {
		private static final CocktailRepositoryImpl INSTANCE = new CocktailRepositoryImpl();
	}

	private static final String DELETE_COCKTAIL = "DELETE FROM Cocktail Where cocktail_id = ?";
	private static final String INSERT_COCKTAIL = "INSERT INTO Cocktail(cocktail_name, user_id, cocktail_description, cocktail_vol,cocktail_isPublished,cocktail_img) VALUES (?,?,?,?,?,?)";
	private static final String SET_COCKTAIL = "UPDATE Cocktail SET cocktail_isPublished=1 WHERE cocktail_id = ?";

	private static final String SET_COCKTAIL_IMAGE = "UPDATE Cocktail SET cocktail_img= ? WHERE cocktail_id = ?";

	public static CocktailRepositoryImpl getInstance() {

		return SingletonHandler.INSTANCE;

	}

	private CocktailRepositoryImpl() {
	}

	@Override
	public void add(Cocktail item) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COCKTAIL,
						Statement.RETURN_GENERATED_KEYS);) {
			if (preparedStatement != null) {
				preparedStatement.setString(1, item.getCocktailName());
				preparedStatement.setInt(2, item.getUserId());
				preparedStatement.setString(3, item.getCocktailDescription());
				preparedStatement.setInt(4, item.getCocktailVol());
				preparedStatement.setBoolean(5, item.getIsPublished());
				preparedStatement.setBlob(6, item.getImage());
				preparedStatement.executeUpdate();
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					item.setCocktailId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Add cocktail failed");
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	public void addImage(int cocktailId, InputStream inputStream) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SET_COCKTAIL_IMAGE);) {
			if (preparedStatement != null) {
				preparedStatement.setBlob(1, inputStream);
				preparedStatement.setInt(2, cocktailId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Set<Cocktail> query(CocktailSpecification specification) throws RepositoryException {
		return specification.query();
	}

	@Override
	public void remove(int cocktailId) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COCKTAIL);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, cocktailId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	public void setPublished(int cocktailId) throws RepositoryException {
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SET_COCKTAIL);) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, cocktailId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}
}

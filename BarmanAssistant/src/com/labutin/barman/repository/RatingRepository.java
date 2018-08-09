package com.labutin.barman.repository;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import com.labutin.barman.entity.Rating;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.pool.ProxyConnection;
import com.labutin.barman.specification.Specification;
public class RatingRepository {

	public RatingRepository() {
		// TODO Auto-generated constructor stub
	}
	private static class SingletonHandler {
		private final static RatingRepository INSTANCE = new RatingRepository();
	}
	private final static String INSERT_BARMAN_RATING = "INSERT INTO BarmanRating(barman_rating, barman_id, user_id) VALUES (?,?,?)";
	private final static String INSERT_COCKTAIL_RATING = "INSERT INTO CocktailRating(user_id, cocktail_id, cocktail_rating) VALUES (?,?,?)";
	public static RatingRepository getInstance() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		PoolConnection pool = PoolConnection.POOL;
		pool.initialization();
		return SingletonHandler.INSTANCE;
	}
	public Set<Rating> query(Specification<Rating> specification) throws RepositoryException
	{
			return specification.querry();
	}
	public void addBarmanRating(Rating item) throws RepositoryException
	{
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BARMAN_RATING)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, item.getRating());
				preparedStatement.setInt(2, item.getEstimated());
				preparedStatement.setInt(3, item.getEstimating());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}
	public void addCocktailRating(Rating item) throws RepositoryException
	{
		try (ProxyConnection connection = PoolConnection.POOL.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COCKTAIL_RATING)) {
			if (preparedStatement != null) {
				preparedStatement.setInt(1, item.getEstimating());
				preparedStatement.setInt(2, item.getEstimated());
				preparedStatement.setInt(3, item.getRating());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}
}

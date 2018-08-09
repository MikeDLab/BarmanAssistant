package com.labutin.barman.specification.cocktail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.entity.Cocktail;


public abstract class AbstractCocktailSpecification {
	private static Logger logger = LogManager.getLogger();
	protected ResultSet resultSet;
	public AbstractCocktailSpecification() {
		// TODO Auto-generated constructor stub
	}

	protected void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Close result set",e);
			}
		}
	}

	protected Cocktail loadCocktailData(ResultSet resultSet) {
		Cocktail cocktail = null;
		try {
			if (resultSet != null) {
				cocktail = new Cocktail();
				cocktail.setCocktailId(resultSet.getInt("cocktail_id"));
				cocktail.setCocktailName(resultSet.getString("cocktail_name"));
				cocktail.setCocktailDescription(resultSet.getString("cocktail_description"));
				cocktail.setUserId(resultSet.getInt("user_id"));
				cocktail.setCocktailVol(resultSet.getInt("cocktail_vol"));
				cocktail.setIsPublished(resultSet.getBoolean("cocktail_isPublished"));
				cocktail.setImage(resultSet.getBlob("cocktail_img").getBinaryStream());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Load from  result set",e);
		}
		return cocktail;
	}
}

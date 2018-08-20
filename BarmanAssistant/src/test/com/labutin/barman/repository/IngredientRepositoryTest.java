package test.com.labutin.barman.repository;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.repository.IngredientRepositoryImpl;
import com.labutin.barman.specification.ingredient.FindIngredientById;
import com.labutin.barman.specification.ingredient.FindIngredientByName;
import com.labutin.barman.specification.ingredient.FindIngredientSet;
import com.labutin.barman.specification.ingredient.FindIngredientSetByCocktailId;

public class IngredientRepositoryTest {
	private IngredientRepositoryImpl repository = IngredientRepositoryImpl.getInstance();
	private PoolConnection pool = PoolConnection.POOL;

	@BeforeClass
	public void initPool() throws NoJDBCDriverException, NoJDBCPropertiesFileException {
		pool.initialization();
	}

	@Test
	public void findIngredientById() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		int ingredientId = 16;
		Ingredient ingrdient = repository.query(new FindIngredientById(ingredientId)).iterator().next();
		assertEquals(ingrdient.getIngredientName(), "Light  Beer");
	}
	@Test
	public void findIngredientByName() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		String ingredientName = "Light  Beer";
		Ingredient ingrdient = repository.query(new FindIngredientByName(ingredientName)).iterator().next();
		assertEquals(ingrdient.getIngredientId(), 16);
	}
	@Test
	public void findIngredientSet() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		Set<Ingredient> setIngredient  = repository.query(new FindIngredientSet());
		assertEquals(setIngredient.size(), 22);
	}
	@Test
	public void findIngredientSetByCocktailId() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		int cocktailId = 77;
		Set<Ingredient> setIngredient  = repository.query(new FindIngredientSetByCocktailId(cocktailId));
		assertEquals(setIngredient.size(), 2);
	}
}

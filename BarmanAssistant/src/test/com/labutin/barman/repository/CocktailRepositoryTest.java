package test.com.labutin.barman.repository;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.repository.CocktailRepositoryImpl;
import com.labutin.barman.specification.cocktail.FindCocktailById;
import com.labutin.barman.specification.cocktail.FindCocktailForBarmenAccept;
import com.labutin.barman.specification.cocktail.FindCocktailPublishedSet;
import com.labutin.barman.specification.cocktail.FindCocktailSetByBarmanId;

public class CocktailRepositoryTest {
	private CocktailRepositoryImpl repository = CocktailRepositoryImpl.getInstance();
	private PoolConnection pool = PoolConnection.POOL;
	@BeforeClass
	public void initPool() throws NoJDBCDriverException, NoJDBCPropertiesFileException
	{
		pool.initialization();
	}
	@Test
	public void findCocktailById() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		int cocktailId = 72;
		Cocktail cocktail = repository.query(new FindCocktailById(cocktailId)).iterator().next();
		assertEquals(cocktail.getCocktailName(), "B-52");
	}
	@Test
	public void findCocktailSetByBarmanId() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		int barmanId = 26;
		Set<Cocktail> cockailSet = repository.query(new FindCocktailSetByBarmanId(barmanId));
		assertEquals(cockailSet.size(), 2);
	}
	@Test
	public void findCocktailSetForBarmanAccept() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		Set<Cocktail> cockailSet = repository.query(new FindCocktailForBarmenAccept());
		assertEquals(cockailSet.size(), 1);
	}
	@Test
	public void findCocktailPublishedSet() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		Set<Cocktail> cockailSet = repository.query(new FindCocktailPublishedSet());
		assertEquals(cockailSet.size(), 7);
	}
}

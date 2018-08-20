package test.com.labutin.barman.repository;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.repository.UserRepositoryImpl;
import com.labutin.barman.specification.user.FindBarmanSet;
import com.labutin.barman.specification.user.FindUserById;
import com.labutin.barman.specification.user.FindUserByLogin;
import com.labutin.barman.specification.user.FindUserByLoginAndPassword;
import com.labutin.barman.specification.user.FindUserSet;

public class UserRepositoryTest {
	private UserRepositoryImpl repository = UserRepositoryImpl.getInstance();
	private PoolConnection pool = PoolConnection.POOL;
	@BeforeClass
	public void initPool() throws NoJDBCDriverException, NoJDBCPropertiesFileException
	{
		pool.initialization();
	}
	@Test
	public void findUserById() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		int userId =1;
		User user  = repository.query(new FindUserById(userId)).iterator().next();
		assertEquals(user.getUserName(), "Mike");
	}
	@Test
	public void findUserSet() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		Set<User> setUser = repository.query(new FindUserSet());
		assertEquals(setUser.size(), 4);
	}
	@Test
	public void findBarmanSet() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		int userId = 1;
		Set<User> setBarman = repository.query(new FindBarmanSet(userId));
		assertEquals(setBarman.size(), 3);
	}
	@Test
	public void findUserByLogin() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		String userLogin = "Mike";
		User user  = repository.query(new FindUserByLogin(userLogin)).iterator().next();
		assertEquals(user.getUserId(),  1);
	}
	@Test
	public void findUserByLoginAndPassword() throws RepositoryException, NoJDBCDriverException, NoJDBCPropertiesFileException {
		String userLogin = "Mike";
		String userPassword = "mixail123";
		User user  = repository.query(new FindUserByLoginAndPassword(userLogin, userPassword)).iterator().next();
		assertEquals(user.getUserId(),  1);
	}
}

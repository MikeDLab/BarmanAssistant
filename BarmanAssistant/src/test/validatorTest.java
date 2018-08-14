package test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.labutin.barman.validator.UserValidator;

public class validatorTest {
	private UserValidator validator = new UserValidator();
  @Test
  public void testUserNamePositive() {
	  String userName = "Miecha";
	  boolean actual = validator.isName(userName);
	  assertEquals(actual, true);
  }
  @Test
  public void testUserNameNegative() {
	  String userName = "Mie3cha";
	  boolean actual = validator.isName(userName);
	  assertEquals(actual, false);
  }
  @Test
  public void testUserNamePositive2() {
	  String userName = "Mie-cha";
	  boolean actual = validator.isName(userName);
	  assertEquals(actual, true);
  }
  @Test
  public void testUserLoginPositive() {
	  String userName = "Miecha";
	  boolean actual = validator.isLogin(userName);
	  assertEquals(actual, true);
  }
  @Test
  public void testUserLoginNegative() {
	  String userName = "mie3c ha";
	  boolean actual = validator.isLogin(userName);
	  assertEquals(actual, false);
  }
  @Test
  public void testUserLoginNegative2() {
	  String userName = "mie3c_ha";
	  boolean actual = validator.isLogin(userName);
	  assertEquals(actual, false);
  }
  @Test
  public void testUserLoginPositive2() {
	  String userName = "Mie-cha";
	  boolean actual = validator.isLogin(userName);
	  assertEquals(actual, false);
  }
}

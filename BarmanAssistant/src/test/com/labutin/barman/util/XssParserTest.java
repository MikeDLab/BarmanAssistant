package test.com.labutin.barman.util;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.labutin.barman.util.XssParser;

public class XssParserTest {
	@Test
	public void testPositive() {
		String input = "<script>dsfsdfa</script>";
		String result = XssParser.parse(input);
		boolean actual = "&script&dsfsdfa&/script&".equals(result);
		System.out.println(result);
		assertEquals(actual, true);
	}

	@Test
	public void testPositive1() {
		String input = "<script>\n" + "\n" + "var x, y, z;  // Declare 3 variables\n"
				+ "x = 5;        // Assign the value 5 to x\n" + "y = 6;        // Assign the value 6 to y\n"
				+ "z = x + y;    // Assign the sum of x and y to z\n" + "\n"
				+ "document.getElementById(\"demo\").innerHTML =\n" + "\"The value of z is \" + z + \".\";\n" + "\n"
				+ "</script>";

		String result = XssParser.parse(input);
		boolean actual = ("&script&\n" + "\n" + "var x, y, z;  // Declare 3 variables\n"
				+ "x = 5;        // Assign the value 5 to x\n" + "y = 6;        // Assign the value 6 to y\n"
				+ "z = x + y;    // Assign the sum of x and y to z\n" + "\n"
				+ "document.getElementById(&demo&).innerHTML =\n" + "&The value of z is & + z + &.&;\n" + "\n"
				+ "&/script&").equals(result);
		assertEquals(actual, true);
	}

	@Test
	public void testNegative() {
		String input = "<script>dsfsdfa</script>";
		String result = XssParser.parse(input);
		boolean actual = "<script>dsfsdfa</script>".equals(result);
		System.out.println(result);
		assertEquals(actual, false);
	}
	@Test
	public void testNegative1() {
		String input = "<script>dsfsdfa</script>";
		String result = XssParser.parse(input);
		boolean actual = "<script>dsfsdfa</script>".equals(result);
		System.out.println(result);
		assertEquals(actual, false);
	}

}

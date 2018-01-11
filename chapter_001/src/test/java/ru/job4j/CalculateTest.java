package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Gregory Smirnov (artress@ngs.ru)
* @version 1.0
* @since 12.01.2018
*/
public class CalculateTest {
	
	/**
	* Test echo().
	*/ 
	@Test
	public void whenTakeNameThanTreeEchoPlusName() {
		String input = "Gregory Smirnov";
		String expect = "Echo, echo, echo: Gregory Smirnov";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}
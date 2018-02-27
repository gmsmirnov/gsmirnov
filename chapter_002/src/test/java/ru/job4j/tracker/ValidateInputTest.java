package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Optimized class for testing emulating users answers with validation, uses @Before & @After.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 26/02/2018
 */
public class ValidateInputTest {
    /**
     * Default console output.
     */
    private final PrintStream stdout = System.out;

    /**
     * Buffer for result testing.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"Invalid", "6"}), new ConsoleOutput());
        input.ask("Enter", new int[] {6});
        assertThat(this.out.toString(), is(String.format("Please enter menu point%n")));
    }
}
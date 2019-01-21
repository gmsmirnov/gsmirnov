package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests Paint.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 17/02/2018
 */
public class PaintTest {
    /**
     * Default console output.
     */
    private final PrintStream stdout = System.out;

    /**
     * Buffer for result testing.
     */
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square(4));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle(4));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("   +   ").append(System.lineSeparator())
                .append("  +++  ").append(System.lineSeparator())
                .append(" +++++ ").append(System.lineSeparator())
                .append("+++++++").append(System.lineSeparator())
                .toString()));
    }
}
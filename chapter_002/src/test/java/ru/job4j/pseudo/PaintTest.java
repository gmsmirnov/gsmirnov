package ru.job4j.pseudo;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests Paint.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 17/02/2018
 */
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square(4));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .toString()));
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle(4));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("   +   ").append(System.lineSeparator())
                .append("  +++  ").append(System.lineSeparator())
                .append(" +++++ ").append(System.lineSeparator())
                .append("+++++++").append(System.lineSeparator())
                .toString()));
        System.setOut(stdout);
    }
}
package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing pyramid painting.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 19/01/2018
 * @version 1.0
 */
public class PaintTest {
    @Test
    public void whenPyramidHeightIs4() {
        Paint paint = new Paint();
        String rst = paint.pyramid(4);
        System.out.println(rst);
        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                            .add("   ^   ")
                            .add("  ^^^  ")
                            .add(" ^^^^^ ")
                            .add("^^^^^^^")
                            .toString()
        ));
    }

    @Test
    public void whenPyramidHeightIs5() {
        Paint paint = new Paint();
        String rst = paint.pyramid(5);
        System.out.println(rst);
        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("    ^    ")
                .add("   ^^^   ")
                .add("  ^^^^^  ")
                .add(" ^^^^^^^ ")
                .add("^^^^^^^^^")
                .toString()
        ));
    }
}
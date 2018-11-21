package ru.job4j.monitore;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Model's test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/11/2018
 */
public class ModelTest {
    @Test
    public void whenModifyModel() {
        Model model = new Model("");
        assertThat(model.getData(), is(""));
        model.modify(1);
        assertThat(model.getData(), is("1"));
        model.modify(11);
        assertThat(model.getData(), is("111"));
    }
}
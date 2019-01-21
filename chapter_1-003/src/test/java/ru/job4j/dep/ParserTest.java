package ru.job4j.dep;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Tests parsing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 2.0
 * @since 23/04/2018
 */
public class ParserTest {
    @Test
    public void whenStringIsParsedThenContainerHasAllSubstrings() {
        String department = "K1\\SK1\\SSK1\\SSSK1\\SSSSK1";
        Parser parser = new Parser(department);
        String[] expected = {"K1", "SK1", "SSK1", "SSSK1", "SSSSK1"};
        assertThat(expected, arrayContainingInAnyOrder(parser.getInput()));
    }
}
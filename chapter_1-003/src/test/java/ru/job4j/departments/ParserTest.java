package ru.job4j.departments;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

/**
 * Tests parsing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class ParserTest {
    @Test
    public void whenStringIsParsedThenContainerHasAllSubstrings() {
        String department = "K1\\SK1\\SSK1\\SSSK1\\SSSSK1";
        Parser parser = new Parser(department);
        parser.parse();
        String[] expected = {"K1\\SK1", "K1", "K1\\SK1\\SSK1\\SSSK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK1\\SSSK1\\SSSSK1"};
        assertThat(expected, arrayContainingInAnyOrder(parser.getSubdepartments().toArray()));
    }
}
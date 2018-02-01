package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the searching of substring in string.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 29/01/2018
 */
public class SubstringInStringTest {
    @Test
    public void whenSubstringIsOnceInStringThanTrue() {
        SubstringInString sis = new SubstringInString();
        boolean expected = true;
        try {
            boolean result = sis.contains("Ho-ho-ho", "ho-");
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void whenSubstringIsTwiceInStringThanTrue() {
        SubstringInString sis = new SubstringInString();
        boolean expected = true;
        try {
            boolean result = sis.contains("Ho-ho-ho", "ho");
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void whenSubstringIsNotInStringThanFalse() {
        SubstringInString sis = new SubstringInString();
        boolean expected = false;
        try {
            boolean result = sis.contains("Ho-ho-ho", "1");
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void whenSubstringIsTheSameAsStringThanTrue() {
        SubstringInString sis = new SubstringInString();
        boolean expected = true;
        try {
            boolean result = sis.contains("Ho-ho-ho", "Ho-ho-ho");
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSubstringIsLongerThanStringThanException() {
        SubstringInString sis = new SubstringInString();
        boolean result = sis.contains("Ho-ho-ho", "Ho-ho-ho-ho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSubstringIsEmptyThanException() {
        SubstringInString sis = new SubstringInString();
        boolean result = sis.contains("Ho-ho-ho", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenStringIsEmptyThanException() {
        SubstringInString sis = new SubstringInString();
        boolean result = sis.contains("", "Ho-ho-ho");
    }
}
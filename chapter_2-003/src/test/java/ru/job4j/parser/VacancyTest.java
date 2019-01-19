package ru.job4j.parser;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * The vacancy's test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/12/2018
 */
public class VacancyTest {
    @Test
    public void whenCreateNewVacancyThanGetNameIsTrue() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        assertThat(vacancy.getName(), is("Test name"));
    }

    @Test
    public void whenCreateNewVacancyThanGetDescriptionIsTrue() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        assertThat(vacancy.getDescription(), is("Test description"));
    }

    @Test
    public void whenCreateNewVacancyThanGetLinkIsTrue() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        assertThat(vacancy.getLink(), is("Test link"));
    }

    @Test
    public void whenSetNameThenGetNameIsTrue() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        vacancy.setName("New name");
        assertThat(vacancy.getName(), is("New name"));
    }

    @Test
    public void whenSetDescriptionThenGetDescriptionIsTrue() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        vacancy.setDescription("New description");
        assertThat(vacancy.getDescription(), is("New description"));
    }

    @Test
    public void whenSetLinkThenGetLinkIsTrue() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        vacancy.setLink("New link");
        assertThat(vacancy.getLink(), is("New link"));
    }

    /**
     * Vacancies are equals if names and descriptions are equals.
     * The links doesn't matter.
     */
    @Test
    public void whenCreateTwoEqualVacanciesThenEqualsIsTrue() {
        Vacancy vacancy1 = new Vacancy("1", "1", "1");
        Vacancy vacancy2 = new Vacancy("1", "1", "2");
        assertThat(vacancy1.equals(vacancy2), is(true));
    }

    @Test
    public void whenCreateTwoNotEqualVacanciesThenEqualsIsFalse() {
        Vacancy vacancy1 = new Vacancy("1", "1", "1");
        Vacancy vacancy2 = new Vacancy("2", "2", "2");
        assertThat(vacancy1.equals(vacancy2), is(false));
    }

    @Test
    public void equalsTest() {
        Vacancy vacancy1 = new Vacancy("1", "1", "1");
        Vacancy vacancy2 = new Vacancy(null, "1", "1");
        Vacancy vacancy3 = new Vacancy("1", null, "1");
        Vacancy vacancy4 = vacancy1;
        assertThat(vacancy1.equals(null), is(false));
        assertThat(vacancy1.equals(vacancy2), is(false));
        assertThat(vacancy1.equals(vacancy3), is(false));
        assertThat(vacancy1.equals(vacancy4), is(true));
    }

    @Test
    public void hashCodeTest() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        int expected = "Test name".hashCode() * 31 + "Test description".hashCode();
        assertThat(vacancy.hashCode(), is(expected));
    }

    @Test
    public void toStringTest() {
        Vacancy vacancy = new Vacancy("Test name", "Test description", "Test link");
        String text1 = String.format("Vacancy%nname='Test name'%ndescription='Test description'%nlink='Test link'%n");
        String text2 = String.format("Vacancyname='Test name'description='Test description'link='Test link'");
        assertThat(vacancy.toString().equals(text1), is(true));
        assertThat(vacancy.toString().equals(text2), is(false));
    }
}
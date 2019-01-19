package ru.job4j.parser;

import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DateTransformerTest {
    @Test
    public void whenTransformFromStringToDateThenTrue() throws ParseException {
        assertThat(DateTransformer.fromStringToDate("01.01.2001"), is(DateTransformer.fullTransformToDate("01 янв 2001, 18:45")));
    }

    @Test
    public void whenTrimStringDateThenTrue() {
        assertThat(DateTransformer.trim("01 май 15, 07:15"), is("01 май 15"));
    }

    @Test
    public void whenDateTransformsFromStringToStringThanTrue() {
        assertThat(DateTransformer.fromStringToString("01 янв 15"), is("01.01.15"));
        assertThat(DateTransformer.fromStringToString("01 фев 15"), is("01.02.15"));
        assertThat(DateTransformer.fromStringToString("01 мар 15"), is("01.03.15"));
        assertThat(DateTransformer.fromStringToString("01 апр 15"), is("01.04.15"));
        assertThat(DateTransformer.fromStringToString("01 май 15"), is("01.05.15"));
        assertThat(DateTransformer.fromStringToString("01 июн 15"), is("01.06.15"));
        assertThat(DateTransformer.fromStringToString("01 июл 15"), is("01.07.15"));
        assertThat(DateTransformer.fromStringToString("01 авг 15"), is("01.08.15"));
        assertThat(DateTransformer.fromStringToString("01 сен 15"), is("01.09.15"));
        assertThat(DateTransformer.fromStringToString("01 окт 15"), is("01.10.15"));
        assertThat(DateTransformer.fromStringToString("01 ноя 15"), is("01.11.15"));
        assertThat(DateTransformer.fromStringToString("01 дек 15"), is("01.12.15"));
    }
}
package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

/**
 * PostgresUtil's test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/12/2018
 */
public class PostgresUtilTest {
    private PostgresUtil util;
    private Logger log;

    @Before
    public void init() {
        this.util = new PostgresUtil("sqlrutest.properties");
        this.log = LogManager.getLogger(PostgresUtilTest.class);
        this.log.info("PostgresUtil test started.");
    }

    @After
    public void end() throws Exception {
        this.util.close();
        this.log.info("PostgresUtil test ended.");
    }

    @Test
    public void whenConnectedToDatabaseThenTrue() {
        assertThat(this.util.isConnected(), is(true));
    }

    @Test
    public void whenIsStructureThenTrue() {
        assertThat(this.util.isStructure(), is(true));
    }

    @Test
    public void whenAddsVacancyThenTrue() {
        this.log.info("Test \'whenAddsVacancyThenTrue\' started.");
        boolean result = false;
        this.util.emptyDataBase(); // db is empty
        if (this.util.addVacancy(new Vacancy("Java developer", "Middle", "sql.ru")) != -1) {
            result = true;
        }
        assertThat(result, is(true));
        this.log.info("Test \'whenAddsVacancyThenTrue\' ended.");
    }

    @Test
    public void whenAddsCollectionOfVacanciesThenTrue() {
        this.log.info("Test \'whenAddsCollectionOfVacanciesThenTrue\' started.");
        this.util.emptyDataBase(); // db is empty
        Collection<Vacancy> expected = new LinkedList<Vacancy>();
        expected.add(new Vacancy("Java developer", "Middle", "sql.ru/1"));
        expected.add(new Vacancy("Java/C# developer", "Senior", "sql.ru/2"));
        expected.add(new Vacancy("C# developer", "Junior", "sql.ru/3"));
        this.util.addAllVacancies(expected);
        Collection<Vacancy> result = this.util.getAllVacancies();
        assertThat(result.size(), is(expected.size()));
        assertThat(expected.containsAll(result), is(true));
        this.log.info("Test \'whenAddsCollectionOfVacanciesThenTrue\' ended.");
    }

    @Test
    public void whenGetVacancyByIdThenTrue() {
        this.log.info("Test \'whenGetVacancyByIdThenTrue\' started.");
        boolean result = false;
        this.util.emptyDataBase(); // db is empty
        int id = this.util.addVacancy(new Vacancy("Java developer", "Middle", "sql.ru"));
        Vacancy vacancy = this.util.findVacancy(id);
        if ("Java developer".equals(vacancy.getName()) && "Middle".equals(vacancy.getDescription()) && "sql.ru".equals(vacancy.getLink())) {
            result = true;
        }
        assertThat(result, is(true));
        this.log.info("Test \'whenGetVacancyByIdThenTrue\' ended.");
    }

    @Test
    public void whenLastUpdateIsEmptyThenLastUpdateIsOneYearEarlier() {
        this.log.info("Test \'whenLastUpdateIsEmptyThenLastUpdateIsOneYearEarlier\' started.");
        this.util.emptyDataBase();
        Date fromDB = this.util.getLastUpdate();
        Date current = new Date(System.currentTimeMillis());
        // the difference between current time - last_update time - one_year is lower than 1 sec.
        assertThat(current.getTime() - fromDB.getTime() - Constants.YEAR, lessThan(1000L));
        this.log.info("Test \'whenLastUpdateIsEmptyThenLastUpdateIsOneYearEarlier\' ended.");
    }

    @Test
    public void whenLastUpdateUpdatedThanTrue() throws ParseException {
        this.log.info("Test \'whenLastUpdateUpdatedThanTrue\' started.");
        this.util.emptyDataBase();
        Date newUpdate = DateTransformer.fromStringToDate("01.01.2001");
        this.util.updateLastUpdate(newUpdate);
        assertThat(this.util.getLastUpdate(), is(newUpdate));
        this.log.info("Test \'whenLastUpdateUpdatedThanTrue\' ended.");
    }
}
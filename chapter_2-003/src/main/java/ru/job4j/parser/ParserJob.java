package ru.job4j.parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Class for creating a job which executes by scheduler.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/12/2018
 */
public class ParserJob implements Job {
    /**
     * Executes the job:
     * Creates a parser, which gathers information from the specified portal (sql.ru)
     * Creates Postgres Util, which puts all unique parsed vacancies into DB.
     *
     * @param jobExecutionContext - not used.
     * @throws JobExecutionException - not used.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        long startParsing = System.currentTimeMillis();
        long lastUpdate = startParsing;
        PostgresUtil postgres = new PostgresUtil("sqlru.properties");
        Parser parser = new Parser(postgres.getLastUpdate());
        parser.parse("https://www.sql.ru/forum/job");
        long endParsing = System.currentTimeMillis();
        System.out.printf("Parsing time is %d.%n", endParsing - startParsing);
        long startDBUpdate = System.currentTimeMillis();
        postgres.addAllVacancies(parser.getVacancies());
        postgres.updateLastUpdate(new Date(lastUpdate));
        try {
            postgres.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endDBUpdate = System.currentTimeMillis();
        System.out.printf("DB updating time is %d.%n", endDBUpdate - startDBUpdate);
    }
}
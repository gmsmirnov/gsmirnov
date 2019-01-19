package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Application entry point.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/12/2018
 */
public class Start {
    public static void main(String[] args) throws SchedulerException {
        Properties config = new Properties();
        try {
            InputStream in = Start.class.getClassLoader().getResourceAsStream("sqlru.properties");
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JobDetail job = JobBuilder
                .newJob(ParserJob.class)
                .withIdentity("QuartzJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("QuartzTrigger", "group1")
                .withSchedule(CronScheduleBuilder
                        .cronSchedule(config.getProperty("cron.time")))
                .build();

        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();
        sched.scheduleJob(job, trigger);
    }
}
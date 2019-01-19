package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

/**
 * Parser which gather information about Java vacancies from the specified url. Except Java Script vacancies.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/12/2018
 */
public class Parser {
    private final Logger parserLog = LogManager.getLogger(Parser.class);

    /**
     * The buffer for storing vacancies during portal parsing. After parsing it inserts into DB.
     */
    private final HashSet<Vacancy> vacancies = new HashSet<Vacancy>();

    /**
     * The date from lst update in database.
     */
    private Date lastUpdate;

    /**
     * The tag which contains numbers of pages (of list of topics).
     */
    private final String pageQuantity = ".sort_options";

    /**
     * Topic's title's tag's name.
     */
    private final String name = ".postslisttopic";

    /**
     * Posts within one topic.
     */
    private final String posts = ".msgTable";

    /**
     * Post's title.
     */
    private final String title = ".messageHeader";

    /**
     * Post's body.
     */
    private final String body = ".msgBody";

    /**
     * Tag's name for date and time of the post.
     */
    private final String time = ".msgFooter";

    /**
     * The Parser's constructor, initialized with the specified last update date, which used to filter older posts.
     *
     * @param lastUpdate - the last update date from database.
     */
    public Parser(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Connects to the specified page, walks through the topic list's pages and calls 'walkThroughPages' method for every page.
     *
     * @param url - the specified base url (sql.ru/forum/job).
     */
    public void parse(String url) {
        try {
            Document page = Jsoup.connect(url).get();
            this.parserLog.info(String.format("Connected to: %s.%n", url));
            Elements numbers = page.select(this.pageQuantity);
            int quantity = 0;
            if (!numbers.isEmpty()) {
                quantity = Integer.parseInt(numbers.last().select("a[href]").last().text()); // the last page number
            }
            for (int pointer = 1; pointer <= quantity; pointer++) {
                this.walkThroughPages(url + '/' + pointer);
            }
            this.parserLog.info(String.format("Parsed %s.%n", url));
        } catch (IOException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Walks through the list of topics of the specified page and calls 'gatherInfo' method for every acceptable title.
     *
     * @param url - the specified page (for example: sql.ru/forum/job/33)
     */
    private void walkThroughPages(String url) {
        try {
            Document page = Jsoup.connect(url).get();
            this.parserLog.info(String.format("Connected to: %s.%n", url));
            Elements themes = page.select(this.name);
            if (!themes.isEmpty()) {
                for (Element theme : themes) {
                    String name = theme.select("a[href]").first().text(); // vacancy's name
                    if (nameFilter(name)) {
                        this.gatherInfo(theme.select("a[href]").first().attr("abs:href")); // vacancy's link
                    }
                }
            }
            this.parserLog.info(String.format("Walked through %s.%n", url));
        } catch (IOException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Gathers the info from the topic's page: title, description, link.
     *
     * @param url - the specified topic's page.
     */
    private void gatherInfo(String url) {
        try {
            Document page = Jsoup.connect(url).get();
            this.parserLog.info(String.format("Connected to: %s to gather vacancy's info.%n", url));
            Element post = page.select(this.posts).first();
            String postTimeText = post.select(this.time).first().text();
            if (DateTransformer.fullTransformToDate(postTimeText).compareTo(this.lastUpdate) >= 0) { // time check
                String name = post.select(this.title).first().text();
                String description = post.select(this.body).last().text();
                this.vacancies.add(new Vacancy(name, description, url));
                this.parserLog.info(String.format("Gathered info %s.%n", url));
            }
        } catch (IOException e) {
            this.parserLog.error(e.getMessage(), e);
        } catch (ParseException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Checks topic's name for containing Java Script (not acceptable topic names).
     *
     * @param name - the specified topic's name.
     * @return true if name is acceptable, false either.
     */
    private boolean nameFilter(String name) {
        boolean result = false;
        if (name.contains("Java") || name.contains("java")) {
            if (!name.contains("JavaScript") && !name.contains("Java Script")
                    && !name.contains("javascript") && !name.contains("java script")
                    && !name.contains("java скрипт") && !name.contains("Java script")
                    && !name.contains("java Script") && !name.contains("Javascript")) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Gets the vacancies's buffer.
     *
     * @return the vacancies's buffer.
     */
    public HashSet<Vacancy> getVacancies() {
        return this.vacancies;
    }
}
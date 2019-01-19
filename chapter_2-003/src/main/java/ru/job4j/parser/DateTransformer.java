package ru.job4j.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Some static methods to transform String date to other String date and to java.util.Date.
 */
public class DateTransformer {
    /**
     * Transforms String date from format 'dd MMM yy' to String date format 'dd.mm.yy'.
     *
     * @param inputDate - the specified String date in format 'dd MMM yy'.
     * @return the String date in format 'dd.mm.yy'.
     */
    public static String fromStringToString(String inputDate) {
        String result = null;
        if (inputDate.contains(" янв ")) {
            result = inputDate.replace(" янв ", ".01.");
        } else if (inputDate.contains(" фев ")) {
            result = inputDate.replace(" фев ", ".02.");
        } else if (inputDate.contains(" мар ")) {
            result = inputDate.replace(" мар ", ".03.");
        } else if (inputDate.contains(" апр ")) {
            result = inputDate.replace(" апр ", ".04.");
        } else if (inputDate.contains(" май ")) {
            result = inputDate.replace(" май ", ".05.");
        } else if (inputDate.contains(" июн ")) {
            result = inputDate.replace(" июн ", ".06.");
        } else if (inputDate.contains(" июл ")) {
            result = inputDate.replace(" июл ", ".07.");
        } else if (inputDate.contains(" авг ")) {
            result = inputDate.replace(" авг ", ".08.");
        } else if (inputDate.contains(" сен ")) {
            result = inputDate.replace(" сен ", ".09.");
        } else if (inputDate.contains(" окт ")) {
            result = inputDate.replace(" окт ", ".10.");
        } else if (inputDate.contains(" ноя ")) {
            result = inputDate.replace(" ноя ", ".11.");
        } else if (inputDate.contains(" дек ")) {
            result = inputDate.replace(" дек ", ".12.");
        }
        return result;
    }

    /**
     * Transforms String date in format 'dd.mm.yy' into java.util.Date.
     *
     * @param date - date in format 'dd.mm.yy'.
     * @return date in java.util.Date format.
     * @throws ParseException as defined.
     */
    public static Date fromStringToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.mm.yy");
        return format.parse(date);
    }

    /**
     * Trims the specified String date ('dd MMM yy, time') to format 'dd MMM yy'.
     *
     * @param inputDate - the specified input date.
     * @return trimmed date.
     */
    public static String trim(String inputDate) {
        return inputDate.substring(0, inputDate.indexOf(','));
    }

    /**
     * Full transform from String date in format 'dd MMM yy, time' to java.util.Date.
     *
     * @param date - the specified String date in format 'dd MMM yy, time'.
     * @return java.util.Date.
     * @throws ParseException as defined.
     */
    public static Date fullTransformToDate(String date) throws ParseException {
        return DateTransformer.fromStringToDate(DateTransformer.fromStringToString(DateTransformer.trim(date)));
    }
}
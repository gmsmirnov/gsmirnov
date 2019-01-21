package ru.job4j.departments;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses departments by parts.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class Parser {
    /**
     * The input department string (for example "K1\\SK1\\SSK1").
     */
    private final String department;

    /**
     * Number of subdepartments in input string (three in example).
     */
    private final int wordsCounter;

    /**
     * All parsed subdepartments (In example: "K1", "K1\\SK1", "K1\\SK1\\SSK1").
     */
    private ArrayList<String> subdepartments = new ArrayList<String>();

    /**
     * Creates and initiates parser.
     *
     * @param department - input department string.
     */
    public Parser(String department) {
        this.department = department;
        this.wordsCounter = this.countWords();
    }

    /**
     * Subdepartments getter.
     *
     * @return - all subdepartments in ArrayList form.
     */
    public ArrayList<String> getSubdepartments() {
        return this.subdepartments;
    }

    /**
     * Gets the number of subdepartments in initial string.
     *
     * @return - the number of subdepartments.
     */
    public int getWordsCounter() {
        return this.wordsCounter;
    }

    /**
     * Calculates the number of departments in initial string. Used for initialise parser object.
     *
     * @return - the number of subdepartments.
     */
    private int countWords() {
        int counter = 0;
        char[] chDep = this.department.toCharArray();
        for (int index = 0; index < chDep.length; index++) {
            if (chDep[index] == '\\') {
                counter++;
            }
        }
        return counter + 1;
    }

    /**
     * Main parser algorithm. Initiates subdepartments.
     */
    public void parse() {
        int chCounter = 0;
        int parsCounter = 0;
        char[] chDep = this.department.toCharArray();
        char[] word = new char[chDep.length];
        for (int i = this.getWordsCounter(); i > 0; i--) {
            for (int j = 0; j < chDep.length; j++) {
                if (chDep[j] == '\\') {
                    parsCounter++;
                }
                if (parsCounter == i) {
                    break;
                }
                word[j] = chDep[j];
                chCounter++;
            }
            this.subdepartments.add(new String(Arrays.copyOf(word, chCounter)));
            chCounter = 0;
            parsCounter = 0;
        }
    }
}
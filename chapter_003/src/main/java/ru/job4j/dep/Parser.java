package ru.job4j.dep;

import java.util.Arrays;

/**
 * Parses input string by words.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 2.0
 * @since 23/04/2018
 */
public class Parser {
    /**
     * Parse by words input string.
     */
    private final String[] input;

    /**
     * Initiates this.input by parsed words from input string.
     *
     * @param input
     */
    public Parser(String input) {
        this.input = input.split("\\\\");
    }

    /**
     * Gets parsed by words input string.
     *
     * @return parsed by words input string.
     */
    public String[] getInput() {
        return this.input;
    }

    /**
     * Gets word of input string by index.
     *
     * @param index - the index.
     * @return - the word if index exists, null either.
     */
    public String getWord(int index) {
        String result = null;
        if (index >= 0 && index < this.input.length) {
            result = this.input[index];
        }
        return result;
    }

    /**
     * Presents parsed string in string view.
     *
     * @return string-view of parsed string.
     */
    @Override
    public String toString() {
        return String.format("Parser{input=%s}", Arrays.toString(this.input));
    }
}
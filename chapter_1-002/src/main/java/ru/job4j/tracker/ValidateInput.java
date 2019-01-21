package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Validate console input.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.3
 * @since 23/02/2018
 */
public class ValidateInput implements Input {
    /**
     * The instance of Input interface.
     */
    private final Input input;

    /**
     * The instance of Output interface.
     */
    private final Output output;

    /**
     * Validate input constructor.
     *
     * @param input - the instance of Input interface.
     * @param output - the instance of Output interface.
     */
    public ValidateInput(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @return - users answer.
     */
    @Override
    public String ask(String request) {
        return this.input.ask(request);
    }

    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @param range - the range of allowable points.
     * @return - the point that user was selected.
     */
    @Override
    public int ask(String request, ArrayList<Integer> range) {
        int result = -1;
        boolean valid = false;
        do {
            try {
                result = this.input.ask(request, range);
                valid = true;
            } catch (MenuOutException moe) {
                StartUI.LOGGER.error("Menu point not exist");
                this.output.print("Menu point not exist");
            } catch (NumberFormatException nfe) {
                StartUI.LOGGER.error("Please enter menu point");
                this.output.print("Please enter menu point");
            }
        } while (!valid);
        return result;
    }
}
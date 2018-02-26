package ru.job4j.tracker;

/**
 * Validate console input.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 23/02/2018
 */
public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String request) {
        return this.input.ask(request);
    }

    @Override
    public int ask(String request, int[] range) {
        int result = -1;
        boolean valid = false;
        do {
            try {
                result = this.input.ask(request, range);
                valid = true;
            } catch (MenuOutException moe) {
                StartUI.LOGGER.error("Menu point not exist");
            } catch (NumberFormatException nfe) {
                StartUI.LOGGER.error("Please enter menu point");
                System.out.println("Please enter menu point");
            }
        } while (!valid);
        return result;
    }
}
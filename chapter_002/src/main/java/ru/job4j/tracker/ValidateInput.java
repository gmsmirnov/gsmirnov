package ru.job4j.tracker;

/**
 * Validate console input.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 23/02/2018
 */
public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String request, int[] range) {
        int result = -1;
        boolean valid = false;
        do {
            try {
                result = super.ask(request, range);
                valid = true;
            } catch (MenuOutException moe) {
                StartUI.LOGGER.error("Menu point not exist.");
                //System.out.println("Menu point not exist.");
            } catch (NumberFormatException nfe) {
                StartUI.LOGGER.error("Please enter menu point.");
                //System.out.println("Please enter menu point.");
            }
        } while (!valid);
        return result;
    }
}
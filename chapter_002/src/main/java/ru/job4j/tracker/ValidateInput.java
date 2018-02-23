package ru.job4j.tracker;

/**
 * Validate console input.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/02/2018
 */
public class ValidateInput extends ConsoleInput {
    public int ask(String request, int[] range) {
        int result = -1;
        boolean valid = false;
        do {
            try {
                result = super.ask(request, range);
                valid = true;
            } catch (MenuOutException moe) {
                System.out.println("Menu point not exist.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter menu point.");
            }
        } while (!valid);
        return result;
    }
}
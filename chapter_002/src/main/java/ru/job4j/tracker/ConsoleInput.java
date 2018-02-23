package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Provides applications console input for user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 10/02/2018
 */
public class ConsoleInput implements Input {
    /**
     * Users answer reader.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @return - users answer.
     */
    @Override
    public String ask(String request) {
        System.out.println(request);
        return scanner.nextLine();
    }

    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @param range - the range of allowable points.
     * @return - the point that user was selected.
     * @throws MenuOutException - wrong menu point.
     */
    @Override
    public int ask(String request, int[] range) throws MenuOutException {
        System.out.println(request);
        int result = -1;
        int answer = Integer.parseInt(scanner.nextLine());
        for (int value : range) {
            if (value == answer) {
                result = answer;
                break;
            }
        }
        if (result == -1) {
            throw new MenuOutException("Wrong point, please, select correct menu point.");
        }
        return result;
    }
}
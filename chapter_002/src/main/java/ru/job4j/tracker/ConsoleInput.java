package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Provides applications console input for user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
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
}
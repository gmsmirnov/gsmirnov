package ru.job4j.pool;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A e-mail notification. A service for e-mail delivery.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/09/2018
 */
public class EmailNotification {
    /**
     * The thread pool.
     */
    private final ExecutorService pool;

    /**
     * The task's results.
     */
    private LinkedList<Future<Boolean>> results;

    /**
     * Gets task's results.
     *
     * @return the list of results.
     */
    public LinkedList<Future<Boolean>> getResults() {
        return results;
    }

    /**
     * Creates a e-mail delivery service with a thread pool inside. This thread pool contains threads in quantity of
     * available processors.
     */
    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.results = new LinkedList<Future<Boolean>>();
    }

    /**
     * Creates a task to send a message to the specified user.
     *
     * @param user the specified user.
     */
    public void emailTo(User user) {
        this.results.add(this.pool.submit(() -> {
            EmailNotification.this.send(String.format("Notification {%s} to email {%s}%n", user.getUserName(), user.geteMail()),
                    String.format("Add new event to {%s}%n", user.getUserName()), user.geteMail());
            return true;
        }));
    }

    /**
     * Sends a message to a user.
     *
     * @param subject - the message's subject.
     * @param body - the message's body.
     * @param email - the user's body.
     */
    public void send(String subject, String body, String email) {
        System.out.printf("The message was sent to the user's e-mail: %s%n", email);
        System.out.printf("The subject: %s%n", subject);
        System.out.printf("Message: %n%s%n", body);
    }

    /**
     * Closes pool. It waits until thread pool terminate.
     */
    public void close() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                System.out.println("Close interrupted.");
            }
        }
    }
}
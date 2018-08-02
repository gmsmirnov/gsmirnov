package ru.job4j.monitore;

/**
 * Transaction's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
public class Transaction implements Runnable {
    /**
     * The users storage.
     */
    private UserStorage storage;

    /**
     * Max user's amount.
     */
    private int maxAmounts;

    /**
     * Creates new transaction.
     *
     * @param storage - the users storage.
     * @param maxAmounts - users quantity.
     */
    public Transaction(UserStorage storage, int maxAmounts) {
        this.storage = storage;
        this.maxAmounts = maxAmounts;
    }

    /**
     * Transaction.
     */
    @Override
    public void run() {
        int from = (int) (this.storage.size() * Math.random());
        int to = (int) (this.storage.size() * Math.random());
        this.storage.transfer(from, to, (int) (this.maxAmounts * Math.random()));
    }
}
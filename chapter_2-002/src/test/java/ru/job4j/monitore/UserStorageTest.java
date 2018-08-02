package ru.job4j.monitore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {
    int capacity;
    int amount;
    int transactions;
    UserStorage storage;
    int expected;

    @Before
    public void init() {
        this.capacity = 100;
        this.amount = 1000;
        this.transactions = 1_000_000;
        this.storage = new UserStorage(capacity);
        for (int i = 0; i < this.capacity; i++) {
            this.storage.add(new User(i, this.amount));
        }
        this.expected = this.storage.total();
        System.out.println("Start total: " + this.expected);
    }

    @Test
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[this.transactions];
        for (int i = 0; i < this.transactions; i++) {
            Transaction transaction = new Transaction(this.storage, this.capacity);
            threads[i] = new Thread(transaction);
            threads[i].start();
        }
        for (int i = 0; i < this.transactions; i++) {
            threads[i].join();
        }
        long end = System.currentTimeMillis();
        int result = this.storage.total();
        System.out.printf("End total for %d ms: %d", (end - start), result);
        assertThat(result, is(this.expected));
    }
}
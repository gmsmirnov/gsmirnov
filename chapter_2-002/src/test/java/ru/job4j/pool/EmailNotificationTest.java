package ru.job4j.pool;

import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmailNotificationTest {
    private final Result results = new Result();
    private final EmailNotification sender = new EmailNotification(this.results);

    @Test
    public void whenCreatesSingleMessageThanTrue() throws ExecutionException, InterruptedException {
        this.sender.emailTo(new User("Alex", "alex@mail.ru"));
        Boolean result = this.results.getResults().get(0).get();
        assertThat(result, is(true));
        this.sender.close();
    }

    @Test
    public void whenCreatesManyMessagesThanTrue() throws ExecutionException, InterruptedException {
        LinkedList<Boolean> results = new LinkedList<Boolean>();
        LinkedList<Boolean> expected = new LinkedList<Boolean>();
        for (int i = 0; i < 100; i++) {
            this.sender.emailTo(new User("User " + i, "user" + i + "@mail.ru"));
            results.add(this.results.getResults().get(i).get());
            expected.add(true);
        }
        Thread.sleep(1000);
        assertThat(results, is(expected));
        this.sender.close();
    }
}
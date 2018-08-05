package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleSynchroLinkedListTest {
    private Thread t0;

    private Thread t1;

    private BaseList<Integer> list;

    private Iterator<Integer> it;

    @Before
    public void init() {
        this.list = new SimpleSynchroLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.list.add(i);
        }

        this.t0 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    SimpleSynchroLinkedListTest.this.list.add(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 20; i++) {
                    SimpleSynchroLinkedListTest.this.list.remove(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        this.t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    SimpleSynchroLinkedListTest.this.list.add(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 30; i++) {
                    SimpleSynchroLinkedListTest.this.list.remove(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Test
    public void test() {
        this.t0.start();
        this.t1.start();

        try {
            this.t0.join();
            this.t1.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.it = this.list.iterator();
        while (this.it.hasNext()) {
            System.out.println(this.it.next());
        }
        assertThat(this.list.size(), is(155));
    }
}
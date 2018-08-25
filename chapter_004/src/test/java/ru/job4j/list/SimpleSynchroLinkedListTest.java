package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleSynchroLinkedListTest {
    // The beginning of single thread test
    private SimpleSynchroLinkedList<Integer> intList;

    private Iterator<Integer> it;

    @Before
    public void init() {
        this.intList = new SimpleSynchroLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.intList.add(i);
        }
        this.it = this.intList.iterator();
    }

    @Test
    public void whenGetIntegerFromListThenTrue() {
        for (int i = 0; i < 5; i++) {
            assertThat(this.intList.get(i), is(i));
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBoundsThenArrayIndexOutOfBoundsException() {
        assertThat(this.intList.get(10), is(10));
    }

    @Test
    public void whenSetNewValueThenReturnOldValue() {
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.set(0, 10), is(0));
        assertThat(this.intList.get(0), is(10));
    }

    @Test
    public void whenRemoveThenReturnOldValue() {
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.get(1), is(1));
        assertThat(this.intList.get(2), is(2));
        assertThat(this.intList.remove(1), is(1));
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.get(1), is(2));
        assertThat(this.intList.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWalksThenIteratorGetsAllElementsWhileThereAreAnyElements() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(false));
        this.it.next();
    }

    @Test
    public void whenListIsEmptyIteratorReturnsFalse() {
        Iterator<String> it = new SimpleLinkedList<String>().iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddStringToContainerThenGetString() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("5");
        String result = simpleLinkedList.get(0);
        assertThat(result, is("5"));
    }

    @Test
    public void whenAddDoubleToContainerThenGetDouble() {
        SimpleLinkedList<Double> simpleLinkedList = new SimpleLinkedList<Double>();
        simpleLinkedList.add(5.0);
        Double result = simpleLinkedList.get(0);
        assertThat(result, is(5.0));
    }

    @Test
    public void whenRemoveValueFromContainerThenGetThatValueAndMoveElements() {
        assertThat(this.intList.remove(2), is(2));
        this.it = this.intList.iterator();
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(false));
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.get(1), is(1));
        assertThat(this.intList.get(2), is(3));
        assertThat(this.intList.get(3), is(4));
    }

    @Test
    public void whenAddValuesMoreThanContainerSizeThenContainerGrows() {
        assertThat(this.intList.size(), is(5));
        this.intList.add(5);
        this.intList.add(6);
        this.intList.add(7);
        this.intList.add(8);
        this.intList.add(9);
        this.intList.add(10);
        this.intList.add(11);
        this.it = this.intList.iterator();
        assertThat(this.intList.size(), is(12));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(5));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(6));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(7));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(8));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(9));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(10));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(11));
        assertThat(this.it.hasNext(), is(false));
    }

    @Test
    public void whenAdds150ElementsThenContainerGrowsFor150() {
        for (int i = 0; i < 150; i++) {
            this.intList.add(i);
        }
        assertThat(this.intList.size(), is(155));
        assertThat(this.intList.get(154), is(149));
    }

    @Test
    public void whenAddIntegerToSpecifiedPositionThenRearrangeList() {
        this.intList.add(4, 10);
        assertThat(this.intList.get(3), is(3));
        assertThat(this.intList.get(4), is(10));
        assertThat(this.intList.get(5), is(4));
    }

    @Test
    public void whenTheListContainsElementThenTrueElseFalse() {
        assertThat(this.intList.contains(0), is(true));
        assertThat(this.intList.contains(1), is(true));
        assertThat(this.intList.contains(2), is(true));
        assertThat(this.intList.contains(3), is(true));
        assertThat(this.intList.contains(4), is(true));
        assertThat(this.intList.contains(10), is(false));
    }

    @Test
    public void whenTheListContainsElementThenCheckIndex() {
        assertThat(this.intList.indexOf(0), is(0));
        assertThat(this.intList.indexOf(1), is(1));
        assertThat(this.intList.indexOf(2), is(2));
        assertThat(this.intList.indexOf(3), is(3));
        assertThat(this.intList.indexOf(4), is(4));
        assertThat(this.intList.indexOf(5), is(-1));
    }

    @Test
    public void print() {
        System.out.println(this.intList);
    }
    // The end of single thread test

    // The beginning of multi-thread test.
    private SimpleSynchroLinkedList<Integer> syncList;

    private BlockingQueue<Runnable> workQueue;

    private Executor executor;

    private ArrayList<Runnable> tasks;

    private ThreadFactory threadFactory;

    private RejectedExecutionHandler handler;

    private final AtomicInteger index = new AtomicInteger(0);

    @Before
    public void multiThreadInit() {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxCorePoolSize = 32 * corePoolSize;
        long keepAliveTime = 1000L;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        this.threadFactory = new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("ThreadPool thread# " + this.index.getAndIncrement());
                return thread;
            }
        };
        this.handler = (r, executor) -> System.out.println("All threads are busy");
        this.syncList = new SimpleSynchroLinkedList<Integer>();
        this.workQueue = new ArrayBlockingQueue<Runnable>(100);
        this.executor = new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, keepAliveTime, unit,
                this.workQueue, this.threadFactory, this.handler);
        this.tasks = new ArrayList<Runnable>(1000);
    }

    @Test
    public void whenManyThreadsOperateWithTheList() {
        // Adds new 100 elements
        for (int i = 0; i < 100; i++) {
            this.tasks.add(new Runnable() {
                @Override
                public void run() {
                    syncList.add(index.getAndIncrement());
                    System.out.println("New thread " + Thread.currentThread());
                }
            });
            this.executor.execute(this.tasks.get(i));
        }
        Integer[] expected = new Integer[100];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer[] result = new Integer[100];
        for (int i = 0; i < this.syncList.size(); i++) {
            result[i] = this.syncList.get(i);
        }
        assertThat(this.syncList.size(), is(100));
        assertThat(result, arrayContainingInAnyOrder(expected));

        // Delete 50 existing elements
        for (int i = 100; i < 150; i++) {
            this.tasks.add(new Runnable() {
                @Override
                public void run() {
                    syncList.remove(index.decrementAndGet());
                }
            });
            this.executor.execute(this.tasks.get(i));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer[] expected1 = new Integer[this.syncList.size()];
        for (int i = 0; i < expected1.length; i++) {
            expected1[i] = i;
        }
        Integer[] result1 = new Integer[this.syncList.size()];
        for (int i = 0; i < this.syncList.size(); i++) {
            result1[i] = this.syncList.get(i);
        }
        assertThat(this.syncList.size(), is(50));
        assertThat(result1, arrayContainingInAnyOrder(expected1));
        System.out.println(this.syncList);
    }
}
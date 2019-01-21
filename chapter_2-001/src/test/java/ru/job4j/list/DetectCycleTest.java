package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DetectCycleTest {
    @Test
    public void whenHasCycleToFirstElementThanTrue() {
        DetectCycle.Node<Integer> first = new DetectCycle.Node<Integer>(1);
        DetectCycle.Node<Integer> second = new DetectCycle.Node<Integer>(2);
        DetectCycle.Node<Integer> third = new DetectCycle.Node<Integer>(3);
        DetectCycle.Node<Integer> forth = new DetectCycle.Node<Integer>(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = first;
        assertThat(new DetectCycle().hasCycle(first), is(true));
    }

    @Test
    public void whenHasCycleToSecondElementThanTrue() {
        DetectCycle.Node<Integer> first = new DetectCycle.Node<Integer>(1);
        DetectCycle.Node<Integer> second = new DetectCycle.Node<Integer>(2);
        DetectCycle.Node<Integer> third = new DetectCycle.Node<Integer>(3);
        DetectCycle.Node<Integer> forth = new DetectCycle.Node<Integer>(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = second;
        assertThat(new DetectCycle().hasCycle(first), is(true));
    }

    @Test
    public void whenHasNoCycleThanFalse() {
        DetectCycle.Node<Integer> first = new DetectCycle.Node<Integer>(1);
        DetectCycle.Node<Integer> second = new DetectCycle.Node<Integer>(2);
        DetectCycle.Node<Integer> third = new DetectCycle.Node<Integer>(3);
        DetectCycle.Node<Integer> forth = new DetectCycle.Node<Integer>(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = null;
        assertThat(new DetectCycle().hasCycle(first), is(false));
    }

    @Test
    public void whenFirstIsNull() {
        DetectCycle.Node<Integer> first = null;
        assertThat(new DetectCycle().hasCycle(first), is(false));
    }

    @Test
    public void whenLongListHasNoCycleThanFalse() {
        DetectCycle.Node<Integer>[] node = new DetectCycle.Node[100];
        for (int i = 0; i < 100; i++) {
            node[i] = new DetectCycle.Node<Integer>(i);
        }
        for (int i = 0; i < 99; i++) {
            node[i].next = node[i + 1];
        }
        node[99].next = null;
        assertThat(new DetectCycle().hasCycle(node[0]), is(false));
    }
}
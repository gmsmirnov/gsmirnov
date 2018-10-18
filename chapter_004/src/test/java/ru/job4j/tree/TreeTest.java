package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * The Tree-test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 09/07/2018
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorGetsElementAfterTheLastThenException() {
        Tree<Integer> tree = new Tree<Integer>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(2, 5), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(1, 4), is(true));
        assertThat(tree.add(4, 5), is(false));
        assertThat(tree.add(1, 5), is(false));
        assertThat(tree.add(7, 5), is(false));
        assertThat(tree.add(2, 4), is(false));
        assertThat(tree.add(5, 6), is(true));
        assertThat(tree.findBy(6).isPresent(), is(true));
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAfterIteratorModifyTreeThenException() {
        Tree<Integer> tree = new Tree<Integer>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(2, 5), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(1, 4), is(true));
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(tree.add(5, 6), is(true));
        assertThat(iterator.next(), is(4));
    }

    @Test
    public void whenTreeIsBinaryThanTrue() {
        Tree<Integer> binaryTree = new Tree<>(1);
        assertThat(binaryTree.add(1, 2), is(true));
        assertThat(binaryTree.add(1, 3), is(true));
        assertThat(binaryTree.add(2, 4), is(true));
        assertThat(binaryTree.add(2, 5), is(true));
        assertThat(binaryTree.add(3, 6), is(true));
        assertThat(binaryTree.add(3, 7), is(true));
        assertThat(binaryTree.add(4, 8), is(true));
        assertThat(binaryTree.add(8, 9), is(true));
        assertThat(binaryTree.isBinary(), is(true));
    }

    @Test
    public void whenTreeIsNotBinaryThanFalse() {
        Tree<Integer> tree = new Tree<Integer>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(1, 4), is(true));
        assertThat(tree.add(3, 5), is(true));
        assertThat(tree.add(3, 6), is(true));
        assertThat(tree.add(6, 7), is(true));
        assertThat(tree.isBinary(), is(false));
    }
}
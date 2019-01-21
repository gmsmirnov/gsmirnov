package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Node structure
 *
 * @param <E> - a type of a stored value.
 */
public class Node<E extends Comparable<E>> {
    /**
     * The list of node's children of the first level.
     */
    private final List<Node<E>> children = new ArrayList<Node<E>>();

    /**
     * The stored value.
     */
    private final E value;

    /**
     * Node constructor. Assigns the value to the storage value.
     *
     * @param value - the assignable value.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Appends a new child to this node.
     *
     * @param child - a new child.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Gets the list of all node's children.
     *
     * @return the list of all node's children.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Compares the specified value with the node's value.
     *
     * @param that - the specified value.
     * @return true if values are equals.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Gets the node's value.
     *
     * @return the node's value.
     */
    public E getValue() {
        return this.value;
    }
}
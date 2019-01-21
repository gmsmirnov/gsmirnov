package ru.job4j.tree;

import java.util.Optional;

/**
 * Simple Tree with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/06/2018
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Appends the specified child to the specified parent.
     *
     * @param parent the specified parent.
     * @param child the specified child.
     * @return true if successful.
     */
    boolean add(E parent, E child);

    /**
     * Finds the node with the specified value.
     *
     * @param value - the specified value.
     * @return the node with the specified value.
     */
    Optional<Node<E>> findBy(E value);
}
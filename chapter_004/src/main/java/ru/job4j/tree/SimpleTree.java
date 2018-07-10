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
     * Adds element child to parent.
     *
     * @param parent
     * @param child
     * @return
     */
    boolean add(E parent, E child);

    /**
     *
     * @param value
     * @return
     */
    Optional<Node<E>> findBy(E value);
}
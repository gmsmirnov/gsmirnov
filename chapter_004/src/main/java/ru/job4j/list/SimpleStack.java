package ru.job4j.list;

/**
 * Simple stack based on simple linked list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/05/2018
 */
public class SimpleStack<E> extends SimpleLinkedList<E> {
    /**
     * Add new element to the tail of the list.
     *
     * @param data - new element.
     */
    public void push(E data) {
        this.add(data);
    }

    /**
     * Take the element from the tail of the list.
     *
     * @return the first element of the list.
     */
    public E poll() {
        return this.remove(this.size() - 1);
    }
}
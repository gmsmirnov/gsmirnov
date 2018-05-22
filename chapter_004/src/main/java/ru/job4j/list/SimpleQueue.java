package ru.job4j.list;

/**
 * Simple queue based on simple linked list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/05/2018
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {
    /**
     * The first position. To get element in the head of the list.
     */
    private final static int FIRST_ELEMENT = 0;

    /**
     * Add new element into the end of the list.
     *
     * @param data - new element.
     */
    public void push(E data) {
        this.add(data);
    }

    /**
     * Take the element from the head of the list.
     *
     * @return the first element of the list.
     */
    public E poll() {
        return this.remove(SimpleQueue.FIRST_ELEMENT);
    }
}
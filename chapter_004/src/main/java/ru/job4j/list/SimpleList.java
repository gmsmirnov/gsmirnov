package ru.job4j.list;

/**
 * Simple list description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/05/2018
 */
public class SimpleList<E> {
    /**
     * The size of the list.
     */
    private int size;

    /**
     * First node pointer.
     */
    private Node<E> first;

    /**
     * Adds new element into the beginning of the list.
     *
     * @param data - new element.
     */
    public void add(E data) {
        Node<E> newLink = new Node<E>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Deletes the first element of the list.
     *
     * @return - deleted element.
     */
    public E delete() {
        Node<E> result = this.first;
        this.first = result.next;
        this.size--;
        return result.data;
    }

    /**
     * Gets the element in the specified position.
     *
     * @param index - the specified position.
     * @return - the element in specified position.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Gets the size of the list.
     *
     * @return - the size of the list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Node description.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 17/05/2018
     */
    private static class Node<E> {
        /**
         * Data of the node.
         */
        E data;

        /**
         * Pointer to th next node.
         */
        Node<E> next;

        /**
         * Node constructor.
         *
         * @param data - the data of the node.
         */
        public Node(E data) {
            this.data = data;
        }
    }
}
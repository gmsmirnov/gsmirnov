package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Simple linked list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 18/05/2018
 */
public class SimpleLinkedList<E> implements BaseList<E> {
    /**
     * The size of the list.
     */
    private int size;

    /**
     * First node pointer.
     */
    private Node<E> first;

    /**
     * Simple array list modification counter.
     */
    private int modCount = 0;

    /**
     * Adds new element into the end of the list.
     *
     * @param value - new element.
     */
    @Override
    public boolean add(E value) {
        Node<E> newLink = new Node<E>(value);
        Node<E> pointer = this.first;
        if (this.first == null) {
            this.first = newLink;
        } else {
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = newLink;
        }
        this.modCount++;
        this.size++;
        return true;
    }

    /**
     * Adds new element into the specified position of the list.
     *
     * @param data - new element.
     */
    @Override
    public void add(int index, E data) {
        this.rangeCheck(index);
        Node<E> newLink = new Node<E>(data);
        Node<E> pointer = this.first;
        if (this.size == index) {
            this.add(data);
        } else {
            for (int position = 0; position < index - 1; position++) {
                pointer = pointer.next;
            }
            Node<E> tmp = pointer.next;
            pointer.next = newLink;
            newLink.next = tmp;
        }
        this.modCount++;
        this.size++;
    }

    /**
     * Replaces the element at the specified position in this linked list with the specified element.
     *
     * @param index - the specified position.
     * @param data - new element.
     * @return old element.
     */
    @Override
    public E set(int index, E data) {
        this.rangeCheck(index);
        Node<E> result = this.first;
        for (int position = 0; position < index; position++) {
            result = result.next;
        }
        E oldData = result.data;
        result.data = data;
        return oldData;
    }

    /**
     * Gets the element in the specified position.
     *
     * @param index - the specified position.
     * @return - the element in specified position.
     */
    @Override
    public E get(int index) {
        this.rangeCheck(index);
        Node<E> result = this.first;
        for (int position = 0; position < index; position++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Deletes the first element of the list.
     *
     * @return - deleted element.
     */
    @Override
    public E remove(int index) {
        this.rangeCheck(index);
        Node<E> result = this.first;
        Node<E> tmp = this.first;
        for (int position = 0; position < index; position++) {
            tmp = result;
            result = result.next;
        }
        if (result == this.first) {
            this.first = result.next;
        }
        tmp.next = result.next;
        this.size--;
        this.modCount++;
        return result.data;
    }

    /**
     * Gets the size of the list.
     *
     * @return - the size of the list.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    @Override
    public int indexOf(Object o) {
        int result = -1;
        int index = 0;
        if (o == null) {
            for (Node<E> pointer = this.first; pointer != null; pointer = pointer.next) {
                if (pointer.data == null) {
                    result = index;
                    break;
                }
                index++;
            }
        } else {
            for (Node<E> pointer = this.first; pointer != null; pointer = pointer.next) {
                if (o.equals(pointer.data)) {
                    result = index;
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Checks index location. If index locates out of linked list bounds, then method throws
     * IndexOutOfBoundsException.
     *
     * @param index - the specified position to check.
     */
    private void rangeCheck(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds! " + index);
        }
    }

    /**
     * Returns an iterator over the elements in this linked list in proper sequence.
     *
     * @return iterator over Simple Linked List.
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIterator<E>();
    }

    /**
     * Node description.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 18/05/2018
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

        /**
         * Represents this node in string-view.
         *
         * @return - presentation in string-view.
         */
        @Override
        public String toString() {
            return String.format("Node{data=%s}", this.data);
        }
    }

    /**
     * Describes an iterator over the elements of SimpleLinkedListSet.
     *
     * @param <E> - Type of elements.
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 18/05/2018
     */
    private class SimpleLinkedListIterator<E> implements Iterator<E> {
        /**
         * The current position of iterator.
         */
        private int position = 0;

        /**
         * Number of expected modifications. If number of modification changes throws exception.
         */
        private int expectedMod = modCount;

        /**
         * Checks for next element.
         *
         * @return true if this SimpleLinkedList contains next element.
         */
        @Override
        public boolean hasNext() {
            boolean result;
            Node pointer = first;
            for (int index = 0; index < this.position; index++) {
                pointer = pointer.next;
            }
            result = pointer != null;
            return result;
        }

        /**
         * Gets current element and moves pointer forward.
         * If the linked list contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            this.modificationCheck();
            Node result = first;
            for (int index = 0; index < this.position; index++) {
                result = result.next;
            }
            if (result == null) {
                throw new NoSuchElementException("No such element!");
            }
            this.position++;
            return (E) result.data;
        }

        /**
         * Checks for modification while iterator is working. If number of modification changes throws exception.
         */
        private void modificationCheck() {
            if (this.expectedMod != modCount) {
                throw new ConcurrentModificationException("The Simple Linked List was changed its structure!");
            }
        }
    }

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    @Override
    public String toString() {
        StringJoiner str = new StringJoiner(" ");
        Node pointer = first;
        for (int index = 0; index < this.size; index++) {
            str.add(pointer.data.toString());
            pointer = pointer.next;
        }
        return str.toString();
    }
}
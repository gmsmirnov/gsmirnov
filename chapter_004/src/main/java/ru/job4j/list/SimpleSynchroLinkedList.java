package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simple thread safe linked list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.4
 * @since 18/05/2018
 */
@ThreadSafe
public class SimpleSynchroLinkedList<E> implements BaseList<E> {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = this.rwLock.readLock();
    private final Lock wLock = this.rwLock.writeLock();

    /**
     * The container.
     */
    @GuardedBy("this.rwLock")
    private final SimpleLinkedList<E> list;

    public SimpleSynchroLinkedList() {
        this.list = new SimpleLinkedList<E>();
    }

    /**
     * Adds new element into the end of the list.
     *
     * @param value - new element.
     */
    public boolean add(E value) {
        this.wLock.lock();
        try {
            return this.list.add(value);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Adds new element into the specified position of the list.
     *
     * @param data - new element.
     */
    public void add(int index, E data) {
        this.wLock.lock();
        try {
            this.list.add(index, data);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Replaces the element at the specified position in this linked list with the specified element.
     *
     * @param index - the specified position.
     * @param data - new element.
     * @return old element.
     */
    public E set(int index, E data) {
        this.wLock.lock();
        try {
            return this.list.set(index, data);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Gets the element in the specified position.
     *
     * @param index - the specified position.
     * @return - the element in specified position.
     */
    public E get(int index) {
        this.rLock.lock();
        try {
            return this.list.get(index);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Deletes the first element of the list.
     *
     * @return - deleted element.
     */
    public E remove(int index) {
        this.wLock.lock();
        try {
            return this.list.remove(index);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return - the size of the list.
     */
    public int size() {
        this.rLock.lock();
        try {
            return this.list.size();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    public int indexOf(Object o) {
        this.rLock.lock();
        try {
            return this.list.indexOf(o);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    public boolean contains(Object o) {
        this.rLock.lock();
        try {
            return this.list.contains(o);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns an iterator over the elements in this linked list in proper sequence.
     *
     * @return iterator over Simple Linked List.
     */
    public Iterator<E> iterator() {
        this.rLock.lock();
        try {
            return new SimpleSynchroLinkedListIterator();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    public String toString() {
        this.rLock.lock();
        try {
            return this.list.toString();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Describes an iterator over the snapshot of elements of the list. The iterator is fail-safe.
     *
     * @param <E> - Type of elements.
     */
    private class SimpleSynchroLinkedListIterator<E> implements Iterator<E> {
        /**
         * The number of the first element.
         */
        private static final int FIRST = 0;

        /**
         * The head of the snapshot.
         */
        private Node<E> head;

        /**
         * Current position.
         */
        private int position = 0;

        /**
         * Creates new snapshot.
         */
        public SimpleSynchroLinkedListIterator() {
            if (SimpleSynchroLinkedList.this.list.size() > 0) {
                this.head = new Node(SimpleSynchroLinkedList.this.list.get(SimpleSynchroLinkedListIterator.FIRST));
                Node<E> pointer = this.head;
                for (int index = 1; index < SimpleSynchroLinkedList.this.list.size(); index++) {
                    pointer.next = new Node(SimpleSynchroLinkedList.this.list.get(index));
                    pointer = pointer.next;
                }
            }
        }

        /**
         * Checks for next element.
         *
         * @return true if this snapshot contains next element.
         */
        @Override
        public boolean hasNext() {
            Node<E> pointer = this.head;
            for (int index = 0; index < this.position; index++) {
                pointer = pointer.next;
            }
            return pointer != null;
        }

        /**
         * Gets current element and moves pointer forward.
         * If the list contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            Node<E> pointer = this.head;
            for (int index = 0; index < this.position; index++) {
                pointer = pointer.next;
            }
            if (pointer == null) {
                throw new NoSuchElementException("No more elements!");
            }
            this.position++;
            return pointer.data;
        }
    }

    /**
     * The snapshot node description.
     *
     * @param <E> - type of element
     */
    private static class Node<E> {
        /**
         * Data.
         */
        private E data;

        /**
         * Link to the next node.
         */
        private Node<E> next;

        /**
         * Creates new node.
         */
        public Node(E data) {
            this.data = data;
        }
    }
}
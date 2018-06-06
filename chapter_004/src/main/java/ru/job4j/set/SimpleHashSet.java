package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SimpleHashSet<E> implements Iterable<E> {
    /**
     * The default size of simple set list.
     */
    private static final int DEFAULT_SIZE = 100;

    /**
     * The load factor. If SimpleHashSet loading is higher than LOAD_FACTOR, than container requires rearrangement.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * Element quantity.
     */
    private int size = 0;

    /**
     * The storage for buckets. Each bucket is the head of SimpleLinkedList. SimpleLinkedList uses to avoid collisions.
     */
    private Object[] storage;

    /**
     * Simple hash set modification counter.
     */
    private int modCount = 0;

    /**
     * Default constructor.
     */
    public SimpleHashSet() {
        this.storage = new Object[SimpleHashSet.DEFAULT_SIZE];
    }

    /**
     * Constructor with the specified size of bucket-array.
     *
     * @param size - the specified size of bucket-array.
     */
    public SimpleHashSet(int size) {
        this.storage = new Object[size];
    }

    /**
     * Adds the specified element in this set if it is not present.
     *
     * @param value - the specified element.
     * @return true if adds the specified element.
     */
    public boolean add(E value) {
        if (this.loadCheck()) {
            this.rearrange();
        }
        boolean result = this.deploy(value, this.storage);
        if (result) {
            this.size++;
            this.modCount++;
        }
        return result;
    }

    /**
     * Deploy the specified value in the specified Object-array.
     *
     * @param value - the specified value.
     * @param deposit - the specified Object-array.
     * @return - true if deployment successful.
     */
    private boolean deploy(E value, Object[] deposit) {
        boolean result = false;
        int index = this.calcBucket(value);
        if (deposit[index] == null) {
            deposit[index] = new SimpleLinkedList<E>();
            if (!((SimpleLinkedList<E>) deposit[index]).contains(value)) {
                result = ((SimpleLinkedList<E>) deposit[index]).add(value);
            }
        } else {
            if (!((SimpleLinkedList<E>) deposit[index]).contains(value)) {
                result = ((SimpleLinkedList<E>) deposit[index]).add(value);
            }
        }
        return result;
    }

    /**
     * Return true if this set contains the specified element.
     *
     * @param value - the specified element.
     * @return - true if this set contains the specified element.
     */
    public boolean contains(Object value) {
        return ((SimpleLinkedList<E>) this.storage[this.calcBucket((E) value)]).contains((E) value);
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param value - the specified element.
     * @return true if delete the specified element.
     */
    public boolean remove(Object value) {
        boolean result = false;
        int index = this.calcBucket((E) value);
        if (((SimpleLinkedList<E>) this.storage[index]).contains((E) value)) {
            ((SimpleLinkedList<E>) this.storage[index]).remove(((SimpleLinkedList<E>) this.storage[index]).indexOf(value));
            this.size--;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set.
     */
    public int size() {
        return this.size;
    }

    /**
     * Removes all of the elements from this set.
     */
    public void clear() {
        for (int index = 0; index < this.storage.length; index++) {
            this.storage[index] = null;
        }
        this.size = 0;
    }

    /**
     * Calculates the index of bucket to deploy the specified value.
     *
     * @param value - the specified value.
     * @return - the index of bucket.
     */
    private int calcBucket(E value) {
        return value.hashCode() % this.storage.length;
    }

    /**
     * Checks load of the storage.
     */
    private boolean loadCheck() {
        return (float) this.size / (float) this.storage.length >= SimpleHashSet.LOAD_FACTOR;
    }

    /**
     * Rearranges the storage if current load exceed the LOAD_FACTOR.
     */
    private void rearrange() {
        int oldCapacity = this.storage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newStorage = new Object[newCapacity];
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            this.deploy(iterator.next(), newStorage);
        }
        this.storage = newStorage;
    }

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(" ");
        for (int index = 0; index < this.storage.length; index++) {
            if (this.storage[index] != null) {
                result.add(this.storage[index].toString());
            }
        }
        return result.toString();
    }

    /**
     * Returns the size of bucket-array.
     *
     * @return the size of bucket-array.
     */
    public int getLength() {
        return this.storage.length;
    }

    /**
     * Calculates loading of SimpleHashSet. Calculates all elements with collision chains, not only filled buckets.
     *
     * @return loading of SimpleHashSet.
     */
    public float getLoad() {
        return (float) this.size / (float) this.storage.length;
    }

    /**
     * Returns an iterator over the elements in this SimpleHashSet.
     *
     * @return iterator over this SimpleHashSet.
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleHashSetIterator<E>();
    }

    /**
     * Describes an iterator over the elements of SimpleHashSet.
     *
     * @param <E> - Type of elements.
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 05/06/2018
     */
    private class SimpleHashSetIterator<E> implements Iterator<E> {
        /**
         * Current iterator.
         */
        private Iterator<E> currentIt = null;

        /**
         * The current position of iterator between storage cells.
         */
        private int position = 0;

        /**
         * Initiates current iterator.
         */
        private void init() {
            if (this.currentIt == null && size != 0) {
                for (int index = position; index < storage.length; index++) {
                    if (storage[index] != null) {
                        position = index;
                        this.currentIt = ((SimpleLinkedList<E>) storage[index]).iterator();
                        break;
                    }
                }
            }
        }

        /**
         * Number of expected modifications. If number of modification changes throws exception.
         */
        private int expectedMod = modCount;

        /**
         * Checks for next element.
         *
         * @return true if this SimpleHashSet contains next element.
         */
        @Override
        public boolean hasNext() {
            this.init();
            boolean result = false;
            if (size != 0) {
                boolean flag = false;
                for (int index = position + 1; index < storage.length; index++) {
                    if (storage[index] != null) {
                        flag = true;
                        break;
                    }
                }
                result = this.currentIt.hasNext() || flag;
            }
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
            E result = null;
            this.init();
            if (this.currentIt != null && this.currentIt.hasNext()) {
                result = this.currentIt.next();
            } else {
                boolean flag = false;
                for (int index = position + 1; index < storage.length; index++) {
                    if (storage[index] != null) {
                        flag = true;
                        this.position = index;
                        break;
                    }
                }
                if (flag) {
                    this.currentIt = ((SimpleLinkedList<E>) storage[position]).iterator();
                    result = this.currentIt.next();
                }
            }
            if (result == null) {
                throw new NoSuchElementException("No more items!");
            }
            return (E) result;
        }

        /**
         * Checks for modification while iterator is working. If number of modification changes throws exception.
         */
        private void modificationCheck() {
            if (this.expectedMod != modCount) {
                throw new ConcurrentModificationException("The Simple Hash Set was changed its structure!");
            }
        }
    }
}
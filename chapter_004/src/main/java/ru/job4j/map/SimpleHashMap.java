package ru.job4j.map;

import ru.job4j.list.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Simple Hash Map with generic type. Supports resolution of collisions by Simple Linked List.
 * Contains Iterator.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/06/2018
 */
public class SimpleHashMap<K, V> implements Iterable<Entry<K, V>> {
    /**
     * The default size of simple hash map table.
     */
    private static final int DEFAULT_CAPACITY = 100;

    /**
     * The load factor. If SimpleHashMap loading is higher than LOAD_FACTOR, than container requires rearrangement.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * The storage for hash map table. Each bucket is a head of SimpleLinkedList. SimpleLinkedList uses to avoid collisions.
     */
    private Object[] table;

    /**
     * Quantity of elements.
     */
    private int size = 0;

    /**
     * Simple hash map modification counter.
     */
    private int modCount = 0;

    /**
     * Constructs an empty HashMap with the default initial capacity (100) and the default load factor (0.75).
     */
    public SimpleHashMap() {
        this.table = new Object[SimpleHashMap.DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty HashMap with the specified initial capacity and the default load factor (0.75).
     * @param size - the initial capacity.
     */
    public SimpleHashMap(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Initial capacity is lower then \'0\'");
        }
        this.table = new Object[size];
    }

    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key - key with which the specified value is to be associated.
     * @param value - value to be associated with the specified key.
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public V put(K key, V value) {
        if (this.loadCheck()) {
            this.rearrange();
        }
        V oldValue = this.get(key);
        boolean result = this.deploy(key, value, this.table);
        if (result) {
            this.modCount++;
            this.size++;
        }
        return oldValue;
    }

    /**
     * Deploy the specified key-value pair in the specified Object-array.
     *
     * @param key - the specified key.
     * @param value - the specified value.
     * @param deposit - the specified Object-array.
     * @return - true if deployment successful.
     */
    private boolean deploy(K key, V value, Object[] deposit) {
        boolean result = false;
        int position = this.calcBucket(key, deposit.length);
        if (deposit[position] == null) {
            deposit[position] = new SimpleLinkedList<Entry<K, V>>();
        }
        Entry<K, V> newElement = new Entry<K, V>(key, value);
        Entry<K, V> temp;
        int index = 0;
        boolean noKey = true;
        Iterator listIt = ((SimpleLinkedList<Entry<K, V>>) deposit[position]).iterator();
        while (listIt.hasNext()) {
            temp = (Entry<K, V>) listIt.next();
            if (temp.getKey().equals(key)) {
                ((SimpleLinkedList<Entry<K, V>>) deposit[position]).set(index, newElement);
                result = false;
                noKey = false;
                break;
            }
            index++;
        }
        if (noKey) {
            result = ((SimpleLinkedList<Entry<K, V>>) deposit[position]).add(newElement);
        }
        return result;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key - the key whose associated value is to be returned.
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    public V get(Object key) {
        int position = this.calcBucket((K) key, this.table.length);
        V value = null;
        Entry<K, V> temp;
        if ((SimpleLinkedList<Entry<K, V>>) this.table[position] != null) {
            Iterator listIt = ((SimpleLinkedList<Entry<K, V>>) this.table[position]).iterator();
            while (listIt.hasNext()) {
                temp = (Entry<K, V>) listIt.next();
                if (temp.getKey().equals(key)) {
                    value = temp.getValue();
                    break;
                }
            }
        }
        return value;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key - the specified key of key-value pair.
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public V remove(Object key) {
        int position = this.calcBucket((K) key, this.table.length);
        V value = null;
        Entry<K, V> temp;
        int listIndex = 0;
        if ((SimpleLinkedList<Entry<K, V>>) this.table[position] != null) {
            Iterator listIt = ((SimpleLinkedList<Entry<K, V>>) this.table[position]).iterator();
            while (listIt.hasNext()) {
                temp = (Entry<K, V>) listIt.next();
                if (temp.getKey().equals(key)) {
                    value = temp.getValue();
                    ((SimpleLinkedList<Entry<K, V>>) this.table[position]).remove(listIndex);
                    if (((SimpleLinkedList<Entry<K, V>>) this.table[position]).size() == 0) {
                        this.table[position] = null;
                    }
                    break;
                }
                listIndex++;
            }
        }
        if (value != null) {
            this.size--;
            this.modCount++;
        }
        return value;
    }

    /**
     * Returns the number of elements (key-value pairs) in this map.
     *
     * @return the number of elements (key-value pairs) in this map.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this map contains no elements.
     *
     * @return true if this map contains no elements.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        for (int index = 0; index < this.table.length; index++) {
            this.table[index] = null;
        }
        this.size = 0;
        this.modCount++;
    }

    /**
     * Calculates the index of bucket to deploy the specified value.
     *
     * @param key - the key.
     * @param size - the size of the table.
     * @return - the index of bucket.
     */
    private int calcBucket(K key, int size) {
        int bucket = key.hashCode() % size;
        return (bucket >= 0) ? bucket : -bucket;
    }

    /**
     * Checks load of the table.
     */
    private boolean loadCheck() {
        return (float) this.size / (float) this.table.length >= SimpleHashMap.LOAD_FACTOR;
    }

    /**
     * Rearranges the storage if current load exceed the LOAD_FACTOR.
     */
    private void rearrange() {
        int oldCapacity = this.table.length;
        int newCapacity = oldCapacity << 1;
        Object[] newTable = new Object[newCapacity];
        Iterator<Entry<K, V>> iter = this.iterator();
        Entry<K, V> temp;
        while (iter.hasNext()) {
            temp = iter.next();
            this.deploy(temp.getKey(), temp.getValue(), newTable);
        }
        this.table = newTable;
    }

    /**
     * Returns an iterator over the elements in this SimpleHashMap.
     *
     * @return iterator over this SimpleHashMap
     * .
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new SimpleHashMapIterator<Entry<K, V>>();
    }

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(" ");
        for (int index = 0; index < this.table.length; index++) {
            if (this.table[index] != null) {
                result.add(this.table[index].toString());
            }
        }
        return result.toString();
    }

    /**
     * Describes an iterator over the elements of SimpleHashSet.
     *
     * @param <E> - Type of elements. Pairs keys-values.
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 09/06/2018
     */
    private class SimpleHashMapIterator<E extends Entry<K, V>> implements Iterator<E> {
        /**
         * Current iterator.
         */
        private Iterator<E> currentIterator = null;

        /**
         * The current position of iterator between storage cells.
         */
        private int position = 0;

        /**
         * Initiates current iterator.
         */
        private void init() {
            if (this.currentIterator == null && size != 0) {
                for (int index = 0; index < table.length; index++) {
                    if (table[index] != null) {
                        this.currentIterator = ((SimpleLinkedList<E>) table[index]).iterator();
                        this.position = index;
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
         * @return true if this SimpleHashMap contains next element.
         */
        @Override
        public boolean hasNext() {
            this.init();
            boolean result = false;
            if (size != 0) {
                boolean flag = false;
                for (int index = this.position + 1; index < table.length; index++) {
                    if (table[index] != null) {
                        flag = true;
                        break;
                    }
                }
                result = this.currentIterator.hasNext() || flag;
            }
            return result;
        }

        /**
         * Gets current element and moves pointer forward.
         * If the simple hash map contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            this.modificationCheck();
            E result = null;
            this.init();
            if (this.currentIterator != null && this.currentIterator.hasNext()) {
                result = this.currentIterator.next();
            } else {
                boolean flag = false;
                for (int index = this.position + 1; index < table.length; index++) {
                    if (table[index] != null) {
                        flag = true;
                        this.position = index;
                        break;
                    }
                }
                if (flag) {
                    this.currentIterator = ((SimpleLinkedList<E>) table[position]).iterator();
                    result = this.currentIterator.next();
                }
            }
            if (result == null) {
                throw new NoSuchElementException("No more elements!");
            }
            return result;
        }

        /**
         * Checks for modification while iterator is working. If number of modification changes throws exception.
         */
        private void modificationCheck() {
            if (this.expectedMod != modCount) {
                throw new ConcurrentModificationException("The Simple Hash Map was changed its structure!");
            }
        }
    }
}
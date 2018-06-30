package ru.job4j.map;

/**
 * Simple Entry. Describes simply key-value pair.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/06/2018
 */
public class Entry<K, V> {
    /**
     * The key.
     */
    private final K key;

    /**
     * The value.
     */
    private V value;

    /**
     * The Entry constructor.
     *
     * @param key - specified key.
     * @param value - specified value.
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key.
     *
     * @return the key.
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * Compares this pair with other pair.
     *
     * @param o - the other pair.
     * @return true if pairs are equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Entry<?, ?> entry = (Entry<?, ?>) o;
            if (key != null ? !key.equals(entry.key) : entry.key != null) {
                result = false;
            } else {
                result = value != null ? value.equals(entry.value) : entry.value == null;
            }
        }
        return result;
    }

    /**
     * Calculates the hashcode of this pair.
     *
     * @return the hashcode.
     */
    @Override
    public int hashCode() {
        int result = this.key != null ? this.key.hashCode() : 0;
        result = 31 * result + (this.value != null ? this.value.hashCode() : 0);
        return result;
    }

    /**
     * Represents this key-value pair in string-view.
     *
     * @return - presentation in string-view.
     */
    @Override
    public String toString() {
        return String.format("{Key = " + this.key + ", value = " + this.value + "}");
    }
}
package ru.job4j.list;

/**
 * Base list interface with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 19/05/2018
 */
public interface BaseList<E> extends Iterable<E> {
    /**
     * Appends the specified element to the end of this list.
     *
     * @param value - appended value.
     * @return - true if success.
     */
    boolean add(E value);

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index - the specified position.
     * @param value - the specified element.
     */
    void add(int index, E value);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index - the specified position.
     * @param value - the specified new element.
     * @return - old element.
     */
    E set(int index, E value);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index - the specified position.
     * @return - the element in the specified position.
     */
    E get(int index);

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index - the specified position.
     * @return - removed element.
     */
    E remove(int index);

    /**
     * Returns the number of elements in this list.
     *
     * @return - the number of elements in this list.
     */
    int size();

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    int indexOf(Object o);

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    boolean contains(Object o);
}

package ru.job4j.tree;

import java.util.*;

/**
 * The realization of Simple Tree interface.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 15/06/2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * The root of the tree.
     */
    private final Node<E> root;

    /**
     * The modification counter.
     */
    private int modCount = 0;

    /**
     * The constructor. Initiates the tree root.
     *
     * @param value - the root value.
     */
    public Tree(E value) {
        this.root = new Node<E>(value);
    }

    /**
     * Adds the specified element (child) to the specified position (as the parent's child). If parent contains
     * the child with the same value, than nothing happens. The result will be false.
     *
     * @param parent - the parent.
     * @param child - the child.
     * @return true if addiction was successful.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> element = this.findBy(parent);
        Node<E> parentNode;
        if (element.isPresent()) {
            parentNode = element.get();
            boolean flag = true;
            Queue<Node<E>> queue = new LinkedList<Node<E>>();
            queue.offer(this.root);
            while (!queue.isEmpty()) {
                Node<E> node = queue.poll();
                if (node.eqValue(child)) {
                    flag = false;
                    break;
                }
                for (Node<E> childNode : node.leaves()) {
                    queue.offer(childNode);
                }
            }
            if (flag) {
                parentNode.add(new Node<E>(child));
                result = true;
                this.modCount++;
            }
        }
        return result;
    }

    /**
     * Checks if the tree contains the value.
     *
     * @param value - the value.
     * @return - the Node with the Value.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<Node<E>>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Checks if tree is binary (e.g. each node has <= 2 children).
     *
     * @return true if this tree is binary.
     */
    public boolean isBinary() {
        boolean result = true;
        int counter = 0;
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            counter = 0;
            for (Node<E> child : node.leaves()) {
                queue.offer(child);
                counter++;
            }
            if (counter > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Returns an iterator over the elements in this Tree.
     *
     * @return iterator over this Tree.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<E>();
    }

    /**
     * Describes an iterator over the elements of Tree.
     *
     * @param <E> - Type of elements.
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 09/07/2018
     */
    private class TreeIterator<E extends Comparable<E>> implements Iterator<E> {
        /**
         * The queue for all tree nodes.
         */
        private Queue<Node<E>> data = new LinkedList<Node<E>>();

        /**
         * The root.
         */
        private Node<E> first;

        /**
         * Number of expected modifications. If number of modification changes throws exception.
         */
        private int expectedMod = modCount;

        /**
         * Initiates current iterator.
         */
        private void init() {
            if (this.first == null) {
                this.first = (Node<E>) root;
                this.data.offer(this.first);
            }
        }

        /**
         * Checks for next element.
         *
         * @return true if this SimpleHashSet contains next element.
         */
        @Override
        public boolean hasNext() {
            this.init();
            boolean result = false;
            if (!this.data.isEmpty()) {
                result = true;
            }
            return result;
        }

        /**
         * Gets first element and moves pointer forward (to the child or to the next leave).
         * If the tree contains no more elements, throws NoSuchElementException.
         *
         * @return first element.
         */
        @Override
        public E next() {
            this.modificationCheck();
            this.init();
            E result = null;
            if (!this.data.isEmpty()) {
                Node<E> element = this.data.poll();
                result = element.getValue();
                for (Node<E> node : element.leaves()) {
                    this.data.offer(node);
                }
            }
            if (result == null) {
                throw new NoSuchElementException("No more items!");
            }
            return result;
        }

        /**
         * Checks for modification while iterator is working. If number of modification changes throws exception.
         */
        private void modificationCheck() {
            if (this.expectedMod != modCount) {
                throw new ConcurrentModificationException("Thi three was changed!");
            }
        }
    }
}
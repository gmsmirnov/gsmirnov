package ru.job4j.list;

/**
 * Simple queue based on two stacks with generic type.
 * It pushes and polls elements in direct order. It pushes elements into input stack and it polls elements from output
 * stack. If output stack is empty then it polls all elements from the input stack and pushes them into the output
 * stack. The order is FIFO (first in, first out).
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/09/2018
 */
public class SimpleQueueOnTwoStacks<E> {
    /**
     * The input stack.
     */
    private final SimpleStack<E> inStack = new SimpleStack<E>();

    /**
     * The output stack.
     */
    private final SimpleStack<E> outStack = new SimpleStack<E>();

    /**
     * Add new element into the end of the queue.
     *
     * @param value - new element.
     */
    public void push(E value) {
        this.inStack.push(value);
    }

    /**
     * Take the element from the head of the queue.
     *
     * @return the first element of the queue.
     */
    public <E> E poll() {
        if (this.outStack.isEmpty()) {
            while (!this.inStack.isEmpty()) {
                this.outStack.push(this.inStack.poll());
            }
        }
        return (E) this.outStack.poll();
    }
}
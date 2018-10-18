package ru.job4j.list;

/**
 * Detects cycle in the list of Nodes.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 22/05/2018
 */
public class DetectCycle {
    boolean hasCycle(Node first) {
        boolean result = false;
        Node slow = first;
        Node fast = null;
        if (slow != null && slow.hasNext()) {
            fast = slow.next;
            while (slow.next != null || fast.next != null) {
                if (slow.equals(fast)) {
                    result = true;
                    break;
                }
                slow = slow.next;
                if (fast.hasNext()) {
                    fast = fast.next.next;
                }
            }
        }
        return result;
    }

    static class Node<E> {
        /**
         * The Node value (data).
         */
        private E value;

        /**
         * Pointer to the next node.
         */
        Node<E> next;

        /**
         * Node constructor.
         *
         * @param value - new value (data).
         */
        public Node(E value) {
            this.value = value;
        }

        /**
         * Compare this node with the other specified node.
         *
         * @param o - other node.
         * @return true if nodes are equals.
         */
        @Override
        public boolean equals(Object o) {
            boolean result;
            if (this == o) {
                result = true;
            } else if (o == null || getClass() != o.getClass()) {
                result = false;
            } else {
                Node<?> node = (Node<?>) o;
                if (value != null ? !value.equals(node.value) : node.value != null) {
                    return false;
                }
                result = next != null ? next.equals(node.next) : node.next == null;
            }
            return result;
        }

        /**
         * Calculate hash code.
         *
         * @return hash code.
         */
        @Override
        public int hashCode() {
            int result = value != null ? value.hashCode() : 0;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        public boolean hasNext() {
            return this.next != null;
        }
    }
}
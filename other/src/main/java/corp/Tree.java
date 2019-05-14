package corp;

import java.util.*;

/**
 * The tree represents a corporation structure.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/05/2019
 */
public class Tree {
    /**
     * The index of the root element.
     */
    private static final int ROOT_INDEX = 0;

    /**
     * The escape character for each branch of subordinates chain.
     */
    private static final int ESCAPE_CHARACTER = 0;

    /**
     * The director.
     */
    private Node root;

    /**
     * The corporation's tree constructor.
     *
     * @param branches - branches of subordinates.
     */
    public Tree(List<List<Integer>> branches) {
        for (List<Integer> branch : branches) {
            this.buildBranch(branch);
        }
    }

    /**
     * Create a new branch of subordinates in this corporation tree.
     *
     * @param branch - a new branch.
     */
    private void buildBranch(List<Integer> branch) {
        if (this.root == null) {
            this.root = new Node(branch.get(Tree.ROOT_INDEX));
            this.root.addChild(new Node(branch.get(Tree.ROOT_INDEX + 1)));
        } else if (this.root.getValue() == branch.get(Tree.ROOT_INDEX)) {
            if (!this.root.getChildren().contains(new Node(branch.get(Tree.ROOT_INDEX + 1)))) {
                this.root.addChild(new Node(branch.get(Tree.ROOT_INDEX + 1)));
            }
        }
        Node cursor = null;
        List<Node> children = this.root.getChildren();
        cursor = this.getRightChild(this.root.getChildren(), branch.get(Tree.ROOT_INDEX + 1));
        for (int index = 2; index < branch.size(); index++) {
            cursor.addChild(new Node(branch.get(index)));
            cursor = this.getRightChild(cursor.getChildren(), branch.get(index));
        }
    }

    /**
     * Finds the right subordinate in the list of subordinates of current node to continue chain of subordinates building.
     * If there is another chain of subordinates with the same ancestors, then we should find the right children,
     * it is already in the list of children and we don't need to append it to the list.
     *
     * @param children - all children of current node.
     * @param value - the value of needed child.
     * @return the right child.
     */
    private Node getRightChild(List<Node> children, int value) {
        Node result = null;
        for (Node child : children) {
            if (child.getValue() == value) {
                result = child;
            }
        }
        return result;
    }

    /**
     * The breadth-first search (BFS) method which gathers all the children of each node into the result map,
     * the key is the current node's value.
     *
     * @return the map in which every node's value (key) matches this node's list of children.
     */
    public Map<Integer, List<Integer>> pass() {
        Map<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node cursor = queue.poll();
            if (cursor.getChildren() != null) {
                this.fillMap(result, cursor);
                for (Node child : cursor.getChildren()) {
                    queue.offer(child);
                }
            } else {
                result.put(cursor.getValue(), Arrays.asList(Tree.ESCAPE_CHARACTER));
            }
        }
        return result;
    }

    /**
     * Fills the map with children's values. The key is the parent's value.
     *
     * @param result - the result map in which every node's value (key) matches this node's list of children.
     * @param node - the needed node.
     * @return the result map in which every node's value (key) matches this node's list of children.
     */
    private Map<Integer, List<Integer>> fillMap(Map<Integer, List<Integer>> result, Node node) {
        List<Integer> childrenValues = new ArrayList<Integer>();
        ArrayList<Node> children = node.getChildren();
        if (!children.isEmpty()) {
            for (Node child : children) {
                childrenValues.add(child.getValue());
            }
            childrenValues.add(Tree.ESCAPE_CHARACTER);
            result.put(node.getValue(), childrenValues);
        }
        return result;
    }

    /**
     * The tree's node.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 13/05/2019
     */
    static class Node {
        /**
         * The node's value.
         */
        private int value;

        /**
         * The node's children.
         */
        ArrayList<Node> children;

        /**
         * The node's constructor. Creates a new node with the specified value.
         *
         * @param value - specified node's value.
         */
        public Node(int value) {
            this.value = value;
        }

        /**
         * Gets this node's value.
         *
         * @return this node's value.
         */
        public int getValue() {
            return this.value;
        }

        /**
         * Gets this node's list of children.
         *
         * @return this node's list of children.
         */
        public ArrayList<Node> getChildren() {
            return this.children;
        }

        /**
         * Append a new child to the list of this node's children.
         *
         * @param node - a new child.
         */
        public void addChild(Node node) {
            if (this.children == null) {
                this.children = new ArrayList<Node>();
            }
            if (!this.children.contains(node)) {
                this.children.add(node);
            }
        }

        /**
         * Compare this node with other node for equivalence.
         *
         * @param o - the other node.
         * @return true if these nodes are equals.
         */
        @Override
        public boolean equals(Object o) {
            boolean result;
            if (this == o) {
                result = true;
            } else if (o == null || getClass() != o.getClass()) {
                result = false;
            } else {
                Node node = (Node) o;
                result = value == node.value;
            }
            return result;
        }

        /**
         * Calculates the hash-code of this node.
         *
         * @return calculated hash-code of this node.
         */
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
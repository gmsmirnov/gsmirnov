package berlyandiya.gas;

import java.util.LinkedList;
import java.util.List;

/**
 * The graph represents a structure with distances between gas stations.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/05/2019
 */
public class Graph {
    /**
     * The list of nodes.
     */
    private List<Node> graph = new LinkedList<Node>();

    /**
     * Gets the list of all nodes.
     *
     * @return the list of all nodes.
     */
    public List<Node> getNodes() {
        return this.graph;
    }

    /**
     * Add a new node into the list and recount weights sum (the sum of distances between this gas station and all other
     * gas stations).
     *
     * @param node - a new node (gas station).
     */
    public void addNode(Node node) {
        this.addVertex(node);
        this.recountSum();
    }

    /**
     * Removes the specified node (gas station from the structure), removes unneeded distances and recount weights sum.
     *
     * @param node - the specified node.
     */
    public void removeNode(Node node) {
        this.removeVertex(node);
        this.recountSum();
    }

    /**
     * Counts a distance between two gas stations.
     *
     * @param val1 - the first station's coordinate.
     * @param val2 - the second station's coordinate.
     * @return s distance between these stations.
     */
    private static int mod(int val1, int val2) {
        return val1 > val2 ? val1 - val2 : val2 - val1;
    }

    /**
     * A part of 'addNode' method. Appends a new node and distance weights.
     *
     * @param node - a new node.
     */
    private void addVertex(Node node) {
        node.emptyWeights();
        if (this.graph.size() == 0) {
            this.graph.add(node);
        } else {
            for (Node vertex : this.graph) {
                vertex.weights.add(Graph.mod(vertex.getValue(), node.getValue()));
                node.weights.add(Graph.mod(vertex.getValue(), node.getValue()));
            }
            this.graph.add(node);
        }
    }

    /**
     * A part of 'removeNode' method. Removes the specified node from this graph and removes unneeded weights.
     *
     * @param node - the specified node.
     */
    private void removeVertex(Node node) {
        if (this.graph.size() > 0) {
            this.graph.remove(node);
            for (Node vertex : this.graph) {
                int index = vertex.getWeights().indexOf(Graph.mod(vertex.getValue(), node.getValue()));
                vertex.weights.remove(index);
            }
        }
    }

    /**
     * Recounts the weights sum for all nodes of the graph.
     */
    private void recountSum() {
        for (Node node : this.graph) {
            node.weightsSum = 0;
            for (Integer weight : node.weights) {
                node.weightsSum += weight;
            }
        }
    }

    /**
     * Searches for the minimum weight.
     *
     * @return the minimum weight.
     */
    public int minWeight() {
        List<Integer> weights = new LinkedList<Integer>();
        for (Node node : this.getNodes()) {
            weights.addAll(node.getWeights());
        }
        weights.sort(Logic.weightsComparator);
        return weights.get(0);
    }

    /**
     * The String representation of the graph.
     *
     * @return the String representation of the graph.
     */
    @Override
    public String toString() {
        return String.format("Graph{graph=%s}", this.graph);
    }

    /**
     * The graph's node.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 15/05/2019
     */
    static class Node {
        /**
         * The value.
         */
        private int value;

        /**
         * The list of weights (distances between this node and all other nodes)
         */
        List<Integer> weights;

        /**
         * The sum of weights (distances).
         */
        int weightsSum;

        /**
         * Creates a new node with the specified value.
         *
         * @param value - the specified value.
         */
        public Node(int value) {
            this.value = value;
            this.weights = new LinkedList<Integer>();
        }

        /**
         * Creates a new node with the specified value and weights list.
         *
         * @param value - the specified value.
         * @param weights - the specified weights list.
         */
        public Node(int value, List<Integer> weights) {
            this.value = value;
            this.weights = weights;
            for (Integer weight : weights) {
                this.weightsSum += weight;
            }
        }

        /**
         * Gets the value of the node.
         *
         * @return the value of the node.
         */
        public int getValue() {
            return this.value;
        }

        /**
         * Gets the weights list.
         *
         * @return the weights list.
         */
        public List<Integer> getWeights() {
            return this.weights;
        }

        /**
         * Gets the weights sum.
         *
         * @return the weights sum.
         */
        public int getWeightsSum() {
            return this.weightsSum;
        }

        /**
         * Empties the list of weights and clear weights sum.
         */
        public void emptyWeights() {
            this.weights.clear();
            this.weightsSum = 0;
        }

        /**
         * The String representation of the node.
         *
         * @return - the String representation of the node.
         */
        @Override
        public String toString() {
            return String.format("Node{value=%s, weights=%s, weightsSum=%d}", this.value, this.weights, this.weightsSum);
        }
    }
}
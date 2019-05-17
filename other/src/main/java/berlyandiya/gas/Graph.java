package berlyandiya.gas;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<Node> graph = new LinkedList<Node>();

    public List<Node> getNodes() {
        return this.graph;
    }

    public void addNode(Node node) {
        this.addVertex(node);
        this.recountSum();
    }

    private static int mod(int val1, int val2) {
        return val1 > val2 ? val1 - val2 : val2 - val1;
    }

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

    private void recountSum() {
        for (Node node : this.graph) {
            node.weightsSum = 0;
            for (Integer weight : node.weights) {
                node.weightsSum += weight;
            }
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "graph=" + this.graph +
                '}';
    }

    static class Node {
        private int value;

        List<Integer> weights;

        int weightsSum;

        public Node(int value) {
            this.value = value;
            this.weights = new LinkedList<Integer>();
        }

        public Node(int value, List<Integer> weights) {
            this.value = value;
            this.weights = weights;
            for (Integer weight : weights) {
                this.weightsSum += weight;
            }
        }

        public int getValue() {
            return this.value;
        }

        public List<Integer> getWeights() {
            return this.weights;
        }

        public int getWeightsSum() {
            return this.weightsSum;
        }

        public void emptyWeights() {
            this.weights.clear();
            this.weightsSum = 0;
        }

        /**
         * Deletes unnecessary weights.
         */
        public void modifyNode(List<Integer> weights) {
            for (Integer weight : weights) {
                if (this.weights.contains(weight)) {
                    this.weights.remove(weight);
                }
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + this.value +
                    ", weights=" + this.weights +
                    ", weightsSum=" + this.weightsSum +
                    '}';
        }
    }
}
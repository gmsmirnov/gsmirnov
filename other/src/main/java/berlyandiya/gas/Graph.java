package berlyandiya.gas;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<Node> graph = new LinkedList<Node>();

    static class Node {
        private int value;

        List<Integer> weights = new LinkedList<Integer>();

        public Node(int value, List<Integer> weights) {
            this.value = value;
            this.weights = weights;
        }

        public int getValue() {
            return this.value;
        }

        public List<Integer> getWeights() {
            return this.weights;
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
    }
}
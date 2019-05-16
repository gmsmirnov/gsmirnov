package berlyandiya.gas;

import java.util.Comparator;
import java.util.List;

/**
 * The logic of application.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/05/2019
 */
public class Logic {
    /**
     * Finds the node with the cheapest sum of weights.
     *
     * @param graph - the specified graph of gas stations.
     * @return the node with the cheapest sum of weights.
     */
    public static Graph.Node findCheapest(Graph graph) {
        Graph.Node result = null;
        if (graph.getNodes().size() > 1) {
            result = graph.getNodes().get(0);
            for (Graph.Node node : graph.getNodes()) {
                if (result.getWeightsSum() > node.getWeightsSum()) {
                    result = node;
                } else if (result.getWeightsSum() == node.getWeightsSum()) {
                    result = Logic.hasLowerWeight(result, node);
                }
            }
        } else if (graph.getNodes().size() == 1) {
            result = graph.getNodes().get(0);
        }
        return result;
    }

    /**
     * If there are two nodes with equals weights sums, then we should get the node with minimum weight
     * (distance between two gas stations).
     *
     * @param node1 - the first specified node.
     * @param node2 - the second specified node.
     * @return the node with lower weight.
     */
    public static Graph.Node hasLowerWeight(Graph.Node node1, Graph.Node node2) {
        Graph.Node result = node2;
        List<Integer> list1 = node1.getWeights();
        list1.sort(Logic.weightsComparator);
        List<Integer> list2 = node2.getWeights();
        list2.sort(Logic.weightsComparator);
        for (int index = 0; index < list1.size(); index++) {
            if (list1.get(index) < list2.get(index)) {
                result = node1;
                break;
            }
        }
        return result;
    }

    /**
     * The comparator for integer values.
     */
    public static Comparator<Integer> weightsComparator = (o1, o2) -> {
        int result = 0;
        if (o1 > o2) {
            result = 1;
        } else if (o1 < o2) {
            result = -1;
        }
        return result;
    };

    /**
     * Deletes unneeded nodes from the gas stations graph.
     *
     * @param graph - the specified graph.
     * @param qnt - the quantity of unneeded nodes.
     * @return the graph without unneeded nodes.
     */
    public static Graph deleteUnneeded(Graph graph, int qnt) {
        while (qnt > 0) {
            graph.removeNode(Logic.findCheapest(graph));
            qnt--;
        }
        return graph;
    }
}
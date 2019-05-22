package berlyandiya.gas;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.*;

/**
 * Logic testing. Also there are work demonstration tests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 20/05/2019
 */
public class LogicTest {
    @Test
    public void ifFoundCheapestThenTrue() {
        Graph graph = new Graph();
        Graph.Node node12 = new Graph.Node(12);
        Graph.Node node96 = new Graph.Node(96);
        Graph.Node node6 = new Graph.Node(6);
        Graph.Node node34 = new Graph.Node(34);
        Graph.Node node73 = new Graph.Node(73);
        graph.addNode(node12);
        graph.addNode(node96);
        graph.addNode(node6);
        graph.addNode(node34);
        graph.addNode(node73);
        assertThat(Logic.findCheapest(graph), is(node34));
        graph.removeNode(node34); // remove cheapest
        assertThat(Logic.findCheapest(graph), is(node12));
        graph.removeNode(node12); // remove cheapest
        assertThat(Logic.findCheapest(graph), is(node73));
        graph.removeNode(node73); // remove cheapest
        assertThat(Logic.findCheapest(graph), isOneOf(node6, node96));
        graph.removeNode(node6); // remove cheapest
        assertThat(Logic.findCheapest(graph), isOneOf(node6, node96));
    }

    @Test
    public void ifThereAreTwoCheapest() {
        Graph graph = new Graph();
        Graph.Node node40 = new Graph.Node(40);
        Graph.Node node80 = new Graph.Node(80);
        Graph.Node node50 = new Graph.Node(50);
        Graph.Node node60 = new Graph.Node(60);
        graph.addNode(node40);
        graph.addNode(node80);
        graph.addNode(node50);
        graph.addNode(node60);
        assertThat(Logic.findCheapest(graph), is(node50));
    }

    /**
     * The first example:
     *  gas stations - 12, 96, 6, 34, 73
     *  needs to delete - 3
     *
     * Output:
     *  left stations - 96, 6
     *  min distance - 90
     */
    @Test
    public void firstExample() {
        Graph graph = new Graph();
        Graph.Node node12 = new Graph.Node(12);
        Graph.Node node96 = new Graph.Node(96);
        Graph.Node node6 = new Graph.Node(6);
        Graph.Node node34 = new Graph.Node(34);
        Graph.Node node73 = new Graph.Node(73);
        graph.addNode(node12);
        graph.addNode(node96);
        graph.addNode(node6);
        graph.addNode(node34);
        graph.addNode(node73);
        int deleteQnt = 3;
        Logic.deleteUnneeded(graph, deleteQnt);
        System.out.println(String.format("Gas stations to be left: %s", graph.getNodes()));
        System.out.println(String.format("The minimum weight is: %d", graph.minWeight()));
    }

    /**
     * The first example:
     *  gas stations - 40, 80, 50, 60
     *  needs to delete - 1
     *
     * Output:
     *  left stations - 40, 80, 60
     *  min distance - 20
     */
    @Test
    public void secondExample() {
        Graph graph = new Graph();
        Graph.Node node40 = new Graph.Node(40);
        Graph.Node node80 = new Graph.Node(80);
        Graph.Node node50 = new Graph.Node(50);
        Graph.Node node60 = new Graph.Node(60);
        graph.addNode(node40);
        graph.addNode(node80);
        graph.addNode(node50);
        graph.addNode(node60);
        int deleteQnt = 1;
        Logic.deleteUnneeded(graph, deleteQnt);
        System.out.println(String.format("Gas stations to be left: %s", graph.getNodes()));
        System.out.println(String.format("The minimum weight is: %d", graph.minWeight()));
    }

    /**
     * The third example:
     *  gas stations - 2, 2, 2
     *  needs to delete - 1
     *
     * Output:
     *  left stations - 2, 2
     *  min distance - 0
     */
    @Test
    public void thirdExample() {
        Graph graph = new Graph();
        Graph.Node node21 = new Graph.Node(2);
        Graph.Node node22 = new Graph.Node(2);
        Graph.Node node23 = new Graph.Node(2);
        graph.addNode(node21);
        graph.addNode(node22);
        graph.addNode(node23);
        int deleteQnt = 1;
        Logic.deleteUnneeded(graph, deleteQnt);
        System.out.println(String.format("Gas stations to be left: %s", graph.getNodes()));
        System.out.println(String.format("The minimum weight is: %d", graph.minWeight()));
    }
}
package berlyandiya.gas;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GraphTest {
    @Test
    public void whenAddNodesThenCheckWeightsSumIsTrue() {
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
        int index12 = graph.getNodes().indexOf(node12);
        assertThat(graph.getNodes().get(index12).getWeightsSum(), is(173));
        int index96 = graph.getNodes().indexOf(node96);
        assertThat(graph.getNodes().get(index96).getWeightsSum(), is(259));
        int index6 = graph.getNodes().indexOf(node6);
        assertThat(graph.getNodes().get(index6).getWeightsSum(), is(191));
        int index34 = graph.getNodes().indexOf(node34);
        assertThat(graph.getNodes().get(index34).getWeightsSum(), is(151));
        int index73 = graph.getNodes().indexOf(node73);
        assertThat(graph.getNodes().get(index73).getWeightsSum(), is(190));
    }
}
package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.isTopologicalOrder;
import static org.junit.Assert.*;

public class IsTopologicalOrderTest {
    @Test
    public void testValidTopologicalOrder() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1,2), new DirectedEdge(2, 3)));
        assertTrue(isTopologicalOrder(g, List.of(0, 1, 2, 3)));

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(2, 3)));
        assertTrue(isTopologicalOrder(g1, List.of(0, 1, 2, 3)));
        assertTrue(isTopologicalOrder(g1, List.of(2, 3, 0, 1)));
    }
    @Test
    public void testInvalidTopologicalOrder() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3)));
        assertFalse(isTopologicalOrder(g, List.of(3, 2, 1, 0)));

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 0)));
        assertFalse(isTopologicalOrder(g1, List.of(0, 1, 2)));
        DirectedGraph g2 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(2, 3)));
        assertFalse(isTopologicalOrder(g2, List.of(1, 0, 2, 3)));
    }
}

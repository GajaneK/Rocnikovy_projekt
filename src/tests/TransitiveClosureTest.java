package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.transitiveClosure;
import static org.junit.Assert.*;

public class TransitiveClosureTest {
    @Test
    public void testTransitiveClosure() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(5, Set.of(new DirectedEdge(0,1), new DirectedEdge(1,2), new DirectedEdge(3,4)));
        DirectedGraph closure = transitiveClosure(g);
        assertTrue(closure.hasEdge(0, 1));
        assertTrue(closure.hasEdge(1, 2));
        assertTrue(closure.hasEdge(0, 2));

        assertTrue(closure.hasEdge(3, 4));
        assertFalse(closure.hasEdge(0, 3));
        assertFalse(closure.hasEdge(2, 4));
    }

    @Test
    public void testEmptyGraph() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Set.of());
        DirectedGraph closure = transitiveClosure(g);

        assertFalse(closure.hasEdge(0, 1));
        assertFalse(closure.hasEdge(1, 2));
    }

    @Test
    public void testFullyConnectedGraph() {
        Set<DirectedEdge> edges = Set.of(new DirectedEdge(0, 1), new DirectedEdge(0, 2),
                new DirectedEdge(1, 0), new DirectedEdge(1, 2),
                new DirectedEdge(2, 0), new DirectedEdge(2, 1));
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, edges);
        DirectedGraph closure = transitiveClosure(g);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    assertTrue(closure.hasEdge(i, j));
                }
            }
        }
    }
}

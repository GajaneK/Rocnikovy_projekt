package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.reflexiveTransitiveClosure;
import static org.junit.Assert.*;

public class ReflexiveTransitiveClosureTest {
    @Test
    public void testReflexiveTransitiveClosure() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(2, List.of(new DirectedEdge(0, 1)));
        DirectedGraph closure = reflexiveTransitiveClosure(g);

        assertTrue(closure.hasEdge(0, 0));
        assertTrue(closure.hasEdge(0, 1));
        assertTrue(closure.hasEdge(1, 1));

        assertFalse(closure.hasEdge(1, 0));

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 0)));
        DirectedGraph closure1 = reflexiveTransitiveClosure(g1);

        assertTrue(closure1.hasEdge(0, 0));
        assertTrue(closure1.hasEdge(0, 1));
        assertTrue(closure1.hasEdge(0, 2));
        assertTrue(closure1.hasEdge(1, 0));
        assertTrue(closure1.hasEdge(1, 1));
        assertTrue(closure1.hasEdge(1, 2));
        assertTrue(closure1.hasEdge(2, 0));
        assertTrue(closure1.hasEdge(2, 1));
        assertTrue(closure1.hasEdge(2, 2));

        DirectedGraph g2 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(2, 3)));
        DirectedGraph closure2 = reflexiveTransitiveClosure(g2);

        assertTrue(closure2.hasEdge(0, 0));
        assertTrue(closure2.hasEdge(0, 1));
        assertTrue(closure2.hasEdge(1, 1));

        assertTrue(closure2.hasEdge(2, 2));
        assertTrue(closure2.hasEdge(2, 3));
        assertTrue(closure2.hasEdge(3, 3));

        assertFalse(closure2.hasEdge(0, 2));
        assertFalse(closure2.hasEdge(1, 3));
    }
}

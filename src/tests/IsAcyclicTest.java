package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.isAcyclic;
import static org.junit.Assert.*;

public class IsAcyclicTest {

    @Test
    public void testAcyclicGraph() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(5, Set.of(new DirectedEdge(0, 1),
                new DirectedEdge(1, 2),
                new DirectedEdge(3, 4)));
        assertTrue(isAcyclic(g));
    }
    @Test
    public void testGraphWithCycle() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Set.of(new DirectedEdge(0, 1),
                new DirectedEdge(1, 2),
                new DirectedEdge(2, 0)));
        assertFalse(isAcyclic(g));
    }
    @Test
    public void testEmptyGraph() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Set.of());
        assertTrue(isAcyclic(g));
    }

    @Test
    public void testGraphWithIsolatedVertices() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, Set.of(new DirectedEdge(0, 1),
                new DirectedEdge(2, 3)));
        assertTrue(isAcyclic(g));
    }
}


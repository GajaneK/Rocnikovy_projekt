package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.isDirectedCycle;
import static org.junit.Assert.*;

public class IsDirectedCycleTest {

    @Test
    public void testEmpty() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Collections.emptyList());
        assertFalse(isDirectedCycle(g, Collections.emptyList()));
    }
    @Test
    public void testOneVertexInvalid() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Collections.emptyList());
        assertFalse(isDirectedCycle(g, Collections.singletonList(0)));
    }
    @Test
    public void testOneVertexValid() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 0)));
        assertTrue(isDirectedCycle(g, Collections.singletonList(0)));
    }

    @Test
    public void testValidDirectedCycle() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, Arrays.asList(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3), new DirectedEdge(3, 0)));
        assertTrue(isDirectedCycle(g, Arrays.asList(0, 1, 2, 3, 0)));
    }

    @Test
    public void testInvalidDirectedCycle() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, Arrays.asList(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3)));
        assertFalse(isDirectedCycle(g, Arrays.asList(0, 1, 2, 3, 0)));
    }
}

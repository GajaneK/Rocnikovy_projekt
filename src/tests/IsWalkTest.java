package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.isWalk;
import static org.junit.Assert.*;

public class IsWalkTest {

    @Test
    public void testEmpty() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Collections.emptyList());
        assertFalse(isWalk(g, Collections.emptyList()));
    }
    @Test
    public void testOneVertex() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Collections.emptyList());
        assertTrue(isWalk(g, Collections.singletonList(0)));
    }

    @Test
    public void testValidWalk() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, Arrays.asList(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2,3)));
        assertTrue(isWalk(g, Arrays.asList(0, 1, 2, 3)));
    }
    @Test
    public void testInvalidWalk() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1)));
        assertFalse(isWalk(g, Arrays.asList(0, 2)));
    }
    @Test
    public void testSelfLoop() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(1, 1)));
        assertTrue(isWalk(g, Arrays.asList(1, 1)));
    }

}

package tests;
import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.isPath;
import static org.junit.Assert.*;
public class IsPathTest {
    @Test
    public void testEmpty() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Collections.emptyList());
        assertFalse(isPath(g, Collections.emptyList()));
    }
    @Test
    public void testOneVertex() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, Collections.emptyList());
        assertTrue(isPath(g, Collections.singletonList(0)));
    }

    @Test
    public void testValidPath() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, Arrays.asList(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2,3)));
        assertTrue(isPath(g, Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void testInvalidPath() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 0)));
        assertFalse(isPath(g, Arrays.asList(0, 1, 2, 0)));
    }

}

package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.existsPath;
import static org.junit.Assert.*;

public class ExistsPathTest {
    @Test
    public void testExistsPath() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(0, 0)));
        assertTrue(existsPath(g, 0, 1));
        assertTrue(existsPath(g, 0, 2));
        assertFalse(existsPath(g, 2, 0));
        assertTrue(existsPath(g, 0, 0));
    }
}

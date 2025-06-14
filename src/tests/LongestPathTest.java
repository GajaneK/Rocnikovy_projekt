package tests;
import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.longestPath;
import static org.junit.Assert.*;
public class LongestPathTest {
    @Test
    public void testLongestPath(){
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3)));
        List<Integer> path = longestPath(g, 0, 3);
        assertEquals(Arrays.asList(0, 1, 2, 3), path);

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1 ,2), new DirectedEdge(2, 3), new DirectedEdge(0, 2), new DirectedEdge(1, 3)));
        List<Integer> path1 = longestPath(g1, 0, 3);
        assertEquals(Arrays.asList(0, 1, 2, 3), path1);

        DirectedGraph g2 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3), new DirectedEdge(3, 0)));
        List<Integer> path2 = longestPath(g2, 0, 3);
        assertEquals(Arrays.asList(0, 1, 2, 3), path2);

    }
    @Test
    public void testNoPath(){
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, List.of());
        List<Integer> path = longestPath(g, 0, 3);
        assertNull(path);
    }
}

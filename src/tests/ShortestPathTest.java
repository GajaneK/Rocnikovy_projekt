package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.shortestPath;
import static org.junit.Assert.*;
public class ShortestPathTest {
    @Test
    public void testShortestPath(){
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0 ,1), new DirectedEdge(1, 2)));
        List<Integer> path = shortestPath(g, 0, 2);
        assertEquals(Arrays.asList(0, 1, 2), path);

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3), new DirectedEdge(0, 2)));
        List<Integer> path1 = shortestPath(g1, 0, 3);
        assertEquals(Arrays.asList(0, 2, 3), path1);

        DirectedGraph g2 = new AdjacencyMatrixDirectedGraph(1, List.of());
        List<Integer> path2 = shortestPath(g2, 0, 0);
        assertEquals(List.of(0), path2);
    }
    @Test
    public void testNoPath(){
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2)));
        List<Integer> path = shortestPath(g, 2, 3);
        assertNull(path);
    }
}

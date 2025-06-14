package tests;

import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.topologicalSort;
import static org.junit.Assert.*;

public class TopologicalSortTest {
    @Test
    public void testTopologicalSort(){
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 3)));
        List<Integer> result = topologicalSort(g);
        assertEquals(Arrays.asList(0, 1, 2, 3), result);

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(0, 2), new DirectedEdge(1, 3), new DirectedEdge(2, 3)));
        List<Integer> result1 = topologicalSort(g1);
        assertTrue(result1.containsAll(Arrays.asList(0, 1, 2, 3)));

        DirectedGraph g2 = new AdjacencyMatrixDirectedGraph(4, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 0)));
        List<Integer> result2 = topologicalSort(g2);
        assertNull(result2);
    }
}

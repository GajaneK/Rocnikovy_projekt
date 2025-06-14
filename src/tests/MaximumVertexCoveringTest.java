package tests;

import graphs.*;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.maximumVertexCovering;
import static org.junit.Assert.*;
public class MaximumVertexCoveringTest {
    @Test
    public void testSimpleGraph(){
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2)));
        Set<Integer> result = maximumVertexCovering(g);
        assertEquals(Set.of(1), result);
    }

    @Test
    public void testCompleteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 2),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(1, 3),
                new UndirectedEdge(2, 3)));
        Set<Integer> result = maximumVertexCovering(g);
        assertEquals(Set.of(1, 2, 3), result);
    }
    @Test
    public void testDisconnected() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(2, 3)));
        Set<Integer> result = maximumVertexCovering(g);
        assertEquals(Set.of(1, 3), result);
    }
    @Test
    public void testEmptyGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Set.of());
        Set<Integer> result = maximumVertexCovering(g);
        assertEquals(Set.of(), result);
    }
    @Test
    public void testSingleEdge() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(2, Set.of(new UndirectedEdge(0, 1)));
        Set<Integer> result = maximumVertexCovering(g);
        assertEquals(Set.of(1), result);
    }

}

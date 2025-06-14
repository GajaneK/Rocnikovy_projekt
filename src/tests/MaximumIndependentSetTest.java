package tests;
import graphs.*;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.maximumIndependentSet;
import static org.junit.Assert.*;

public class MaximumIndependentSetTest {
    @Test
    public void testEmptyGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(0, Set.of());
        Set<Integer> result = maximumIndependentSet(g);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleVertex() {
        UndirectedGraph g  = new AdjacencyMatrixUndirectedGraph(1, Set.of());
        Set<Integer> result = maximumIndependentSet(g);
        assertEquals(Set.of(0), result);
    }
    @Test
    public void testCompleteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 2),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(1, 3),
                new UndirectedEdge(2, 3)));
        Set<Integer> result = maximumIndependentSet(g);
        assertEquals(Set.of(0), result);
    }
    @Test
    public void testBipartiteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(2, 1),
                new UndirectedEdge(2, 3)));
        Set<Integer> result = maximumIndependentSet(g);
        assertEquals(Set.of(0, 2), result);
    }

    @Test
    public void testCycleGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(2, 3),
                new UndirectedEdge(3, 0)));
        Set<Integer> result = maximumIndependentSet(g);
        assertEquals(Set.of(0, 2), result);
    }

    @Test
    public void testDisconnectedGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(5, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(2, 3)));
        Set<Integer> result = maximumIndependentSet(g);
        assertEquals(Set.of(0, 2, 4), result);
        //toto by malo byt len set.of(0,2) ale tak to nefunguje
    }
}

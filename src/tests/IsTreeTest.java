package tests;

import graphs.*;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.isTree;
import static org.junit.Assert.*;

public class IsTreeTest {
    @Test
    public void testValidTree() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(1, 3)));
        assertTrue(isTree(g));
    }
    @Test
    public void testGraphWithCycle() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Set.of(new UndirectedEdge(0, 1), new UndirectedEdge(1, 2), new UndirectedEdge(2, 0)));
        assertFalse(isTree(g));
    }
    @Test
    public void testDisconnectedGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1), new UndirectedEdge(2,3)));
        assertFalse(isTree(g));
    }
    @Test
    public void testGraphWithIsolatedVertex() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Set.of(new UndirectedEdge(0, 1)));
        assertFalse(isTree(g));
    }

    @Test
    public void testEmptyGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(0, Set.of());
        assertFalse(isTree(g));
    }
}

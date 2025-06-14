package tests;
import graphs.*;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.isBipartite;
import static org.junit.Assert.*;
public class IsBipartiteTest {
    @Test
    public void testBipartiteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1), new UndirectedEdge(0, 3),
                new UndirectedEdge(2, 1),
                new UndirectedEdge(2, 3)));
        assertTrue(isBipartite(g));
    }

    @Test
    public void testNonBipartiteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Set.of( new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(2, 0)));
        assertFalse(isBipartite(g));
    }
    @Test
    public void testDisconnected() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(2, 3)));
        assertTrue(isBipartite(g));
    }
    @Test
    public void testEmptyGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(0, Set.of());
        assertTrue(isBipartite(g));
    }
    @Test
    public void testSingleVertexGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(1, Set.of());
        assertTrue(isBipartite(g));
    }
}


package tests;
import graphs.*;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.hasColouring;
import static org.junit.Assert.*;
public class HasColouringTest {
    @Test
    public void testTwoColors() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(2, 1),
                new UndirectedEdge(2, 3)));
        assertTrue(hasColouring(g, 2));
    }

    @Test
    public void testThreeColors() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(2, 0),
                new UndirectedEdge(0, 3)));
        assertTrue(hasColouring(g, 3));
        assertFalse(hasColouring(g, 2));
    }

    @Test
    public void testCompleteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 2),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(1, 3),
                new UndirectedEdge(2, 3)));
        assertTrue(hasColouring(g, 4));
        assertFalse(hasColouring(g, 3));
    }

    @Test
    public void testSingleVertexGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(1, Set.of());
        assertTrue(hasColouring(g, 1));
    }
}

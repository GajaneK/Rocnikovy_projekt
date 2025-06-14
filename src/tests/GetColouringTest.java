package tests;
import graphs.*;
import org.junit.Test;
import java.util.*;
import static graphs.Graphs.getColouring;
import static org.junit.Assert.*;
public class GetColouringTest {
    @Test
    public void testTwoColors() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(2, 1),
                new UndirectedEdge(2, 3)));
        assertNotNull(getColouring(g, 2));
    }
    @Test
    public void testThreeColors() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(2, 0),
                new UndirectedEdge(0, 3)));
        assertNotNull(getColouring(g, 3));
        assertNull(getColouring(g, 2));
    }

    @Test
    public void testCompleteGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(0, 2),
                new UndirectedEdge(0, 3),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(1, 3),
                new UndirectedEdge(2, 3)));
        assertNotNull(getColouring(g, 4));
        assertNull(getColouring(g, 3));
    }

    @Test
    public void testEmptyGraph() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(0, Set.of());
        assertNotNull(getColouring(g, 1));
    }

    @Test
    public void testSingleVertex() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(1, Set.of());
        assertNotNull(getColouring(g, 1));
    }

    @Test
    public void testColouringMap() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(4, Set.of(new UndirectedEdge(0, 1),
                new UndirectedEdge(1, 2),
                new UndirectedEdge(2, 3)));
        Map<Integer, Integer> colouring = getColouring(g, 2);
        assertNotNull(colouring);
        for (int vertex : colouring.keySet()) {
            for (int neighbor : g.outgoingEdgesDestinations(vertex)) {
                assertNotEquals(colouring.get(vertex), colouring.get(neighbor));
            }
        }
    }
}

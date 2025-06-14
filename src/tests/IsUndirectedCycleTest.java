package tests;

import graphs.AdjacencyListsUndirectedGraph;
import graphs.AdjacencyMatrixUndirectedGraph;
import graphs.UndirectedEdge;
import graphs.UndirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.isUndirectedCycle;
import static org.junit.Assert.*;

public class IsUndirectedCycleTest {
    @Test
    public void testEmpty() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Collections.emptyList());
        assertFalse(isUndirectedCycle(g, Collections.emptyList()));
    }
    @Test
    public void testOneVertexInvalid() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, Collections.emptyList());
        assertFalse(isUndirectedCycle(g, Collections.singletonList(0)));
    }
    @Test
    public void testOneVertexValid() {
        UndirectedGraph g = new AdjacencyMatrixUndirectedGraph(3, List.of(new UndirectedEdge(0,0)));
        assertTrue(isUndirectedCycle(g, List.of(0)));
    }
    @Test
    public void testTwoVertices() {
        UndirectedGraph g = new AdjacencyListsUndirectedGraph(3, List.of(new UndirectedEdge(0, 1)));
        assertFalse(isUndirectedCycle(g, List.of(0,1)));
    }

    @Test
    public void testValidCycle() {
        UndirectedGraph g = new AdjacencyListsUndirectedGraph(3, List.of(new UndirectedEdge(0, 1), new UndirectedEdge(1, 2), new UndirectedEdge(2, 0)));
        assertTrue(isUndirectedCycle(g, List.of(0, 1, 2, 0)));
    }

    @Test
    public void testInvalidCycle(){
        UndirectedGraph g = new AdjacencyListsUndirectedGraph(3, List.of(new UndirectedEdge(0, 1), new UndirectedEdge(1, 2)));
        assertFalse(isUndirectedCycle(g, List.of(0, 1, 2, 0)));

        UndirectedGraph g1 = new AdjacencyListsUndirectedGraph(4, List.of(new UndirectedEdge(0, 1), new UndirectedEdge(1, 2), new UndirectedEdge(2, 0), new UndirectedEdge(2, 3)));
        assertFalse(isUndirectedCycle(g1, List.of(0, 1, 2, 3, 0)));
    }


}

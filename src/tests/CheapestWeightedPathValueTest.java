package tests;

import graphs.*;
import org.junit.Test;
import java.util.Set;

import static graphs.Graphs.cheapestWeightedPathValue;
import static org.junit.Assert.*;

public class CheapestWeightedPathValueTest {

    @Test
    public void testSinglePath() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(3, Set.of(
                new WeightedDirectedEdge(0, 1, 3),
                new WeightedDirectedEdge(1, 2, 2)
        ));
        assertEquals(5.0, cheapestWeightedPathValue(g, 0, 2), 1e-6);
    }

    @Test
    public void testMultiplePaths() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(4, Set.of(
                new WeightedDirectedEdge(0, 1, 2),
                new WeightedDirectedEdge(1, 2, 2),
                new WeightedDirectedEdge(0, 2, 5),
                new WeightedDirectedEdge(2, 3, 1),
                new WeightedDirectedEdge(1, 3, 4)
        ));
        assertEquals(5.0, cheapestWeightedPathValue(g, 0, 3), 1e-6);
    }

    @Test
    public void testNoPath() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(3, Set.of(
                new WeightedDirectedEdge(0, 1, 3)
        ));
        assertEquals(-1.0, cheapestWeightedPathValue(g, 0, 2), 1e-6);
    }

    @Test
    public void testOneVertex() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(3, Set.of(
                new WeightedDirectedEdge(0, 1, 3),
                new WeightedDirectedEdge(1, 2, 2)
        ));
        assertEquals(0.0, cheapestWeightedPathValue(g, 0, 0), 1e-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidVertex() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(2, Set.of(
                new WeightedDirectedEdge(0, 1, 3)
        ));
        cheapestWeightedPathValue(g, 0, 5);
    }
}

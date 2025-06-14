package tests;

import graphs.*;

import org.junit.Test;
import java.util.*;
import static graphs.Graphs.cheapestWeightedPath;
import static org.junit.Assert.*;

public class CheapestWeightedPathTest {
    @Test
    public void testSinglePath() {
        WeightedDirectedGraph g  = new WeightedSuccessorListsDirectedGraph(3, Set.of(new WeightedDirectedEdge(0, 1, 3), new WeightedDirectedEdge(1, 2, 2)));
        assertEquals(List.of(0, 1, 2), cheapestWeightedPath(g, 0, 2));
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
        assertEquals(List.of(0, 1, 2, 3), cheapestWeightedPath(g, 0, 3));
    }
    @Test
    public void testNoPath() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(3, Set.of(new WeightedDirectedEdge(0, 1 , 3)));
        assertTrue(cheapestWeightedPath(g, 0, 2).isEmpty());
    }

    @Test
    public void testOneVertex() {
        WeightedDirectedGraph g = new WeightedSuccessorListsDirectedGraph(3, Set.of(
                new WeightedDirectedEdge(0, 1, 3),
                new WeightedDirectedEdge(1, 2, 2)));
        assertEquals(List.of(), cheapestWeightedPath(g, 0, 0));
    }
}

package tests;
import graphs.AdjacencyMatrixDirectedGraph;
import graphs.DirectedEdge;
import graphs.DirectedGraph;
import org.junit.Test;
import java.util.*;

import static graphs.Graphs.distance;
import static org.junit.Assert.*;
public class DistanceTest {

    @Test
    public void testDistance() {
        DirectedGraph g = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2)));
        assertEquals(2, distance(g, 0, 2));

        DirectedGraph g1 = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1), new DirectedEdge(1, 2), new DirectedEdge(2, 0)));
        assertEquals(2, distance(g1, 0, 2));
        assertEquals(1, distance(g1, 0, 1));

        DirectedGraph g2 = new AdjacencyMatrixDirectedGraph(3, List.of(new DirectedEdge(0, 1)));
        assertEquals(-1, distance(g2, 1, 2));
    }

}

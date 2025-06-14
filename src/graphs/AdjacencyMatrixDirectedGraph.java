package graphs;

import java.util.*;

/**
 * Trieda reprezentujuca orientovany graf pomocou matice susednosti.
 */
public class AdjacencyMatrixDirectedGraph implements DirectedGraph {
    /**
     * Matica susednosti.
     */
    private boolean[][] adjacencyMatrix;

    /**
     * Pocet orientovanych hran v grafe (velkost grafu).
     */
    private int directedEdgeCount;

    /**
     * Konstruktor, ktory dostane ako argumenty pocet vrcholov grafu (t. j. jeho rad), ako aj vsetky hrany grafu.
     *
     * @param vertexCount   Rad grafu, cize pocet jeho vrcholov.
     * @param directedEdges Zoskupenie pozostavajuce zo vsetkych orientovanych hran grafu.
     * @throws IllegalArgumentException &ndash; ak je pocet vrcholov zaporny, ak je niektora hrana incidentna
     *                                          s neexistujucim vrcholom, alebo ak zoskupenie directedEdges obsahuje
     *                                          viacero hran s rovnakym pociatocnym aj koncovym vrcholom.
     */
    public AdjacencyMatrixDirectedGraph(int vertexCount, Collection<? extends DirectedEdge> directedEdges) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Negative vertex count.");
        }
        adjacencyMatrix = new boolean[vertexCount][vertexCount];
        directedEdgeCount = 0;
        for (DirectedEdge e : directedEdges) {
            if (!hasVertex(e.getFrom()) || !hasVertex(e.getTo())) {
                throw new IllegalArgumentException("Edge incident to a nonexistent vertex.");
            }
            if (!hasEdge(e.getFrom(), e.getTo())) {
                adjacencyMatrix[e.getFrom()][e.getTo()] = true;
                directedEdgeCount++;
            } else {
                throw new IllegalArgumentException("Multiple edges connecting the same pair of vertices.");
            }
        }
    }

    @Override
    public int getVertexCount() {
        return adjacencyMatrix.length;
    }

    @Override
    public boolean hasVertex(int v) {
        return v >= 0 && v <= getVertexCount() - 1;
    }

    @Override
    public int getDirectedEdgeCount() {
        return directedEdgeCount;
    }

    @Override
    public boolean hasEdge(int from, int to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        return adjacencyMatrix[from][to];
    }

    @Override
    public Iterable<Integer> outgoingEdgesDestinations(int vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i <= getVertexCount() - 1; i++) {
            if (adjacencyMatrix[vertex][i]) {
                a.add(i);
            }
        }
        return Collections.unmodifiableList(a);
    }
}
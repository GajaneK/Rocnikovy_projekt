package graphs;

import java.util.*;

/**
 * Trieda reprezentujuca orientovany graf pomocou zoznamov naslednikov jednotlivych jeho vrcholov.
 */
public class SuccessorListsDirectedGraph implements DirectedGraph {
    /**
     * Pre kazdy vrchol si pamatame zoznam jeho naslednikov.
     */
    private ArrayList<ArrayList<Integer>> successorLists;

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
    public SuccessorListsDirectedGraph(int vertexCount, Collection<? extends DirectedEdge> directedEdges) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Negative vertex count.");
        }
        successorLists = new ArrayList<>();
        for (int i = 0; i <= vertexCount - 1; i++) {
            successorLists.add(new ArrayList<>());
        }
        directedEdgeCount = 0;
        for (DirectedEdge e : directedEdges) {
            if (!hasVertex(e.getFrom()) || !hasVertex(e.getTo())) {
                throw new IllegalArgumentException("Edge incident to a nonexistent vertex.");
            }
            if (!hasEdge(e.getFrom(), e.getTo())) {
                successorLists.get(e.getFrom()).add(e.getTo());
                directedEdgeCount++;
            } else {
                throw new IllegalArgumentException("Multiple edges connecting the same pair of vertices.");
            }
        }
    }

    @Override
    public int getVertexCount() {
        return successorLists.size();
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
        return successorLists.get(from).contains(to);
    }

    @Override
    public Iterable<Integer> outgoingEdgesDestinations(int vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        // Nasledujucim prikazom vratime nemodifikovatelny pohlad na zoznam naslednikov vrcholu vertex
        return Collections.unmodifiableList(successorLists.get(vertex));
    }
}
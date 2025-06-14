package graphs;

import java.util.*;

/**
 * Trieda reprezentujuca neorientovany graf pomocou zoznamov susedov jednotlivych jeho vrcholov.
 */
public class AdjacencyListsUndirectedGraph extends SuccessorListsDirectedGraph implements UndirectedGraph {
    private int undirectedEdgeCount;

    /**
     * Konstruktor, ktory dostane ako argumenty pocet vrcholov grafu (t. j. jeho rad) a vsetky jeho hrany.
     *
     * @param vertexCount   Rad grafu, cize pocet jeho vrcholov.
     * @param undirectedEdges Zoskupenie pozostavajuce zo vsetkych neorientovanych hran grafu.
     * @throws IllegalArgumentException &ndash; ak je pocet vrcholov zaporny, ak je niektora hrana incidentna
     *                                          s neexistujucim vrcholom, alebo ak zoskupenie undirectedEdges obsahuje
     *                                          viacero neorientovanych hran spajajucich rovnaku dvojicu vrcholov.
     */
    public AdjacencyListsUndirectedGraph(int vertexCount, Collection<? extends UndirectedEdge> undirectedEdges) {
        super(vertexCount, Edges.undirectedToDirectedEdges(undirectedEdges));
        undirectedEdgeCount = undirectedEdges.size();
    }

    @Override
    public int getUndirectedEdgeCount() {
        return undirectedEdgeCount;
    }
}
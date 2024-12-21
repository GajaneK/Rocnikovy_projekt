package graphs;

public interface WeightedDirectedGraph extends DirectedGraph {
    /**
     * Metoda, ktora vrati vsetky ohodnotene hrany vychadzajuce z vrcholu vertex reprezentovaneho ohodnoteneho grafu.
     * @param vertex Vrchol ohodnoteneho grafu.
     * @return       Ohodnotene hrany vychadzajuce z vrcholu vertex.
     */
    Iterable<WeightedDirectedEdge> outgoingWeightedDirectedEdges(int vertex);
}
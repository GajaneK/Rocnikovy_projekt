package graphs;

/**
 *  Rozhranie implementovane reprezentaciami neorientovanych grafov o vrcholoch 0, 1, ..., n-1 pre nejake prirodzene n.
 */
public interface UndirectedGraph extends DirectedGraph {
    /**
     * Metoda, ktora vrati pocet neorientovanych hran reprezentovaneho grafu.
     *
     * @return Pocet neorientovanych hran grafu.
     */
    int getUndirectedEdgeCount();

}
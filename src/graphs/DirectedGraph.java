package graphs;

/**
 *  Rozhranie implementovane reprezentaciami orientovanych grafov o vrcholoch 0, 1, ..., n-1 pre nejake prirodzene n.
 *  Aj neorientovane grafy budu povazovane za specialny pripad orientovanych grafov; toto rozhranie tak bude
 *  implementovane vsetkymi triedami reprezentujucimi grafy.
 */
public interface DirectedGraph {
    /**
     * Metoda, ktora vrati pocet vrcholov reprezentovaneho grafu.
     *
     * @return Pocet vrcholov grafu.
     */
    int getVertexCount();

    /**
     * Metoda, ktora zisti, ci graf obsahuje vrchol s cislom vertex.
     *
     * @param vertex Vrchol, ktoreho existencia sa ma zistit.
     * @return       Vrati true prave vtedy, ked graf obsahuje vrchol s cislom vertex.
     */
    boolean hasVertex(int vertex);

    /**
     * Metoda, ktora vrati pocet orientovanych hran reprezentovaneho grafu.
     *
     * @return Pocet orientovanych hran grafu.
     */
    int getDirectedEdgeCount();

    /**
     * Metoda, ktora zisti, ci v grafe existuje hrana medzi danou dvojicou vrcholov.
     *
     * @param from Pociatocny vrchol.
     * @param to   Koncovy vrchol.
     * @return     Vrati true prave vtedy, ked v grafe existuje hrana z vrcholu from do vrcholu to.
     * @throws IllegalArgumentException &ndash; ak niektory z argumentov nie je vrcholom grafu.
     */
    boolean hasEdge(int from, int to);

    /**
     * Metoda, ktora vrati vsetkych naslednikov daneho vrcholu -- cize vsetky vrcholy, do ktorych vedie z daneho vrcholu
     * orientovana hrana. Pre neorientovane grafy tak tato metoda vzdy vrati vsetkych susedov daneho vrcholu.
     *
     * @param vertex Lubovolny vrchol grafu.
     * @return       Naslednici vrcholu vertex ako instancia typu Iterable&lt;Integer&gt;.
     * @throws IllegalArgumentException &ndash; ak argumentom nie je vrchol grafu.
     */
    Iterable<Integer> outgoingEdgesDestinations(int vertex);
}
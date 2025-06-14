package graphs;

import java.util.*;

public class Edges {

    /**
     * Metoda, ktora prerobi zoskupenie neorientovanych hran na prislusny zoznam orientovanych hran.
     * @param undirectedEdges Zoznam neorientovanych hran.
     * @return                Prislusny zoznam orientovanych hran.
     */
    public static List<DirectedEdge> undirectedToDirectedEdges(Collection<? extends UndirectedEdge> undirectedEdges) {
        ArrayList<DirectedEdge> result = new ArrayList<>();
        for (UndirectedEdge e : undirectedEdges) {
            ArrayList<Integer> vertices = new ArrayList<>(e.getIncidentVertices());
            if (vertices.size() == 1) {
                result.add(new DirectedEdge(vertices.get(0), vertices.get(0)));
            } else {
                result.add(new DirectedEdge(vertices.get(0), vertices.get(1)));
                result.add(new DirectedEdge(vertices.get(1), vertices.get(0)));
            }
        }
        return Collections.unmodifiableList(result);
    }
}
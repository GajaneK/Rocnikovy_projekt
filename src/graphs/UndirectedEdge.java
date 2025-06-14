package graphs;

import java.util.*;

public class UndirectedEdge {
    private Set<Integer> incidentVertices;

    public UndirectedEdge(int u, int v) {
        incidentVertices = new HashSet<>();
        incidentVertices.add(u);
        incidentVertices.add(v);
    }

    public Set<Integer> getIncidentVertices() {
        return Collections.unmodifiableSet(incidentVertices);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return this.getClass() == o.getClass() && incidentVertices.equals(((UndirectedEdge) o).getIncidentVertices());
    }

    @Override
    public int hashCode() {
        return incidentVertices.hashCode();
    }
}
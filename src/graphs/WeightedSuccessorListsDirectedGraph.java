package graphs;

import java.util.*;

public class WeightedSuccessorListsDirectedGraph extends SuccessorListsDirectedGraph implements WeightedDirectedGraph {
    private ArrayList<ArrayList<WeightedDirectedEdge>> outgoingWeightedEdges;

    public WeightedSuccessorListsDirectedGraph(int vertexCount,
                                               Collection<? extends WeightedDirectedEdge> weightedDirectedEdges) {
        super(vertexCount, weightedDirectedEdges);
        outgoingWeightedEdges = new ArrayList<>();
        for (int v = 0; v <= vertexCount - 1; v++) {
            outgoingWeightedEdges.add(new ArrayList<>());
        }
        for (WeightedDirectedEdge weightedDirectedEdge : weightedDirectedEdges) {
            outgoingWeightedEdges.get(weightedDirectedEdge.getFrom()).add(weightedDirectedEdge);
        }
    }

    @Override
    public Iterable<WeightedDirectedEdge> outgoingWeightedDirectedEdges(int vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        return Collections.unmodifiableList(outgoingWeightedEdges.get(vertex));
    }
}
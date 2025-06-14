package graphs;

/**
 * Trieda reprezentujuca ohodnotenu orientovanu hranu.
 */
public class WeightedDirectedEdge extends DirectedEdge {
    private double weight;

    public WeightedDirectedEdge(int from, int to, double weight) {
        super(from, to);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return getClass() == o.getClass() &&
                getFrom() == ((WeightedDirectedEdge) o).getFrom() &&
                getTo() == ((WeightedDirectedEdge) o).getTo() &&
                getWeight() == ((WeightedDirectedEdge) o).getWeight();
    }

    @Override
    public int hashCode() {
        return Double.valueOf(weight).hashCode() + 31 * super.hashCode();
    }
}
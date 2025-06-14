package graphs;

public class DirectedEdge {
    private int from, to;

    public DirectedEdge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return this.getClass() == o.getClass() && from == ((DirectedEdge) o).from && to == ((DirectedEdge) o).to;
    }

    @Override
    public int hashCode() {
        return from + 31 * to;
    }
}
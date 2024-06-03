// This class will represent the edges between stars.

public class Edge implements Comparable<Edge> {
    public String star1;
    public String star2;
    public int weight;

    public Edge(String star1, String star2, int weight) {
        this.star1 = star1;
        this.star2 = star2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}


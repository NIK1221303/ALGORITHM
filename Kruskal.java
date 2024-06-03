import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Kruskal {

    private StarRoutes starRoutes;
    private List<Edge> edges;
    private List<String> stars;
    private List<Edge> mst;

    public Kruskal(StarRoutes starRoutes) {
        this.starRoutes = starRoutes;
        this.stars = new ArrayList<>(starRoutes.getStarRoutes().keySet());
        this.edges = new ArrayList<>();
        this.mst = new ArrayList<>();
        initializeEdges();
    }

    private void initializeEdges() {
        for (String star1 : starRoutes.getStarRoutes().keySet()) {
            for (Map.Entry<String, Integer> entry : starRoutes.getConnectedStars(star1).entrySet()) {
                String star2 = entry.getKey();
                int distance = entry.getValue();
                edges.add(new Edge(star1, star2, distance));
            }
        }
    }

    public void findMST() throws IOException {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(stars);

        for (Edge edge : edges) {
            if (uf.union(edge.star1, edge.star2)) {
                mst.add(edge);
            }
        }

        printResult("mst.txt");

    }

    public void printResult(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Minimum Spanning Tree:\n");
            for (Edge edge : mst) {
                writer.write(String.format("%s - %s: %d\n", edge.star1, edge.star2, edge.weight));
            }
        }
    }
}

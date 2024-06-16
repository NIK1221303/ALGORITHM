package ShortestPaths;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import MinimumSpanningTree.Edge;
import DatasetTwo.StarRoutes;

public class Dijkstra {

    private Set<String> visited; // all visited node put here
    private Map<String, Integer> shortestDist; // store shortest distance from Star A
    private Map<String, PriorityQueue<Edge>> graph; // this graph store all edges for each star, each String key have
                                                    // priorityQueue, so always first value for each key is the Edge
                                                    // with min distance
    private final StarRoutes starRoutes;
    private static final int INFINITE = Integer.MAX_VALUE;

    public Dijkstra(StarRoutes starRoutes) {
        this.starRoutes = starRoutes;
        visited = new HashSet<>();
        shortestDist = new HashMap<>();
        graph = new HashMap<>();

    }

    private void initializeEdges() {
        for (String star1 : starRoutes.getStarRoutes().keySet()) {
            PriorityQueue<Edge> edges = new PriorityQueue<>();
            for (Map.Entry<String, Integer> entry : starRoutes.getConnectedStars(star1).entrySet()) {
                String star2 = entry.getKey();
                int distance = entry.getValue();
                edges.add(new Edge(star1, star2, distance)); // add all edges for star1, ascending order of distance
            }
            graph.put(star1, edges); // now add star1 and it's edges into 'graph'(Map)
        }
    }

    public PriorityQueue<Edge> getEdges(String e) {

        return graph.get(e);
    }

    public void InitializeToInfinite(int minDistance) {

        shortestDist.clear();
        for (String star : graph.keySet()) {
            for (Edge edge : getEdges(star)) {

                if (!shortestDist.containsKey(edge.star2)) {
                    shortestDist.put(edge.star2, minDistance); // set all distance to infinite (max value)
                }
            }
        }
    }

    public void calcShortestDistance(String currentNode, int currentDist) {
        int currentShortest = INFINITE;
        String nextNode = null;
        visited.add(currentNode); // Star A

        for (Edge edge : getEdges(currentNode)) { // Get edges of Star A
            if (!visited.contains(edge.star2)) { // If the edge is not inside visited,
                if (edge.distance + currentDist < shortestDist.get(edge.star2)) {
                    shortestDist.put(edge.star2, edge.distance + currentDist); // update min distance
                }
                //
                if (currentShortest > edge.distance + currentDist) { //
                    currentShortest = edge.distance + currentDist; // update currentDist
                    nextNode = edge.star2; // assign node with shortest distance edge
                }
                for (Map.Entry<String, Integer> entry : shortestDist.entrySet()) {
                    if (visited.contains(entry.getKey())) {
                        continue;
                    }
                    if (entry.getValue() < currentShortest) {
                        currentShortest = entry.getValue();
                        nextNode = entry.getKey();
                    }
                }
            }
        }

        if (nextNode == null) {

            return;
        }

        // should only call this when done calculated all min distance to every edge
        calcShortestDistance(nextNode, currentShortest);
    }

    private void printResult(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Shortest Path:\n");
            for (Map.Entry<String, Integer> entry : shortestDist.entrySet()) {
                writer.write(String.format("%s - %s: %d\n", "Star A", entry.getKey(), entry.getValue()));
            }
        }
    }

    public void shortestPath(String startNode) throws IOException {
        initializeEdges(); // initialize all edges for every Star
        InitializeToInfinite(INFINITE); // initialize shortest distance to infinite first
        shortestDist.put(startNode, 0); // set start node distance to 0
        calcShortestDistance(startNode, 0); // start calculate shortest distance
        printResult("ShortestPaths/ShortestPath.txt");
    }
}